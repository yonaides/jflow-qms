/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.ejb;

import com.aniuska.jflow.entity.Espera;
import com.aniuska.jflow.entity.Session;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hectorvent@gmail.com
 */
@Stateless
public class EsperaFacade extends AbstractFacade<Espera> {

    @PersistenceContext(unitName = "JFLOW")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EsperaFacade() {
        super(Espera.class);
    }

    public Espera findEsperaActiva(Session session) {

        String jpsql = " FROM Espera e "
                + " WHERE e.enespera = 'S' "
                + " AND e.idsession = :session";

        try {
            return (Espera) em.createQuery(jpsql)
                    .setParameter("session", session)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception ex) {
        }

        return null;
    }

    public void terminarEspera(Espera es) {
        es.setEnespera('N');
        es.setFechaFin(new Date());
        edit(es);
    }

}
