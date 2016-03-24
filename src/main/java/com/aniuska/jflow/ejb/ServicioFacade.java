/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.ejb;

import com.aniuska.jflow.entity.Oficina;
import com.aniuska.jflow.entity.Servicio;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hventura@citrus.com.do
 */
@Stateless
public class ServicioFacade extends AbstractFacade<Servicio> {

    @PersistenceContext(unitName = "Turnos")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServicioFacade() {
        super(Servicio.class);
    }

    public List<Servicio> findByOficina(Oficina oficina) {
        return em.createNativeQuery("SELECT S.* "
                + "FROM SERVICIO S, OFICINA_SERVICIO OS "
                + "WHERE S.IDSERVICIO = OS.IDSERVICIO "
                + "  AND OS.IDOFICINA = ?1", Servicio.class)
                .setParameter(1, oficina.getIdoficina())
                .getResultList();
    }

}
