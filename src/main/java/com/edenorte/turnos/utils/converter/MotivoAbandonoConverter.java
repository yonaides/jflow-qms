/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edenorte.turnos.utils.converter;

import com.edenorte.turnos.ejb.MotivoAbandonoFacade;
import com.edenorte.turnos.entity.MotivoAbandono;
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
@Named("motivoAbandonoConverter")
public class MotivoAbandonoConverter implements Converter {

    @EJB
    private MotivoAbandonoFacade motivoAbandonoCtrl;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.matches("[0-9]+")) {
            return motivoAbandonoCtrl.find(Integer.parseInt(value));
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value == null || (value instanceof String) ? null : ((MotivoAbandono) value).getIdmotivoAbandono().toString();
    }

    public void setMotivoAbandonoCtrl(MotivoAbandonoFacade motivoAbandonoCtrl) {
        this.motivoAbandonoCtrl = motivoAbandonoCtrl;
    }

}
