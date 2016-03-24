/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edenorte.turnos.ejb;

import com.edenorte.turnos.entity.Estacion;
import com.edenorte.turnos.entity.Oficina;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hventura@citrus.com.do
 */
@Stateless
public class EstacionFacade extends AbstractFacade<Estacion> {

    @PersistenceContext(unitName = "Turnos")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstacionFacade() {
        super(Estacion.class);
    }

    public List<Estacion> getEstacionByOficina(Oficina oficina) {
        String jpql = "FROM Estacion e WHERE e.idoficina = :oficina";
        return em.createQuery(jpql)
                .setParameter("oficina", oficina)
                .getResultList();
    }

}
