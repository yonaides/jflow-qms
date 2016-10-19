/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.restful;

import com.aniuska.jflow.ejb.KioscoFacade;
import com.aniuska.jflow.ejb.SucursalFacade;
import com.aniuska.jflow.ejb.ServicioFacade;
import com.aniuska.jflow.ejb.TicketFacade;
import com.aniuska.jflow.entity.Cliente;
import com.aniuska.jflow.entity.Kiosco;
import com.aniuska.jflow.entity.Sucursal;
import com.aniuska.jflow.entity.Servicio;
import com.aniuska.jflow.entity.Ticket;
import com.aniuska.jflow.entity.TicketDetalle;
import com.aniuska.jflow.restful.model.RestTicket;
import com.aniuska.jflow.utils.Estados;
import com.aniuska.utils.date.DateUtils;
import com.sun.javafx.css.Combinator;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.CompletionCallback;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.container.TimeoutHandler;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author hectorvent@gmail.com
 */
@Path("turno")
public class TurnoResource {

    private final ExecutorService executorService = Executors.newCachedThreadPool();
    private static final Logger LOG = LogManager.getLogger(ServicioResource.class);
    @EJB
    KioscoFacade kioscoCtrl;
    @EJB
    TicketFacade turnoCtrl;
    @EJB
    ServicioFacade servicioCtrl;
    @EJB
    SucursalFacade sucursalCtrl;

    @GET
    @Path("/kiosco/{tokenApi}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findTurnosByKiosco(@PathParam("tokenApi") String token) {

        LOG.info("Peticion token {}", token);
        Kiosco k = kioscoCtrl.find(token);

        if (k == null) {
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .build();
        }

        LOG.info("Kiosco {} ", k.getDescripcion());
        k.setUltimaConexion(new Date());
        kioscoCtrl.edit(k);

        List<TicketDetalle> ts = turnoCtrl.getTurnoEnProcesoBySucursal(k.getIdsucursal());
        RestTicket turnos[] = new RestTicket[ts.size()];
        int i = 0;
        for (TicketDetalle td : ts) {
            turnos[i] = new RestTicket();
            turnos[i].setServicio(td.getIdservicio().getNombre());
//            turnos[i].set(td.getIdestacion().getNumeroEstacion() + "");
            turnos[i].setTurno(td.getIdticket().getHappyNumber());
            i++;
        }

        return Response.ok(turnos).build();
    }

    @POST
    @Consumes(value = {MediaType.APPLICATION_JSON})
    public void createTurno(@Suspended final AsyncResponse asyncResponse, final RestTicket rt) {

        asyncResponse.setTimeout(20, TimeUnit.SECONDS);
        asyncResponse.setTimeoutHandler(new TimeoutHandler() {

            @Override
            public void handleTimeout(AsyncResponse asyncResponse) {
                asyncResponse.resume(Response.status(Response.Status.SERVICE_UNAVAILABLE)
                        .entity("Operation time out.").build());
            }
        });

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                asyncResponse.resume(doCreateTurno(rt));
            }
        });
    }

    private Response doCreateTurno(RestTicket rt) {
        LOG.info("Buscando Kiosco by Token {}", rt.getTokenApi());
        Kiosco k = kioscoCtrl.find(rt.getTokenApi());

        if (k == null) {
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .build();
        }

        LOG.info("Kiosco {}, creando turno ", k.getDescripcion());
        Sucursal ofi = k.getIdsucursal();
        int num = ofi.getSecuencia() + 1;

        // Se el numero es 100 resetiar a 1, (1-99)
        if (num == 100) {
            num = 1;
        }
        ofi.setSecuencia(num);

        // Se actualiza la secuencia de la sucursal
        sucursalCtrl.edit(ofi);

        // se busca el tipo de servicio solicitado
        Servicio ser = servicioCtrl.find(rt.getServicioId());

        Ticket turno = new Ticket();
        turno.setFechaCreacion(new Date());
        turno.setHappyNumber(ser.getPrefijo() + "-" + num);
        turno.setPrioridad(rt.getPrioridad());
        turno.setIdestado(Estados.EN_ESPERA);
        turno.setIdcliente(new Cliente(BigDecimal.ONE));
        turno.setIdsucursal(ofi);

        TicketDetalle td = new TicketDetalle();
        td.setIdticket(turno);
        td.setFechaInicio(turno.getFechaCreacion());
        td.setIdestado(Estados.EN_ESPERA);
        td.setIdservicio(new Servicio(rt.getServicioId()));
        td.setTiempoEspera(BigDecimal.ZERO);
        td.setTiempoProceso(BigDecimal.ZERO);

        List<TicketDetalle> detalle = new ArrayList();

        detalle.add(td);
        turno.setTicketDetalleList(detalle);

        turnoCtrl.create(turno);

        rt.setServicio(ser.getNombre());
        rt.setTurno(turno.getHappyNumber());
        rt.setSucursal(ofi.getNombre());
        rt.setFecha(DateUtils.dateTime2String(turno.getFechaCreacion()));

        return Response.ok(rt).build();
    }

}
