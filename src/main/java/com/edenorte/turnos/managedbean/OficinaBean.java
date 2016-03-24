/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edenorte.turnos.managedbean;

import com.edenorte.turnos.ejb.OficinaFacade;
import com.edenorte.turnos.entity.Oficina;
import com.edenorte.turnos.entity.Servicio;
import com.edenorte.utils.MessageUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author hventura@citrus.com.do
 */
@ViewScoped
@Named
public class OficinaBean implements Serializable {

    private final long serialVersionUID = 23L;

    @EJB
    private OficinaFacade oficinaCtrl;

    private Oficina oficina;
    private List<Servicio> servicios;

    private String busqueda = "";

    @PostConstruct
    public void init() {
        oficina = new Oficina();
        servicios = new ArrayList();
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public OficinaFacade getOficinaCtrl() {
        return oficinaCtrl;
    }

    public void setOficinaCtrl(OficinaFacade oficinaCtrl) {
        this.oficinaCtrl = oficinaCtrl;
    }

    public Oficina getOficina() {
        return oficina;
    }

    public void setOficina(Oficina oficina) {
        this.oficina = oficinaCtrl.find(oficina.getIdoficina());
        servicios = oficina.getServicioList();
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public List<Oficina> getOficinas() {
        if (busqueda.isEmpty()) {
            return oficinaCtrl.findAll();
        } else {
            return oficinaCtrl.findAll(busqueda);
        }
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

    public void salvar() {

        oficina.setServicioList(servicios);

        if (oficina.getIdoficina() == null) {
            oficina.setSecuencia(0);
            oficinaCtrl.create(oficina);

            MessageUtils.sendSuccessfulMessage("Nueva oficina Creada");
        } else {
            oficinaCtrl.edit(oficina);
            MessageUtils.sendSuccessfulMessage("Oficina actualizada");
        }

        nuevo();
    }

    public void nuevo() {
        oficina = new Oficina();
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
