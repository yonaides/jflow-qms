/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.utils.converter;

import com.aniuska.jflow.ejb.TipoEstacionFacade;
import com.aniuska.jflow.entity.TipoEstacion;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

/**
 *
 * @author hectorvent@gmail.com
 */
@RequestScoped
@Named("tipoEstacionConverter")
public class TipoEstacionConverter implements Converter {

    @EJB
    private TipoEstacionFacade tipoEstacionCtrl;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.matches("[0-9]+")) {
            return tipoEstacionCtrl.find(Integer.parseInt(value));
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value == null || (value instanceof String) ? null : ((TipoEstacion) value).getIdtipoEstacion().toString();
    }

    public void setTipoEstacionCtrl(TipoEstacionFacade tipoEstacionCtrl) {
        this.tipoEstacionCtrl = tipoEstacionCtrl;
    }

    

}
