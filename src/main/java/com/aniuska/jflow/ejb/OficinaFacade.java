/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.ejb;

import com.aniuska.jflow.entity.Oficina;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hventura@citrus.com.do
 */
@Stateless
public class OficinaFacade extends AbstractFacade<Oficina> {

    @PersistenceContext(unitName = "Turnos")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OficinaFacade() {
        super(Oficina.class);
    }

    public Oficina findOficinaByNumero(Integer numOficina) {
        try {
            return (Oficina) em.createQuery("FROM Oficina o WHERE o.numeroOficina = :numOficina")
                    .setParameter("numOficina", numOficina)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }

    public List<Oficina> findAll(String busqueda) {
        return em.createNativeQuery("SELECT O.* "
                + "FROM OFICINA O "
                + " WHERE LOWER(O.NOMBRE) LIKE ?1 ", Oficina.class)
                .setParameter(1, "%" + busqueda.toLowerCase().trim() + "%")
                .getResultList();

    }

}
