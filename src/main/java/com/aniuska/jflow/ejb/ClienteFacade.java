/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.ejb;

import com.aniuska.jflow.entity.Cliente;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hectorvent@gmail.com
 */
@Stateless
public class ClienteFacade extends AbstractFacade<Cliente> {

    private static final Logger LOG = Logger.getLogger(ClienteFacade.class.getName());

    @PersistenceContext(unitName = "JFLOW")
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
            LOG.log(Level.INFO, "Error al realizar la consulta {0}", ex);
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
            LOG.log(Level.INFO, "Error al realizar la consulta {0}", ex);
        }
        return null;
    }

}
