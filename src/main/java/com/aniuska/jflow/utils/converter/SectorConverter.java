/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.utils.converter;

import com.aniuska.jflow.ejb.SectorFacade;
import com.aniuska.jflow.entity.Sector;
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
@Named
public class SectorConverter implements Converter {

    @EJB
    SectorFacade sectorCtrl;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.matches("[0-9]+")) {
            return sectorCtrl.find(Integer.parseInt(value));
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value == null || (value instanceof String) ? null : ((Sector) value).getIdsector().toString();
    }

}
