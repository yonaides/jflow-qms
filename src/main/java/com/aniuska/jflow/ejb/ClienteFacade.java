/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.ejb;

import com.aniuska.jflow.entity.Cliente;
import java.math.BigInteger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hventura@citrus.com.do
 */
@Stateless
public class ClienteFacade extends AbstractFacade<Cliente> {

    @PersistenceContext(unitName = "Turnos")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }

    public Cliente findByIdentificacion(String identificacion) {

        String jql = "FROM Cliente c WHERE c.cedula = :identificacion";
        try {
            return (Cliente) em.createQuery(jql)
                    .setParameter("identificacion", identificacion)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception ex) {
        }
        return null;
    }

    public Cliente findByContrato(BigInteger nic) {

        String jql = "FROM Cliente c WHERE c.contrato = :contrato";
        try {
            return (Cliente) em.createQuery(jql)
                    .setParameter("contrato", nic)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception ex) {
        }
        return null;
    }

}
