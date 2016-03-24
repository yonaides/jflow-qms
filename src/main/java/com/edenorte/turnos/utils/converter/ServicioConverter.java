/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edenorte.turnos.utils.converter;

import com.edenorte.turnos.ejb.ServicioFacade;
import com.edenorte.turnos.entity.Servicio;
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
@Named("servicioConverter")
public class ServicioConverter implements Converter {

    @EJB
    private ServicioFacade servicioFacade;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.matches("[0-9]+")) {
            return servicioFacade.find(Integer.parseInt(value));
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value == null || (value instanceof String) ? "" : ((Servicio) value).getIdservicio().toString();
    }

    public ServicioFacade getServicioFacade() {
        return servicioFacade;
    }

    public void setServicioFacade(ServicioFacade servicioFacade) {
        this.servicioFacade = servicioFacade;
    }

}
