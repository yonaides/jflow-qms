/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.managedbean;

import com.aniuska.jflow.utils.GenericType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author hectorvent@gmail.com
 */
@Named
@ViewScoped
public class ConfiguracionBean implements Serializable {

    private List<GenericType> configuracion;
    private String value;

    @PostConstruct
    public void init() {

        value = "init";
        configuracion = new ArrayList();
        configuracion.add(new GenericType("Motivo Abandono", "motivo_abandono"));
        configuracion.add(new GenericType("Motivo Receso", "motivo_receso"));
        configuracion.add(new GenericType("Variables del Sistema", "variables"));
        configuracion.add(new GenericType("Subir Archivos", "archivos"));
        configuracion.add(new GenericType("Lista Archivos", "archivos_lista"));
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List getConfiguracion() {
        return configuracion;
    }

}
