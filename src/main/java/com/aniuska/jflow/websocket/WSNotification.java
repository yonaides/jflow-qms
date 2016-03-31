/**
 *
 *
 */
package com.aniuska.jflow.websocket;

import com.aniuska.jflow.ejb.KioscoFacade;
import com.aniuska.jflow.entity.Kiosco;
import com.aniuska.jflow.entity.Sucursal;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author hectorvent@gmail.com
 */
@Singleton
@ServerEndpoint("/notification")
public class WSNotification {

    private final Logger LOG = LogManager.getLogger(WSNotification.class);

    @EJB
    private KioscoFacade kioscoCtrl;
    private final Map<Session, Kiosco> clients = Collections.synchronizedMap(new HashMap<Session, Kiosco>());

    @OnOpen
    public void open(Session session) {

    }

    @OnMessage
    public void handleMessage(String message, Session session) {

        LOG.info("Receive message {} ", message);

        // Convertimos el mensaje en un Object Message
        Message nm = GsonUtils.from(message, Message.class);

        switch (nm.getTipoMensaje()) {
            case MessageType.LOGIN:
                // Si el mensaje es de tipo LOGIN, agregarlo a la lista de Clientes (KIOSCOS)
                login(nm, session);
                break;
        }
    }

    @OnClose
    public void close(Session session) {

        synchronized (clients) {
            Kiosco k = clients.remove(session);

            if (k != null) {
                k.setUltimaConexion(new Date());
                kioscoCtrl.edit(k);
                LOG.info("Disconneting Kiosk {}", k.getDescripcion());
            }
        }
    }

    @OnError
    public void onError(Throwable error) {
        LOG.error("Error >> : ", error);
    }

    @PreDestroy
    public void closeAllSession() {

        synchronized (clients) {

            for (Session session : clients.keySet()) {
                try {
                    LOG.info("Disconneted Kiosk {}", session);
                    session.close();
                } catch (IOException ex) {
                    LOG.info("Error closing session : ", ex);
                }
            }

        }
    }

    public void sendMessage(Sucursal sucursal, Message nm) {

        synchronized (clients) {

            LOG.info("Sending to all kiosk from ofice id = {}, name = {}",
                    sucursal.getIdsucursal(), sucursal.getNombre());
            LOG.info("Kiosk conneted : {}", clients.size());

            for (Map.Entry<Session, Kiosco> entry : clients.entrySet()) {
                if (sucursal.equals(entry.getValue().getIdsucursal())) {
                    sendMessage(entry.getKey(), nm);
                }

            }
        }
    }

    private void sendMessage(Session session, Message nm) {

        try {
            LOG.info("Sending message : {}", nm);
            session.getBasicRemote()
                    .sendText(GsonUtils.toJson(nm));
        } catch (IOException ex) {
            LOG.error(ex.getMessage(), ex);
        }

    }

    public boolean isConnected(Kiosco k) {
        synchronized (clients) {
            for (Map.Entry<Session, Kiosco> entry : clients.entrySet()) {

                if (entry.getValue().equals(k)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void login(Message nm, Session session) {

        try {
            String tokenApi = nm.getMensaje().get("tokenApi").getAsString();
            String versionKiosco = nm.getMensaje().get("version").getAsString();
            Kiosco k = kioscoCtrl.find(tokenApi);

            if (k != null) {
                synchronized (clients) {

                    k.setUltimaConexion(new Date());
                    k.setVersionKiosco(versionKiosco);
                    kioscoCtrl.edit(k);

                    clients.put(session, k);
                    Message ms = new Message(MessageType.SUCCESS_LOGIN);
                    ms.put("nombreOficina", k.getIdsucursal().getNombre());

                    sendMessage(session, ms);
                    LOG.info("Success connected kiosk {} ", k.getDescripcion());
                }
            } else {
                try {
                    session.close(new CloseReason(CloseReason.CloseCodes.VIOLATED_POLICY, "Error login!, kiosk not found"));
                } catch (IOException ex) {
                }
            }
        } catch (Exception ex) {
            try {
                session.close(new CloseReason(CloseReason.CloseCodes.NOT_CONSISTENT, "Error login!, check tokenApi and verion params"));
            } catch (IOException ex1) {
            }
        }

    }

}
