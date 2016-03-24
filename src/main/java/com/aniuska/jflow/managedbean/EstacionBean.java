/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.managedbean;

import com.aniuska.jflow.ejb.EstacionFacade;
import com.aniuska.jflow.ejb.OficinaFacade;
import com.aniuska.jflow.ejb.TipoEstacionFacade;
import com.aniuska.jflow.entity.Estacion;
import com.aniuska.jflow.entity.EstacionServicio;
import com.aniuska.jflow.entity.Oficina;
import com.aniuska.jflow.entity.Servicio;
import com.aniuska.jflow.entity.TipoEstacion;
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
public class EstacionBean implements Serializable {

    private final long serialVersionUID = 24L;

    @EJB
    private OficinaFacade oficinaCtrl;
    @EJB
    private EstacionFacade estacionCtrl;
    @EJB
    private TipoEstacionFacade tipoEstacionCtrl;
    private Estacion estacion;
    private List<EstacionServicio> estacionServicios;
    private List<Servicio> oficinaServicios;
    private List<Estacion> estaciones;
    private List<Oficina> oficinas;
    private List<TipoEstacion> tiposEstacion;
    private String busqueda;

    @PostConstruct
    public void init() {
        estacion = new Estacion();
        estacionServicios = new ArrayList();
        estaciones = estacionCtrl.findAll();
        oficinas = oficinaCtrl.findAll();
        oficinaServicios = null;
        tiposEstacion = tipoEstacionCtrl.findAll();
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public List<Oficina> getOficinas() {
        return oficinas;
    }

    public void setOficinas(List<Oficina> oficinas) {
        this.oficinas = oficinas;
    }

    public EstacionFacade getEstacionCtrl() {
        return estacionCtrl;
    }

    public void setEstacionCtrl(EstacionFacade estacionCtrl) {
        this.estacionCtrl = estacionCtrl;
    }

    public OficinaFacade getOficinaCtrl() {
        return oficinaCtrl;
    }

    public void setOficinaCtrl(OficinaFacade oficinaCtrl) {
        this.oficinaCtrl = oficinaCtrl;
    }

    public List<EstacionServicio> getEstacionServicios() {
        return estacionServicios;
    }

    public void setEstacionServicios(List<EstacionServicio> estacionServicios) {
        this.estacionServicios = estacionServicios;
    }

    public Estacion getEstacion() {
        return estacion;
    }

    public void setTipoEstacionCtrl(TipoEstacionFacade tipoEstacionCtrl) {
        this.tipoEstacionCtrl = tipoEstacionCtrl;
    }

    public List<TipoEstacion> getTiposEstacion() {
        return tiposEstacion;
    }

    public void setEstacion(Estacion est) {
        this.estacion = estacionCtrl.find(est.getIdestacion());

        estacionServicios = estacion.getEstacionServicioList();
        oficinaServicios = estacion.getIdoficina().getServicioList();
    }

    public List<Estacion> getEstaciones() {
        return estaciones;
    }

    public void setEstaciones(List<Estacion> estaciones) {
        this.estaciones = estaciones;
    }

    public List<Servicio> getOficinaServicios() {
        return oficinaServicios;
    }

    public void setOficinaServicios(List<Servicio> oficinaServicios) {
        this.oficinaServicios = oficinaServicios;
    }

    public void salvar() {

        estacion.setEstacionServicioList(estacionServicios);

        if (estacion.getIdestacion() == null) {
            estacionCtrl.create(estacion);

            MessageUtils.sendSuccessfulMessage("Nueva estación Creada");
        } else {
            estacionCtrl.edit(estacion);
            MessageUtils.sendSuccessfulMessage("Estación actualizada");
        }

        estaciones = estacionCtrl.findAll();
        nuevo();
    }

    public void nuevo() {
        estacion = new Estacion();
        estacionServicios = new ArrayList();
        oficinaServicios = null;
    }

    public void agregarServicio(Servicio servicio) {

        for (EstacionServicio es : estacionServicios) {
            if (es.getIdservicio().equals(servicio)) {
                return;
            }
        }

        EstacionServicio es = new EstacionServicio();
        es.setIdestacion(estacion);
        es.setIdservicio(servicio);
        es.setHabilitado('S');
        es.setPrioritario((short) 0);

        estacionServicios.add(es);
    }

    public void buscar() {

    }

    public void onOficinaChanged() {
        oficinaServicios = estacion.getIdoficina().getServicioList();
    }

}
