/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.restful;

import com.aniuska.jflow.ejb.DispositivoFacade;
import com.aniuska.jflow.entity.Dispositivo;
import com.aniuska.jflow.entity.Servicio;
import com.aniuska.jflow.restful.model.RestServicio;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author hectorvent@gmail.com
 */
@Path("servicio")
public class ServicioResource {

    private static final Logger LOG = LogManager.getLogger(ServicioResource.class);
    @EJB
    DispositivoFacade ctrlKiosco;

    /**
     *
     * @param tokenApi
     * @return Response
     */
    @GET
    @Path("/kiosco/{tokenApi}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findServicioByKiosco(@PathParam("tokenApi") String tokenApi) {

        LOG.info("Peticion token {}", tokenApi);
        Dispositivo k = ctrlKiosco.find(tokenApi);

        if (k == null) {
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .build();
        }

        LOG.info("Kiosco {} id ", k.getDescripcion());

        List<Servicio> sers = k.getIdsucursal().getServicioList();

        LOG.info(" Datos de busqueda ", sers.get(0));
        LOG.info(sers.get(0).toString());

        //RestServicio servicios[] = new RestServicio[0] ;
        RestServicio servicio = new RestServicio();
        
        sers.forEach((item)->
        {
            
            servicio.setDescripcion(item.getDescripcion());
            servicio.setNombre(item.getNombre());
            servicio.setServicioId(item.getIdservicio());

        });
            /*servicios[0].setDescripcion(servicio.getDescripcion());
            servicios[0].setNombre(servicio.getNombre());
            servicios[0].setServicioId(servicio.getServicioId());*/
        
        return Response.ok(Entity.json(servicio) ).build() ;
        
        /*RestServicio servicios[] = (RestServicio[]) sers.stream()
                .map(r -> {
                    RestServicio servicio = new RestServicio();
                    servicio.setNombre(r.getNombre());
                    servicio.setServicioId(r.getIdservicio());
                    servicio.setDescripcion(r.getDescripcion());
                    return servicio;
                }).toArray();
         */
        //return Response.ok(servicios).build();
        //return Response.ok(Response.accepted()).build();
    }

}
