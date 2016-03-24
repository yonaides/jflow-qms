/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.restful;

import com.aniuska.jflow.ejb.KioscoFacade;
import com.aniuska.jflow.ejb.OficinaFacade;
import com.aniuska.jflow.ejb.ServicioFacade;
import com.aniuska.jflow.ejb.TurnoFacade;
import com.aniuska.jflow.entity.Cliente;
import com.aniuska.jflow.entity.Kiosco;
import com.aniuska.jflow.entity.Oficina;
import com.aniuska.jflow.entity.Servicio;
import com.aniuska.jflow.entity.Turno;
import com.aniuska.jflow.entity.TurnoDetalle;
import com.aniuska.jflow.restful.model.RestTurno;
import com.aniuska.jflow.utils.Estados;
import com.edenorte.utils.date.DateUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author hventura@citrus.com.do
 */
@Path("turno")
public class TurnoResource {

    private static final Logger LOG = LogManager.getLogger(ServicioResource.class);
    @EJB
    KioscoFacade kioscoCtrl;
    @EJB
    TurnoFacade turnoCtrl;
    @EJB
    ServicioFacade servicioCtrl;
    @EJB
    OficinaFacade oficinaCtrl;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createTurno(RestTurno rt) {

        LOG.info("Buscando Kiosco by Token {}", rt.getTokenApi());
        Kiosco k = kioscoCtrl.find(rt.getTokenApi());

        if (k == null) {
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .build();
        }

        LOG.info("Kiosco {}, creando turno ", k.getDescripcion());
        Oficina ofi = k.getIdoficina();
        int num = ofi.getSecuencia() + 1;

        // Se el numero es 100 resetiar a 1, (1-99)
        if (num == 100) {
            num = 1;
        }
        ofi.setSecuencia(num);

        // Se actualiza la secuencia de la oficina
        oficinaCtrl.edit(ofi);

        // se busca el tipo de servicio solicitado
        Servicio ser = servicioCtrl.find(rt.getServicioId());

        Turno turno = new Turno();
        turno.setFechaCreacion(new Date());
        turno.setHappyNumber(ser.getPrefijo() + "-" + num);
        turno.setPrioridad(rt.getPrioridad());
        turno.setIdestado(Estados.EN_ESPERA);
        turno.setIdcliente(new Cliente(BigDecimal.ONE));
        turno.setIdoficina(ofi);

        TurnoDetalle td = new TurnoDetalle();
        td.setIdturno(turno);
        td.setFechaInicio(turno.getFechaCreacion());
        td.setIdestado(Estados.EN_ESPERA);
        td.setIdservicio(new Servicio(rt.getServicioId()));
        td.setTiempoEspera(BigDecimal.ZERO);
        td.setTiempoProceso(BigDecimal.ZERO);

        List<TurnoDetalle> detalle = new ArrayList();

        detalle.add(td);
        turno.setTurnoDetalleList(detalle);

        turnoCtrl.create(turno);

        rt.setServicio(ser.getNombre());
        rt.setTurno(turno.getHappyNumber());
        rt.setOficina(ofi.getNombre());
        rt.setFecha(DateUtils.dateTime2String(turno.getFechaCreacion()));

        return Response.ok(rt).build();
    }

    @GET
    @Path("/kiosco/{token}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findTurnosByKiosco(@PathParam("token") String token) {

        LOG.info("Peticion toke {}", token);
        Kiosco k = kioscoCtrl.find(token);

        if (k == null) {
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .build();
        }

        LOG.info("Kiosco {} ", k.getDescripcion());
        k.setUltimaConexion(new Date());
        kioscoCtrl.edit(k);

        List<TurnoDetalle> ts = turnoCtrl.getTurnoEnProcesoByOficina(k.getIdoficina());
        RestTurno turnos[] = new RestTurno[ts.size()];
        int i = 0;
        for (TurnoDetalle td : ts) {
            turnos[i] = new RestTurno();
            turnos[i].setServicio(td.getIdservicio().getNombre());
//            turnos[i].set(td.getIdestacion().getNumeroEstacion() + "");
            turnos[i].setTurno(td.getIdturno().getHappyNumber());
            i++;
        }

        return Response.ok(turnos).build();
    }

}
