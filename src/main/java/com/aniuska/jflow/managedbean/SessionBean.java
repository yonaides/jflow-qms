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
import com.aniuska.jflow.entity.Sucursal;
import com.aniuska.jflow.entity.Session;
import com.aniuska.jflow.utils.Estados;
import com.aniuska.utils.MessageUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author hectorvent@gmail.com
 */
@ViewScoped
@Named
public class SessionBean implements Serializable {

    private final long serialVersionUID = 23L;

    @EJB
    EstacionFacade estacionCtrl;

    @EJB
    SessionesFacade sessionCtrl;

    @Inject
    AuthenticationBean authenticationBean;
    private Estacion estacion;

    @PostConstruct
    public void init() {
        estacion = null;
    }

    public List<Estacion> getEstaciones() {
        Sucursal ofi = authenticationBean.getUsuario().getIdsucursal();
        return estacionCtrl.getEstacionByOficina(ofi);
    }

    public Estacion getEstacion() {
        return estacion;
    }

    public void setEstacion(Estacion estacion) {
        this.estacion = estacion;
    }

    public void salvar() {

        Session sec = sessionCtrl.estaLibre(estacion);
        if (sec != null) {
            MessageUtils.sendSuccessfulMessage("Esta estación esta ocupada por : "
                    + sec.getIdoperador().getNombre());
            return;
        }

        Session session = new Session();
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

    public void doClose() {
        System.out.println("cerrando session ");

        Session sec = authenticationBean.getSession();
        sec.setFechaFin(new Date());
        sec.setIdestado(Estados.CERRADA);

        System.out.println("sec = " + sec);

        sessionCtrl.edit(sec);
        authenticationBean.setSession(null);
    }

    

}
