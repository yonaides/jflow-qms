/**
 * WSPrinter, is a WebService client to send "Turno" to a printer
 */
package com.aniuska.jflow.websocket;

import com.aniuska.jflow.ejb.SucursalFacade;
import com.aniuska.jflow.entity.Sucursal;
import java.io.IOException;
import java.util.Collections;
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
@ServerEndpoint("/printer")
public class WSPrinter {

    @EJB
    private SucursalFacade sucursalCtrl;
    private final Logger LOG = LogManager.getLogger(WSPrinter.class);
    private final Map<Sucursal, Session> clients = Collections.synchronizedMap(new HashMap<Sucursal, Session>());

    @OnOpen
    public void open(Session session) {
    }

    @OnMessage
    public void handleMessage(String message, Session session) {

        LOG.info("Receive menssage {}", message);
        // Convertimos el mensaje en un Object Message
        Message nm = GsonUtils.from(message, Message.class);

        switch (nm.getTipoMensaje()) {

            case MessageType.LOGIN:

                Integer sucursalId = nm.getMensaje().get("sucursal").getAsInt();
                Sucursal sucursal = sucursalCtrl.findSucursalByNumero(sucursalId);

                LOG.info("Sucursal with #{} is {}", sucursalId, sucursal);
                if (sucursal == null) {
                    try {
                        session.close(new CloseReason(CloseReason.CloseCodes.VIOLATED_POLICY,
                                "Error login, office (" + sucursalId + ") not found"));
                    } catch (IOException ex) {
                    }
                } else {
                    // Si el mensaje es de tipo LOGIN, lo agregarlo a la lista de Clientes (KIOSCOS)
                    clients.put(sucursal, session);
                    Message ms = new Message(MessageType.SUCCESS_LOGIN);
                    ms.put("nombreSucursal", sucursal.getNombre());
                    sendMessage(session, ms);
                }

                break;
        }
    }

    @OnClose
    public void close(Session session) {

        synchronized (clients) {
            for (Map.Entry<Sucursal, Session> entry : clients.entrySet()) {
                if (entry.getValue().equals(session)) {
                    LOG.info("Removing printer sucursal {}", entry.getKey().getIdsucursal());
                    clients.remove(entry.getKey());
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
            for (Session session : clients.values()) {
                try {
                    LOG.info("Disconnected printer {}", session);
                    session.close();
                } catch (IOException ex) {
                    LOG.info("Error closing session : ", ex);
                }
            }
        }
    }

    public void sendMessage(Sucursal sucursal, Message nm) {

        synchronized (clients) {
            LOG.info("Sending message to ofice {} - {}'s printer",
                    sucursal.getIdsucursal(), sucursal.getNombre());

            LOG.info("Printers connected : {}", clients.size());
            Session session = clients.get(sucursal);

            if (clients.containsKey(sucursal)) {
                sendMessage(session, nm);
            }
        }
    }

    private void sendMessage(Session session, Message nm) {

        try {
            LOG.info("Sending message : {}", nm);
            session.getBasicRemote()
                    .sendText(GsonUtils.toJson(nm));
            LOG.info("Sended message : {}", nm);
        } catch (IOException ex) {
            LOG.error("Error sending message to printer : ", ex.getMessage(), ex);
        }

    }

    public boolean isConnected(Sucursal ofi) {
        return clients.containsKey(ofi);
    }
}
