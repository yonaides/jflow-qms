/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.managedbean;

import com.aniuska.jflow.ejb.TipoEstacionFacade;
import com.aniuska.jflow.entity.TipoEstacion;
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
public class TipoEstacionBean implements Serializable {

    private final long serialVersionUID = 28L;

    @EJB
    TipoEstacionFacade tipoEstacionCtrl;
    private TipoEstacion tipoEstacion;

    @PostConstruct
    public void init() {
        tipoEstacion = new TipoEstacion();
    }

    public TipoEstacion getTipoEstacion() {
        return tipoEstacion;
    }

    public void setTipoEstacion(TipoEstacion tipoEstacion) {
        this.tipoEstacion = tipoEstacion;
    }

    public List<TipoEstacion> getTipoEstaciones() {
        return tipoEstacionCtrl.findAll();
    }

    public void salvar() {

        if (tipoEstacion.getIdtipoEstacion() == null) {
            tipoEstacionCtrl.create(tipoEstacion);
            MessageUtils.sendSuccessfulMessage("Nuevo tipo estación guardada");
        } else {
            tipoEstacionCtrl.edit(tipoEstacion);
            MessageUtils.sendSuccessfulMessage("Tipo estación actualizada");
        }

        init();
    }

}
