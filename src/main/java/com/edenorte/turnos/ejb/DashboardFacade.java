/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edenorte.turnos.ejb;

import com.edenorte.turnos.entity.Estado;
import com.edenorte.turnos.entity.Usuario;
import com.edenorte.turnos.utils.Estados;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hventura@citrus.com.do
 */
@Stateless
public class DashboardFacade extends AbstractFacade<Object> {

    @PersistenceContext(unitName = "Turnos")
    private EntityManager em;

    private static final SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DashboardFacade() {
        super(Object.class);
    }

    public BigDecimal getCountTurnosToday(Usuario usuario) {
        return (BigDecimal) em.createNativeQuery("SELECT COUNT(*) "
                + "FROM TURNO T WHERE TO_CHAR(T.FECHA_CREACION, 'DD/MM/YYYY') = ?1 "
                + "AND T.IDOFICINA = ?2")
                .setParameter(1, df.format(new Date()))
                .setParameter(2, usuario.getIdoficina().getIdoficina())
                .getSingleResult();
    }

    public BigDecimal getCountTurnosStatus(Usuario usuario, Estado estado) {
        return (BigDecimal) em.createNativeQuery("SELECT COUNT(*) "
                + "FROM TURNO T INNER JOIN TURNO_DETALLE TD ON (T.IDTURNO = TD.IDTURNO) "
                + "WHERE TO_CHAR(T.FECHA_CREACION, 'DD/MM/YYYY') = ?1 "
                + "AND T.IDOFICINA = ?2 AND TD.IDESTADO = ?3")
                .setParameter(1, df.format(new Date()))
                .setParameter(2, usuario.getIdoficina().getIdoficina())
                .setParameter(3, estado.getIdestado())
                .getSingleResult();
    }

    public BigDecimal getCountTurnosStatusTiempo(Usuario usuario, Estado estado, Double tiempo) {
        return (BigDecimal) em.createNativeQuery("SELECT COUNT(*) "
                + "FROM TURNO T INNER JOIN TURNO_DETALLE TD ON (T.IDTURNO = TD.IDTURNO) "
                + "WHERE TO_CHAR(T.FECHA_CREACION, 'DD/MM/YYYY') = ?1 "
                + "AND T.IDOFICINA = ?2 AND TD.IDESTADO = ?3 "
                + "AND TD.TIEMPO_ESPERA+TD.TIEMPO_PROCESO < ?4 AND TD.TIEMPO_ESPERA>0")
                .setParameter(1, df.format(new Date()))
                .setParameter(2, usuario.getIdoficina().getIdoficina())
                .setParameter(3, estado.getIdestado())
                .setParameter(4, tiempo)
                .getSingleResult();
    }

    public BigDecimal getCountSessionesEstaciones(Usuario usuario) {
        return (BigDecimal) em.createNativeQuery("SELECT COUNT (*) "
                + "FROM SESSIONES S INNER JOIN USUARIO U ON (S.IDOPERADOR = U.IDOPERADOR) "
                + "WHERE U.IDOFICINA = ?1 AND S.IDESTADO = ?2")
                .setParameter(1, usuario.getIdoficina().getIdoficina())
                .setParameter(2, Estados.ABIERTA.getIdestado())
                .getSingleResult();
    }

}
