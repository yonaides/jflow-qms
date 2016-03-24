/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edenorte.turnos.managedbean;

import com.edenorte.turnos.auth.AuthenticationBean;
import com.edenorte.turnos.ejb.DashboardFacade;
import com.edenorte.turnos.entity.Usuario;
import com.edenorte.turnos.utils.Estados;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author hventura@citrus.com.do
 */
@Named
@ViewScoped
public class DashboardBean implements Serializable {

    @EJB
    private DashboardFacade dashboardCtrl;
    @Inject
    private AuthenticationBean authBean;
    private Usuario user;

    @PostConstruct
    public void init() {
        user = authBean.getUsuario();
    }

    public void setDashboardCtrl(DashboardFacade dashboardCtrl) {
        this.dashboardCtrl = dashboardCtrl;
    }

    public void setAuthBean(AuthenticationBean authBean) {
        this.authBean = authBean;
    }

    public BigDecimal getTurnoGenerados() {
        return dashboardCtrl.getCountTurnosToday(user);
    }

    public BigDecimal getTurnoAtendidos() {
        return dashboardCtrl.getCountTurnosStatus(user, Estados.ATENDIDO);
    }

    public BigDecimal getTurnoAbandonados() {
        return dashboardCtrl.getCountTurnosStatus(user, Estados.ABANDONADO);
    }

    public BigDecimal getTurnoEspera() {
        return dashboardCtrl.getCountTurnosStatus(user, Estados.EN_ESPERA);
    }

    public Double getTiempoAtencion() {
        BigDecimal tt = dashboardCtrl.getCountTurnosStatusTiempo(user, Estados.ATENDIDO, 40.0);
        BigDecimal t = getTurnoGenerados();

        if (t.intValue() == 0) {
            return 0.00;
        }

        Double d = new Double(1);
        return ((tt.doubleValue() / t.doubleValue()) * 100);
    }

    public BigDecimal getSessionesEstaciones() {
        return dashboardCtrl.getCountSessionesEstaciones(user);
    }

}
