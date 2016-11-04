/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.managedbean;

import com.aniuska.jflow.ejb.SucursalFacade;
import com.aniuska.jflow.entity.Sucursal;
import com.aniuska.jflow.entity.Servicio;
import com.aniuska.utils.MessageUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author hectorvent@gmail.com
 */
@ViewScoped
@Named
public class SucursalBean implements Serializable {

    private final long serialVersionUID = 23L;

    @EJB
    SucursalFacade sucursalCtrl;
    private Sucursal sucursal;
    private List<Servicio> servicios;
    private String vista = "consulta";
    private String busqueda = "";

    @PostConstruct
    public void init() {
        sucursal = new Sucursal();
        servicios = new ArrayList();
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursalCtrl.find(sucursal.getIdsucursal());
        servicios = sucursal.getServicioList();
        vista = "editar";
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public List<Sucursal> getSucursales() {
        if (busqueda.isEmpty()) {
            return sucursalCtrl.findAll();
        } else {
            return sucursalCtrl.findAll(busqueda);
        }
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

    public String getVista() {
        return vista;
    }

    public void setVista(String vista) {
        this.vista = vista;
    }

    public void salvar() {

        sucursal.setServicioList(servicios);

        if (sucursal.getIdsucursal() == null) {
            sucursal.setSecuencia(0);
            sucursalCtrl.create(sucursal);

            MessageUtils.sendSuccessfulMessage("Nueva sucursal Creada");
        } else {
            sucursalCtrl.edit(sucursal);
            MessageUtils.sendSuccessfulMessage("Oficina actualizada");
        }

        nuevo();
    }

    public void nuevo() {
        sucursal = new Sucursal();
        servicios = new ArrayList();
    }

    public void agregarServicio(Servicio servicio) {

        if (!servicios.contains(servicio)) {
            servicios.add(servicio);
        }
    }

    public void quitarServicio(Servicio servicio) {

        if (servicios.contains(servicio)) {
            servicios.remove(servicio);
        }
    }

}
