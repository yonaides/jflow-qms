/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.managedbean;

import com.aniuska.jflow.auth.AuthenticationBean;
import com.aniuska.jflow.ejb.TurnoFacade;
import com.aniuska.jflow.entity.Oficina;
import com.aniuska.jflow.entity.Turno;
import com.aniuska.jflow.entity.TurnoDetalle;
import com.aniuska.jflow.entity.TurnoMonitoreo;
import com.aniuska.jflow.utils.Estados;
import com.aniuska.jflow.utils.TimeUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author hventura@citrus.com.do
 */
@Named
@ViewScoped
public class MonitorearTurno implements Serializable {

    private final long serialVersionUID = 23L;
    private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(MonitorearTurno.class.getName());

    @EJB
    private TurnoFacade turnoCtrl;
    @Inject
    private AuthenticationBean authenticationBean;

    @PostConstruct
    public void init() {

    }

    public void setTurnoCtrl(TurnoFacade turnoCtrl) {
        this.turnoCtrl = turnoCtrl;
    }

    public void setAuthenticationBean(AuthenticationBean authenticationBean) {
        this.authenticationBean = authenticationBean;
    }

    public List<TurnoMonitoreo> getTurnosEspera() {

        Oficina oficina = authenticationBean.getUsuario().getIdoficina();
        List<TurnoDetalle> turnos = turnoCtrl.findTurnosByOficinaAndEstado(oficina, Estados.EN_ESPERA);
        List<TurnoMonitoreo> mTurnos = new ArrayList();

        for (TurnoDetalle td : turnos) {
            TurnoMonitoreo tm = new TurnoMonitoreo();

            Turno turno = td.getIdturno();
            tm.setCliente(turno.getIdcliente().toString());
            tm.setEspecial(turno.getPrioridad() == 2);
            tm.setFecha(turno.getFechaCreacion());
            tm.setNic(turno.getIdcliente().getContrato().toString());
            tm.setTiempoEspera(TimeUtils.getDiffTimeMinutes(turno.getFechaCreacion(), new Date()));
            tm.setServicio(td.getIdservicio().getNombre());
            tm.setTurno(turno.getHappyNumber());

            mTurnos.add(tm);
        }

        Collections.sort(mTurnos, new Comparator<TurnoMonitoreo>() {
            @Override
            public int compare(TurnoMonitoreo o1, TurnoMonitoreo o2) {
                return o2.getTiempoEspera().subtract(o1.getTiempoEspera()).intValue();
            }
        });

        return mTurnos;
    }

    public List<TurnoMonitoreo> getTurnosProceso() {

        Oficina oficina = authenticationBean.getUsuario().getIdoficina();
        List<TurnoDetalle> turnos = turnoCtrl.findTurnosByOficinaAndEstado(oficina, Estados.EN_PROCESO);
        List<TurnoMonitoreo> mTurnos = new ArrayList();

        for (TurnoDetalle td : turnos) {

            TurnoMonitoreo tm = new TurnoMonitoreo();

            Turno turno = td.getIdturno();
            tm.setCliente(turno.getIdcliente().toString());
            tm.setEspecial(turno.getPrioridad() == 2);
            tm.setFecha(turno.getFechaCreacion());
            tm.setNic(turno.getIdcliente().getContrato().toString());
            tm.setTiempoEspera(td.getTiempoEspera());

            tm.setEstacion(td.getIdestacion().getNombre());

            tm.setUsuario(td.getIdoperador().toString());
            tm.setTiempoProceso(TimeUtils.getDiffTimeMinutes(td.getFechaInicioAtencion(), new Date()));
            tm.setServicio(td.getIdservicio().getNombre());
            tm.setTurno(turno.getHappyNumber());

            mTurnos.add(tm);
        }

        Collections.sort(mTurnos, new Comparator<TurnoMonitoreo>() {
            @Override
            public int compare(TurnoMonitoreo o1, TurnoMonitoreo o2) {
                return o2.getTiempoProceso().subtract(o1.getTiempoProceso()).intValue();
            }
        });

        return mTurnos;
    }

}
