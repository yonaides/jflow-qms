/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.managedbean;

import com.aniuska.jflow.auth.AuthenticationBean;
import com.aniuska.jflow.ejb.DashboardFacade;
import com.aniuska.jflow.entity.Usuario;
import com.aniuska.jflow.utils.Estados;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author hectorvent@gmail.com
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

    public Long getTurnoGenerados() {
        return dashboardCtrl.getCountTurnosToday(user);
    }

    public Long getTurnoAtendidos() {
        return dashboardCtrl.getCountTurnosStatus(user, Estados.ATENDIDO);
    }

    public Long getTurnoAbandonados() {
        return dashboardCtrl.getCountTurnosStatus(user, Estados.ABANDONADO);
    }

    public Long getTurnoEspera() {
        return dashboardCtrl.getCountTurnosStatus(user, Estados.EN_ESPERA);
    }

    public Double getTiempoAtencion() {
        Long tt = dashboardCtrl.getCountTurnosStatusTiempo(user, Estados.ATENDIDO, 40.0);
        Long t = getTurnoGenerados();

        if (t.intValue() == 0) {
            return 0.00;
        }

        Double d = new Double(1);
        return ((tt.doubleValue() / t.doubleValue()) * 100);
    }

    public Long getSessionesEstaciones() {
        return dashboardCtrl.getCountSessionesEstaciones(user);
    }

}
