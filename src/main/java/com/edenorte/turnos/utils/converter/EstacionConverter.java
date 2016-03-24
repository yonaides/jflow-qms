/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edenorte.turnos.utils.converter;

import com.edenorte.turnos.ejb.EstacionFacade;
import com.edenorte.turnos.entity.Estacion;
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
@Named("estacionConverter")
public class EstacionConverter implements Converter {

    @EJB
    private EstacionFacade estacionCtrl;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.matches("[0-9]+")) {
            return estacionCtrl.find(Integer.parseInt(value));
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value == null || (value instanceof String) ? null : ((Estacion) value).getIdestacion().toString();
    }

    public EstacionFacade getEstacionCtrl() {
        return estacionCtrl;
    }

    public void setEstacionCtrl(EstacionFacade estacionCtrl) {
        this.estacionCtrl = estacionCtrl;
    }

}
