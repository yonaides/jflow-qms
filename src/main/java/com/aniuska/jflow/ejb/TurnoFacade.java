/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.ejb;

import com.aniuska.jflow.entity.Estado;
import com.aniuska.jflow.entity.Oficina;
import com.aniuska.jflow.entity.Sessiones;
import com.aniuska.jflow.entity.Turno;
import com.aniuska.jflow.entity.TurnoDetalle;
import com.aniuska.jflow.utils.Estados;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hventura@citrus.com.do
 */
@Stateless
public class TurnoFacade extends AbstractFacade<Turno> {

    @PersistenceContext(unitName = "Turnos")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TurnoFacade() {
        super(Turno.class);
    }

    /**
     *
     * @param session, Sesscion en una estacion especifica en una oficina por un
     * usuario
     * @return turno, siquiente para la session especificada
     */
    public TurnoDetalle nextTurnoDetalle(Sessiones session) {

        String jpql = "SELECT TD.* FROM TURNO T INNER JOIN TURNO_DETALLE TD "
                + "ON (T.IDTURNO = TD.IDTURNO) INNER JOIN ESTACION_SERVICIO ES "
                + "ON (TD.IDSERVICIO = ES.IDSERVICIO AND ES.IDESTACION = ?1) "
                + "WHERE TD.IDESTADO = ?2 AND T.IDOFICINA = ?3 ORDER BY T.PRIORIDAD DESC, "
                + "ES.PRIORITARIO DESC, T.IDTURNO";

        try {
            return (TurnoDetalle) em.createNativeQuery(jpql, TurnoDetalle.class)
                    .setParameter(1, session.getIdestacion().getIdestacion())
                    .setParameter(2, Estados.EN_ESPERA.getIdestado())
                    .setParameter(3, session.getIdoperador().getIdoficina().getIdoficina())
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception ex) {
        }

        return null;
    }

    public TurnoDetalle turnoPendienteDetalle(Sessiones session) {

        String jpql = "SELECT TD.* FROM TURNO T INNER JOIN TURNO_DETALLE TD "
                + "ON (T.IDTURNO = TD.IDTURNO) "
                + "WHERE TD.IDESTADO = ?1 "
                + "AND TD.IDOPERADOR = ?2 "
                + "ORDER BY T.PRIORIDAD DESC, TD.IDTURNO_DETALLE";

        try {
            return (TurnoDetalle) em.createNativeQuery(jpql, TurnoDetalle.class)
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
     * @param oficina
     * @return
     */
    public List<TurnoDetalle> getTurnoEnProcesoByOficina(Oficina oficina) {
        String jpql = "SELECT TD.* FROM TURNO T INNER JOIN TURNO_DETALLE TD "
                + "ON (T.IDTURNO = TD.IDTURNO) WHERE T.IDOFICINA = ?1 "
                + "AND TD.IDESTADO = ?2 ORDER BY TD.FECHA_INICIO_ATENCION DESC";

        return em.createNativeQuery(jpql, TurnoDetalle.class)
                .setParameter(1, oficina.getIdoficina())
                .setParameter(2, Estados.EN_PROCESO.getIdestado())
                .getResultList();
    }

    public List<Turno> findLast10(Oficina oficina) {
        String jpql = "SELECT T.* "
                + "FROM (SELECT T.* FROM TURNO T "
                + "WHERE T.IDOFICINA = ?1 "
                + "ORDER BY T.IDTURNO DESC) T "
                + "WHERE ROWNUM <= 5";

        return em.createNativeQuery(jpql, Turno.class)
                .setParameter(1, oficina.getIdoficina())
                .getResultList();
    }

    public List<TurnoDetalle> findTurnosByOficinaAndEstado(Oficina oficina, Estado estado) {
        String jpql = "SELECT TD.* "
                + "FROM TURNO_DETALLE TD INNER JOIN TURNO T ON (T.IDTURNO = TD.IDTURNO) "
                + "WHERE T.IDOFICINA = ?1 AND TD.IDESTADO = ?2";

        return em.createNativeQuery(jpql, TurnoDetalle.class)
                .setParameter(1, oficina.getIdoficina())
                .setParameter(2, estado.getIdestado())
                .setMaxResults(10)
                .getResultList();
    }

    /**
     * Este metodo es para llamar todos los turnos en espera
     *
     * @return
     */
    public List<Turno> findTurnosAbiertos() {
        String jpql = "FROM Turno t "
                + "WHERE t.idestado = :estado1 OR t.idestado = :estado2";

        return em.createQuery(jpql, Turno.class)
                .setParameter("estado1", Estados.EN_ESPERA)
                .setParameter("estado2", Estados.EN_PROCESO)
                .getResultList();
    }

}
