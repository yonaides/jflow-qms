/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.ejb;

import com.aniuska.jflow.entity.Estado;
import com.aniuska.jflow.entity.Usuario;
import com.aniuska.jflow.utils.Estados;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hectorvent@gmail.com
 */
@Stateless
public class DashboardFacade extends AbstractFacade<Object> {

    @PersistenceContext(unitName = "JFLOW")
    private EntityManager em;

    private static final SimpleDateFormat DF = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DashboardFacade() {
        super(Object.class);
    }

    public Long getCountTurnosToday(Usuario usuario) {
        return (Long) em.createNativeQuery("SELECT COUNT(*) "
                + "FROM TICKET T WHERE TO_CHAR(T.FECHA_CREACION, 'DD/MM/YYYY') = ?1 "
                + "AND T.IDSUCURSAL = ?2")
                .setParameter(1, DF.format(new Date()))
                .setParameter(2, usuario.getIdsucursal().getIdsucursal())
                .getSingleResult();
    }

    public Long getCountTurnosStatus(Usuario usuario, Estado estado) {
        return (Long) em.createNativeQuery("SELECT COUNT(*) "
                + "FROM TICKET T INNER JOIN TICKET_DETALLE TD ON (T.IDTICKET = TD.IDTICKET) "
                + "WHERE TO_CHAR(T.FECHA_CREACION, 'DD/MM/YYYY') = ?1 "
                + "AND T.IDSUCURSAL = ?2 AND TD.IDESTADO = ?3")
                .setParameter(1, DF.format(new Date()))
                .setParameter(2, usuario.getIdsucursal().getIdsucursal())
                .setParameter(3, estado.getIdestado())
                .getSingleResult();
    }

    public Long getCountTurnosStatusTiempo(Usuario usuario, Estado estado, Double tiempo) {
        return (Long) em.createNativeQuery("SELECT COUNT(*) "
                + "FROM TICKET T INNER JOIN TICKET_DETALLE TD ON (T.IDTICKET = TD.IDTICKET) "
                + "WHERE TO_CHAR(T.FECHA_CREACION, 'DD/MM/YYYY') = ?1 "
                + "AND T.IDTICKET = ?2 AND TD.IDESTADO = ?3 "
                + "AND TD.TIEMPO_ESPERA+TD.TIEMPO_PROCESO < ?4 AND TD.TIEMPO_ESPERA>0")
                .setParameter(1, DF.format(new Date()))
                .setParameter(2, usuario.getIdsucursal().getIdsucursal())
                .setParameter(3, estado.getIdestado())
                .setParameter(4, tiempo)
                .getSingleResult();
    }

    public Long getCountSessionesEstaciones(Usuario usuario) {
        return (Long) em.createNativeQuery("SELECT COUNT (*) "
                + "FROM SESSION S INNER JOIN USUARIO U ON (S.IDOPERADOR = U.IDOPERADOR) "
                + "WHERE U.IDSUCURSAL = ?1 AND S.IDESTADO = ?2")
                .setParameter(1, usuario.getIdsucursal().getIdsucursal())
                .setParameter(2, Estados.ABIERTA.getIdestado())
                .getSingleResult();
    }

}
