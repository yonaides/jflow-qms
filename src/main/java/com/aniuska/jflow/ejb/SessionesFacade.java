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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author hectorvent@gmail.com
 */
@Stateless
public class SessionesFacade extends AbstractFacade<Session> {

    private static final Logger LOG = Logger.getLogger(SessionesFacade.class.getName());

    @PersistenceContext(unitName = "JFLOW")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SessionesFacade() {
        super(Session.class);
    }
    
    public Session isOpenSession(Usuario usuario){
        
        Session session = null;
        
        String jpql = "FROM Session s "
                + "WHERE s.idestado = :estado AND s.idoperador = :usuario";
        
        try {
           Query query =  em.createQuery(jpql,Session.class)
                    .setParameter("estado", Estados.ABIERTA)
                    .setParameter("usuario", usuario);
            
           session = (Session) query.getResultList().stream().findFirst().orElse(null);
           
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error al abrir al session del usuario ", e);
        }
        return session;
        
    }
    
/*
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
            LOG.log(Level.SEVERE, "Error al abrir al session del usuario ", ex);
        }

        return null;
        
        
    }
*/
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
            LOG.log(Level.SEVERE, "Error al revisar la estacion libre ", ex);
        }

        return null;
    }

}
