/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.managedbean;

import com.aniuska.jflow.ejb.MotivoAbandonoFacade;
import com.aniuska.jflow.ejb.MotivoRecesoFacade;
import com.aniuska.jflow.entity.MotivoAbandono;
import com.aniuska.jflow.entity.MotivoReceso;
import com.aniuska.utils.MessageUtils;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author hectorvent@gmail.com
 */
@ViewScoped
@Named
public class GenericoBean implements Serializable {

    private final long serialVersionUID = 23L;

    @EJB
    MotivoAbandonoFacade motivoAbandonoCtrl;
    @EJB
    MotivoRecesoFacade motivoRecesoCtrl;
    private MotivoAbandono motivoAbandono;
    private MotivoReceso motivoReceso;

    @PostConstruct
    public void init() {
        motivoAbandono = new MotivoAbandono();
        motivoReceso = new MotivoReceso();
    }

    public MotivoAbandono getMotivoAbandono() {
        return motivoAbandono;
    }

    public void setMotivoAbandono(MotivoAbandono motivoAbandono) {
        this.motivoAbandono = motivoAbandono;
    }

    public List<MotivoAbandono> getMotivoAbandonos() {
        return motivoAbandonoCtrl.findAll();
    }

    public MotivoReceso getMotivoReceso() {
        return motivoReceso;
    }

    public void setMotivoReceso(MotivoReceso motivoReceso) {
        this.motivoReceso = motivoReceso;
    }

    public List<MotivoReceso> getMotivoRecesos() {
        return motivoRecesoCtrl.findAll();
    }

    public void salvarMotivoAbandono() {

        if (motivoAbandono.getIdmotivoAbandono() == null) {
            motivoAbandonoCtrl.create(motivoAbandono);
        } else {
            motivoAbandonoCtrl.edit(motivoAbandono);
        }
        motivoAbandono = new MotivoAbandono();
        MessageUtils.sendSuccessfulMessage("Nuevo motivo creado");
    }

    public void salvarMotivoReceso() {

        if (motivoReceso.getIdmotivoReceso() == null) {
            motivoRecesoCtrl.create(motivoReceso);
        } else {
            motivoRecesoCtrl.edit(motivoReceso);
        }
        motivoReceso = new MotivoReceso();
        MessageUtils.sendSuccessfulMessage("Nuevo motivo creado");
    }

    public void salvar() {
//
//        Sessiones sec = sessionCtrl.estaLibre(estacion);
//        if (sec != null) {
//            MessageUtils.sendSuccessfulMessage("Esta estación esta ocupada por : " + sec.getIdoperador().getNombre());
//            return;
//        }
//
//        Sessiones session = new Sessiones();
//
//        session.setFechaInicio(new Date());
//        session.setIdestacion(estacion);
//        session.setIdestado(Estados.ABIERTA);
//        session.setIdoperador(authenticationBean.getUsuario());
//
//        sessionCtrl.create(session);
//        authenticationBean.setSession(session);
//
//        MessageUtils.sendSuccessfulMessage("Nueva session iniciada");
    }

}
