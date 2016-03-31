/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.ejb;

import com.aniuska.jflow.entity.Estacion;
import com.aniuska.jflow.entity.Sucursal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hectorvent@gmail.com
 */
@Stateless
public class EstacionFacade extends AbstractFacade<Estacion> {

    @PersistenceContext(unitName = "JFLOW")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstacionFacade() {
        super(Estacion.class);
    }

    public List<Estacion> getEstacionByOficina(Sucursal sucursal) {
        String jpql = "FROM Estacion e WHERE e.idsucursal = :sucursal";
        return em.createQuery(jpql)
                .setParameter("sucursal", sucursal)
                .getResultList();
    }

}
