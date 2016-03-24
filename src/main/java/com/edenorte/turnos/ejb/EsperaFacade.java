/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edenorte.turnos.ejb;

import com.edenorte.turnos.entity.Espera;
import com.edenorte.turnos.entity.Sessiones;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hventura@citrus.com.do
 */
@Stateless
public class EsperaFacade extends AbstractFacade<Espera> {

    @PersistenceContext(unitName = "Turnos")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EsperaFacade() {
        super(Espera.class);
    }

    public Espera findEsperaActiva(Sessiones session) {

        String jpsql = "FROM Espera e "
                + "WHERE e.enespera = 'S' AND e.idsession = :session";

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
