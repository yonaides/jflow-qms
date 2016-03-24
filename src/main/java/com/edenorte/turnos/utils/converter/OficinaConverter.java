/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edenorte.turnos.utils.converter;

import com.edenorte.turnos.ejb.OficinaFacade;
import com.edenorte.turnos.entity.Oficina;
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
@Named
public class OficinaConverter implements Converter {

    @EJB
    private OficinaFacade oficinaCtrl;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.matches("[0-9]+")) {
            return oficinaCtrl.find(Integer.parseInt(value));
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value == null || (value instanceof String) ? "" : ((Oficina) value).getIdoficina().toString();
    }

    public OficinaFacade getOficinaFacade() {
        return oficinaCtrl;
    }

    public void setOficinaFacade(OficinaFacade oficinaFacade) {
        this.oficinaCtrl = oficinaFacade;
    }

}
