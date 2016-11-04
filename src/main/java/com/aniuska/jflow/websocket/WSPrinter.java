/**
 * WSPrinter, is a WebService client to send "Turno" to a printer
 */
package com.aniuska.jflow.websocket;

import com.aniuska.jflow.ejb.DispositivoFacade;
import com.aniuska.jflow.entity.Dispositivo;
import com.aniuska.jflow.entity.Sucursal;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;
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
@ServerEndpoint(value = "/printer", decoders = {MessageDecoder.class}, encoders = {MessageEncoder.class})
public class WSPrinter {

    @EJB
    DispositivoFacade kioscoCtrl;
    private final Logger LOG = LogManager.getLogger(WSPrinter.class);
    private final Map<Dispositivo, Session> clients = Collections.synchronizedMap(new HashMap());

    @OnOpen
    public void open(Session session) {

        QueryParams qpp = new QueryParams();
        String queryString = session.getQueryString();
        if (queryString != null) {
            qpp.parser(queryString);
        }

        login(session, qpp);
    }

    @OnMessage
    public void handleMessage(Session session, Message message) {
        LOG.info("Received menssage {}", message);
    }

    @OnClose
    public void close(Session session) {

        Dispositivo printer = (Dispositivo) session.getUserProperties()
                .get(WSParams.PRINTER);

        if (printer != null) {
            synchronized (clients) {
                clients.remove(printer);
                LOG.info("Removing printer sucursal {}",
                        printer.getIdsucursal().getNombre());
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

            Predicate<Map.Entry<Dispositivo, Session>> p;
            p = (entry) -> (sucursal.equals(entry.getKey().getIdsucursal()));

            clients.entrySet().stream().filter(p).forEach((entry) -> {
                sendMessage(entry.getValue(), nm);
            });
        }
    }

    public void sendMessage(Dispositivo kioscoInf, Message nm) {

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

    public void sendMessage(Message nm) {

        synchronized (clients) {

            LOG.info("Sending to all kiosk...");

            this.clients.forEach((Dispositivo d, Session session) -> {
                sendMessage(session, nm);
            });
        }
    }

    private void sendMessage(Session session, Message nm) {

        try {
            LOG.info("Sending message : {}", nm);
            session.getAsyncRemote()
                    .sendObject(nm);

            LOG.info("The message has been sent : {}", nm);
        } catch (Exception ex) {
            LOG.error("Error sending message to printer : {}", ex.getMessage(), ex);
        }

    }

    public boolean isConnected(Sucursal sucursal) {
        Predicate<Map.Entry<Dispositivo, Session>> p;
        p = (entry) -> (sucursal.equals(entry.getKey().getIdsucursal()));
        Stream<Map.Entry<Dispositivo, Session>> printers = clients.entrySet().stream();
        return printers.anyMatch(p);
    }

    public boolean isConnected(Dispositivo printer) {
        return clients.containsKey(printer);
    }

    private void login(Session session, QueryParams qpp) {

        String tokenApi = qpp.get(WSParams.TOKEN_API_PARAM);
        String version = qpp.get(WSParams.VERSION_PARAM);

        if (tokenApi == null) {
            closeReason(session, CloseCodes.NOT_CONSISTENT,
                    "Authentication error! check tokenApi and verion params");
            return;
        }

        Dispositivo printer = kioscoCtrl.getPrinter(tokenApi);
        if (printer == null) {
            closeReason(session, CloseCodes.VIOLATED_POLICY,
                    "Error login, printer (" + tokenApi + ") not found");
            return;
        }

        LOG.info("Sucursal with #{} is {}", tokenApi, printer);
        // Si el mensaje es de tipo LOGIN, lo agregarlo a la lista de Clientes (KIOSCOS)
        clients.put(printer, session);

        session.getUserProperties()
                .put(WSParams.PRINTER, printer);

        sendMessage(session,
                new Message(MessageType.SUCCESS_LOGIN)
                .put("nombreSucursal", printer.getIdsucursal().getNombre())
        );

    }

    private void closeReason(Session session, CloseCodes cc, String motivo) {
        try {
            session.close(new CloseReason(cc, motivo));
        } catch (IOException ex1) {
        }
    }

}
