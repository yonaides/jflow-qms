/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.ejb;

import com.aniuska.jflow.entity.Estado;
import com.aniuska.jflow.entity.Sucursal;
import com.aniuska.jflow.entity.Session;
import com.aniuska.jflow.entity.Ticket;
import com.aniuska.jflow.entity.TicketDetalle;
import com.aniuska.jflow.utils.Estados;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hectorvent@gmail.com
 */
@Stateless
public class TicketFacade extends AbstractFacade<Ticket> {
    
    @PersistenceContext(unitName = "JFLOW")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public TicketFacade() {
        super(Ticket.class);
    }

    /**
     *
     * @param session, Sesscion en una estacion especifica en una sucursal por
     * un usuario
     * @return turno, siquiente para la session especificada
     */
    public TicketDetalle nextTurnoDetalle(Session session) {
        
        String jpql = "SELECT TD.* FROM TICKET T INNER JOIN TICKET_DETALLE TD "
                + "ON (T.IDTICKET = TD.IDTICKET) INNER JOIN ESTACION_SERVICIO ES "
                + "ON (TD.IDSERVICIO = ES.IDSERVICIO AND ES.IDESTACION = ?1) "
                + "WHERE TD.IDESTADO = ?2 AND T.IDSUCURSAL = ?3 ORDER BY T.PRIORIDAD DESC, "
                + "ES.PRIORITARIO DESC, T.IDTICKET";
        
        try {
            return (TicketDetalle) em.createNativeQuery(jpql, TicketDetalle.class)
                    .setParameter(1, session.getIdestacion().getIdestacion())
                    .setParameter(2, Estados.EN_ESPERA.getIdestado())
                    .setParameter(3, session.getIdoperador().getIdsucursal().getIdsucursal())
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception ex) {
        }
        
        return null;
    }
    
    public TicketDetalle turnoPendienteDetalle(Session session) {
        
        String jpql = "SELECT TD.* FROM TICKET T INNER JOIN TICKET_DETALLE TD "
                + "ON (T.IDTICKET = TD.IDTICKET) "
                + "WHERE TD.IDESTADO = ?1 "
                + "AND TD.IDOPERADOR = ?2 "
                + "ORDER BY T.PRIORIDAD DESC, TD.IDTICKET_DETALLE";
        
        try {
            return (TicketDetalle) em.createNativeQuery(jpql, TicketDetalle.class)
                    .setParameter(1, Estados.EN_PROCESO.getIdestado())
                    .setParameter(2, session.getIdoperador().getIdoperador())
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception ex) {
        }
        
        return null;
    }

    /**
     * Use this method for Resource (Rest)
     *
     * @param sucursal
     * @return
     */
    public List<TicketDetalle> getTurnoEnProcesoBySucursal(Sucursal sucursal) {
        String jpql = "SELECT TD.* FROM TICKET T INNER JOIN TICKET_DETALLE TD "
                + "ON (T.IDTICKET = TD.IDTICKET) WHERE T.IDSUCURSAL = ?1 "
                + "AND TD.IDESTADO = ?2 ORDER BY TD.FECHA_INICIO_ATENCION DESC";
        
        return em.createNativeQuery(jpql, TicketDetalle.class)
                .setParameter(1, sucursal.getIdsucursal())
                .setParameter(2, Estados.EN_PROCESO.getIdestado())
                .getResultList();
    }
    
    public List<Ticket> findLast10(Sucursal sucursal) {
        String jpql = "SELECT T.* "
                + "FROM (SELECT T.* FROM TICKET T "
                + "WHERE T.IDSUCURSAL = ?1 "
                + "ORDER BY T.IDTICKET DESC) T "
                + "LIMIT 10";
        
        List<Ticket> listado = em.createNativeQuery(jpql, Ticket.class)
                .setParameter(1, sucursal.getIdsucursal())
                .getResultList();
        
        listado.forEach((item) -> {
            
            System.out.println("item = " + item);
        });
        
        return listado;
        
    }
    
    public List<TicketDetalle> findTurnosBySucursalAndEstado(Sucursal sucursal, Estado estado) {
        String jpql = "SELECT TD.* "
                + "FROM TICKET_DETALLE TD INNER JOIN TICKET T ON (T.IDTICKET = TD.IDTICKET) "
                + "WHERE T.IDSUCURSAL = ?1 AND TD.IDESTADO = ?2";
        
        return em.createNativeQuery(jpql, TicketDetalle.class)
                .setParameter(1, sucursal.getIdsucursal())
                .setParameter(2, estado.getIdestado())
                .setMaxResults(10)
                .getResultList();
    }

    /**
     * Este metodo es para llamar todos los turnos en espera
     *
     * @return
     */
    public List<Ticket> findTurnosAbiertos() {
        String jpql = "FROM Ticket t "
                + "WHERE t.idestado = :estado1 OR t.idestado = :estado2";
        
        return em.createQuery(jpql, Ticket.class)
                .setParameter("estado1", Estados.EN_ESPERA)
                .setParameter("estado2", Estados.EN_PROCESO)
                .getResultList();
    }
    
}
