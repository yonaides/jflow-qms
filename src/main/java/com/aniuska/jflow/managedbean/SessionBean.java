/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.managedbean;

import com.aniuska.jflow.auth.AuthenticationBean;
import com.aniuska.jflow.ejb.EstacionFacade;
import com.aniuska.jflow.ejb.SessionesFacade;
import com.aniuska.jflow.entity.Estacion;
import com.aniuska.jflow.entity.Oficina;
import com.aniuska.jflow.entity.Sessiones;
import com.aniuska.jflow.utils.Estados;
import com.edenorte.utils.MessageUtils;
import java.io.Serializable;
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
@ViewScoped
@Named
public class SessionBean implements Serializable {

    private final long serialVersionUID = 23L;

    @EJB
    private EstacionFacade estacionCtrl;
    @EJB
    private SessionesFacade sessionCtrl;
    private Estacion estacion;

    @Inject
    private AuthenticationBean authenticationBean;

    @PostConstruct
    public void init() {
        estacion = null;
    }

    public void setAuthenticationBean(AuthenticationBean authenticationBean) {
        this.authenticationBean = authenticationBean;
    }

    public void setSessionCtrl(SessionesFacade sessionCtrl) {
        this.sessionCtrl = sessionCtrl;
    }

    public void setEstacionCtrl(EstacionFacade estacionCtrl) {
        this.estacionCtrl = estacionCtrl;
    }

    public List<Estacion> getEstaciones() {
        Oficina ofi = authenticationBean.getUsuario().getIdoficina();
        return estacionCtrl.getEstacionByOficina(ofi);

    }

    public Estacion getEstacion() {
        return estacion;
    }

    public void setEstacion(Estacion estacion) {
        this.estacion = estacion;
    }

    public void salvar() {

        Sessiones sec = sessionCtrl.estaLibre(estacion);
        if (sec != null) {
            MessageUtils.sendSuccessfulMessage("Esta estación esta ocupada por : " + sec.getIdoperador().getNombre());
            return;
        }

        Sessiones session = new Sessiones();

        session.setFechaInicio(new Date());
        session.setIdestacion(estacion);
        session.setIdestado(Estados.ABIERTA);
        session.setIdoperador(authenticationBean.getUsuario());

        sessionCtrl.create(session);
        authenticationBean.setSession(session);

        MessageUtils.sendSuccessfulMessage("Nueva session iniciada");
    }

    public void buscar() {

    }

    public void cerrarSession() {

        Sessiones sec = authenticationBean.getSession();
        sec.setFechaFin(new Date());
        sec.setIdestado(Estados.CERRADA);

        sessionCtrl.edit(sec);

        authenticationBean.setSession(null);
    }

}
