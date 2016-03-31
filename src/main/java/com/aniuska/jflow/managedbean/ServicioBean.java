/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.managedbean;

import com.aniuska.jflow.ejb.ServicioFacade;
import com.aniuska.jflow.entity.Servicio;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author hectorvent@gmail.com
 */
@ViewScoped
@Named
public class ServicioBean implements Serializable {

    private final long serialVersionUID = 24L;

    @EJB
    private ServicioFacade servicioCtrl;
    private List<Servicio> servicios;
    private Servicio servicio;

    private String busqueda;

    @PostConstruct
    public void init() {
        servicio = new Servicio();
        servicios = servicioCtrl.findAll();
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public ServicioFacade getServicioCtrl() {
        return servicioCtrl;
    }

    public void setServicioCtrl(ServicioFacade servicioCtrl) {
        this.servicioCtrl = servicioCtrl;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

    public void salvar() {

        if (servicio.getIdservicio() == null) {
            servicioCtrl.create(servicio);
        } else {
            servicioCtrl.edit(servicio);
        }

        init();
    }

    public void nuevo() {
        servicio = new Servicio();
    }

    public void onRowSelect(SelectEvent event) {
        servicio = (Servicio) event.getObject();
    }

}
