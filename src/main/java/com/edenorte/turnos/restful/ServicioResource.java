/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edenorte.turnos.restful;

import com.edenorte.turnos.ejb.KioscoFacade;
import com.edenorte.turnos.entity.Kiosco;
import com.edenorte.turnos.entity.Servicio;
import com.edenorte.turnos.restful.model.RestServicio;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.GET;
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
@Path("servicio")
public class ServicioResource {

    private static final Logger LOG = LogManager.getLogger(ServicioResource.class);
    @EJB
    KioscoFacade ctrlKiosco;

    @GET
    @Path("/kiosco/{token}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findServicioByKiosco(@PathParam("token") String token) {

        LOG.info("Peticion token {}", token);
        Kiosco k = ctrlKiosco.find(token);

        if (k == null) {
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .build();
        }

        LOG.info("Kiosco {} id ", k.getDescripcion());

        List<Servicio> sers = k.getIdoficina().getServicioList();
        RestServicio servicios[] = new RestServicio[sers.size()];
        int i = 0;
        for (Servicio servicio : sers) {
            servicios[i] = new RestServicio();
            servicios[i].setNombre(servicio.getNombre());
            servicios[i].setServicioId(servicio.getIdservicio());
            servicios[i].setDescripcion(servicio.getDescripcion());
            i++;
        }

        return Response.ok(servicios).build();
    }

}
