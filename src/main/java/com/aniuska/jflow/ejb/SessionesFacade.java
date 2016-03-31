/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.ejb;

import com.aniuska.jflow.entity.Estacion;
import com.aniuska.jflow.entity.Session;
import com.aniuska.jflow.entity.Usuario;
import com.aniuska.jflow.utils.Estados;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hectorvent@gmail.com
 */
@Stateless
public class SessionesFacade extends AbstractFacade<Session> {

    @PersistenceContext(unitName = "JFLOW")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SessionesFacade() {
        super(Session.class);
    }

    public Session getOpenSesssion(Usuario usuario) {
        String jpql = "FROM Session s "
                + "WHERE s.idestado = :estado AND s.idoperador = :usuario";

        try {
            return (Session) em.createQuery(jpql)
                    .setParameter("estado", Estados.ABIERTA)
                    .setParameter("usuario", usuario)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception ex) {
        }

        return null;
    }

    public Session estaLibre(Estacion estacion) {
        String jpql = "FROM Session s "
                + "WHERE s.idestacion = :estacion AND s.idestado = :estado";

        try {
            Object obj = em.createQuery(jpql)
                    .setParameter("estado", Estados.ABIERTA)
                    .setParameter("estacion", estacion)
                    .getSingleResult();

            return (Session) obj;
        } catch (Exception ex) {
        }

        return null;
    }

}
