/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.ejb;

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
public class SucursalFacade extends AbstractFacade<Sucursal> {

    @PersistenceContext(unitName = "JFLOW")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SucursalFacade() {
        super(Sucursal.class);
    }

    public Sucursal findSucursalByNumero(Integer numSucursal) {
        try {
            return (Sucursal) em.createQuery("FROM Sucursal o WHERE o.numeroSucursal = :numSucursal")
                    .setParameter("numSucursal", numSucursal)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }

    public List<Sucursal> findAll(String busqueda) {
        return em.createNativeQuery("SELECT O.* "
                + "FROM SUCURSAL O "
                + " WHERE LOWER(O.NOMBRE) LIKE ?1 ", Sucursal.class)
                .setParameter(1, "%" + busqueda.toLowerCase().trim() + "%")
                .getResultList();

    }

}
