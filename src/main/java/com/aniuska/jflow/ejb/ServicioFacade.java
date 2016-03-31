/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.ejb;

import com.aniuska.jflow.entity.Sucursal;
import com.aniuska.jflow.entity.Servicio;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hectorvent@gmail.com
 */
@Stateless
public class ServicioFacade extends AbstractFacade<Servicio> {

    @PersistenceContext(unitName = "JFLOW")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServicioFacade() {
        super(Servicio.class);
    }

    public List<Servicio> findBySucursal(Sucursal sucursal) {
        return em.createNativeQuery("SELECT S.* "
                + "FROM SERVICIO S, SUCURSAL_SERVICIO SS "
                + "WHERE S.IDSERVICIO = SS.IDSERVICIO "
                + "  AND SS.IDSUCURSAL = ?1", Servicio.class)
                .setParameter(1, sucursal.getIdsucursal())
                .getResultList();
    }

}
