/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edenorte.turnos.utils.converter;

import com.edenorte.turnos.ejb.MotivoRecesoFacade;
import com.edenorte.turnos.entity.MotivoReceso;
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
@Named("motivoRecesoConverter")
@RequestScoped
public class MotivoRecesoConverter implements Converter {

    @EJB
    private MotivoRecesoFacade motivoRecesoCtrl;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.matches("[0-9]+")) {
            System.out.println("ID MOTIVO  =  " + value);
            return motivoRecesoCtrl.find(Integer.parseInt(value));
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value == null || (value instanceof String) ? null : ((MotivoReceso) value).getIdmotivoReceso().toString();
    }

    public void setMotivoRecesoCtrl(MotivoRecesoFacade motivoRecesoCtrl) {
        this.motivoRecesoCtrl = motivoRecesoCtrl;
    }

}
