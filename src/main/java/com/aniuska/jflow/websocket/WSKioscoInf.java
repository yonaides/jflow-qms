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
import java.util.function.Predicate;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.websocket.CloseReason;
import javax.websocket.CloseReason.CloseCodes;
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
@ServerEndpoint(value = "/kioscoinf", decoders = {MessageDecoder.class}, encoders = {MessageEncoder.class})
public class WSKioscoInf {

    @EJB
    private KioscoFacade kioscoCtrl;
    private final Logger LOG = LogManager.getLogger(WSKioscoInf.class);
    private final Map<Kiosco, Session> clients = Collections.synchronizedMap(new HashMap());

    @OnOpen
    public void open(Session session) {

        QueryParams qpp = new QueryParams();
        String qp = session.getQueryString();

        if (qp != null) {
            qpp.parser(qp);
        }

        login(session, qpp);
    }

    @OnMessage
    public void handleMessage(Session session, Message message) {
        LOG.info("Receive message {} ", message);
    }

    @OnClose
    public void close(Session session) {

        synchronized (clients) {
            if (clients.containsValue(session)) {

                Kiosco k = (Kiosco) session.getUserProperties()
                        .get(WSParams.KIOSCO);

                if (k != null) {
                    k.setUltimaConexion(new Date());
                    kioscoCtrl.edit(k);
                    clients.remove(k);
                    LOG.info("Disconnecting Kiosk {}", k.getDescripcion());
                }
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
            clients.values()
                    .stream()
                    .forEach((session) -> {
                        closeReason(session, CloseCodes.GOING_AWAY, "Server will go down!");
                    });
            clients.clear();
        }
    }

    public void sendMessage(Sucursal sucursal, Message nm) {

        synchronized (clients) {

            LOG.info("Sending to all kiosk from ofice id = {}, name = {}",
                    sucursal.getIdsucursal(), sucursal.getNombre());
            LOG.info("Kiosk conneted : {}", clients.size());

            Predicate<Map.Entry<Kiosco, Session>> p;
            p = (entry) -> (sucursal.equals(entry.getKey().getIdsucursal()));

            clients.entrySet()
                    .stream()
                    .filter(p)
                    .forEach((entry) -> {
                        sendMessage(entry.getValue(), nm);
                    });
        }
    }

    public void sendMessage(Kiosco kioscoInf, Message nm) {

        synchronized (clients) {

            LOG.info("Sending to all kiosk from ofice id = {}, name = {}",
                    kioscoInf.getIdsucursal(), kioscoInf.getIdsucursal().getNombre());
            LOG.info("Kiosk conneted : {}", clients.size());

            Session session = clients.get(kioscoInf);

            if (session != null) {
                sendMessage(session, nm);
            }

        }
    }

    private void sendMessage(Session session, Message nm) {

        try {
            LOG.info("Sending message : {}", nm);
            session.getAsyncRemote()
                    .sendObject(nm);
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }

    }

    public boolean isConnected(Kiosco k) {
        synchronized (clients) {
            return clients.containsKey(k);
        }
    }

    private void login(Session session, QueryParams qpp) {

        String tokenApi = qpp.get(WSParams.TOKEN_API_PARAM);
        String version = qpp.get(WSParams.VERSION_PARAM);

        if (tokenApi == null) {
            closeReason(session, CloseCodes.NOT_CONSISTENT,
                    "Authentication error! check tokenApi and verion params");
            return;
        }

        try {

            Kiosco k = kioscoCtrl.getKioscoInf(tokenApi);
            if (k == null) {
                closeReason(session, CloseCodes.VIOLATED_POLICY,
                        "Authentication error! kiosk haven't be found");
                return;
            }

            synchronized (clients) {

                // Verificamos si el kiosco tiene una conexion existente para cerrarla
                if (clients.containsKey(k)) {
                    Session s = clients.get(k);
                    closeReason(session, CloseCodes.UNEXPECTED_CONDITION,
                            "Other connection happened");
                }

                k.setUltimaConexion(new Date());
                k.setVersionKiosco(version);
                kioscoCtrl.edit(k);

                clients.put(k, session);
                session.getUserProperties()
                        .put(WSParams.KIOSCO, k);

                sendMessage(session, new Message(MessageType.SUCCESS_LOGIN)
                        .put("nombreOficina", k.getIdsucursal().getNombre()));

                LOG.info("Kiosk '{}' connected successfully", k.getDescripcion());

            }
        } catch (Exception ex) {
            closeReason(session, CloseCodes.NOT_CONSISTENT, "Authentication error! check tokenApi and verion params");
        }

    }

    private void closeReason(Session session, CloseReason.CloseCodes cc, String motivo) {
        try {
            session.close(new CloseReason(cc, motivo));
        } catch (IOException ex1) {
        }
    }

}
