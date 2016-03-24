/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edenorte.turnos.utils.converter;

import com.edenorte.turnos.ejb.TipoEstacionFacade;
import com.edenorte.turnos.entity.TipoEstacion;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

/**
 *
 * @author hventura@citrus.com.do
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
