/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.utils.converter;

import com.aniuska.jflow.ejb.SucursalFacade;
import com.aniuska.jflow.entity.Sucursal;
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
public class SucursalConverter implements Converter {

    @EJB
    SucursalFacade sucursalCtrl;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.matches("[0-9]+")) {
            return sucursalCtrl.find(Integer.parseInt(value));
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value == null || (value instanceof String) ? "" : ((Sucursal) value).getIdsucursal().toString();
    }

}
