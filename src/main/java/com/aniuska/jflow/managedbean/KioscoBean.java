/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.managedbean;

import com.aniuska.jflow.ejb.KioscoFacade;
import com.aniuska.jflow.ejb.SucursalFacade;
import com.aniuska.jflow.entity.Kiosco;
import com.aniuska.jflow.websocket.Message;
import com.aniuska.jflow.websocket.MessageType;
import com.aniuska.jflow.websocket.WSNotification;
import com.aniuska.utils.MessageUtils;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
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
public class KioscoBean implements Serializable {

    private final long serialVersionUID = 23L;

    @EJB
    private SucursalFacade sucursalCtrl;
    @EJB
    private KioscoFacade kioscoCtrl;
    @EJB
    private WSNotification wsNotificacion;

    private Kiosco kiosco;
    private List<Kiosco> kioscos;
    private String busqueda;

    @PostConstruct
    public void init() {
        kiosco = new Kiosco();
        kioscos = kioscoCtrl.findAll();
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public SucursalFacade getSucursalCtrl() {
        return sucursalCtrl;
    }

    public void setSucursalCtrl(SucursalFacade sucursalCtrl) {
        this.sucursalCtrl = sucursalCtrl;
    }

    public KioscoFacade getKioscoCtrl() {
        return kioscoCtrl;
    }

    public void setKioscoCtrl(KioscoFacade kioscoCtrl) {
        this.kioscoCtrl = kioscoCtrl;
    }

    public Kiosco getKiosco() {
        return kiosco;
    }

    public void setKiosco(Kiosco kiosco) {
        this.kiosco = kiosco;
    }

    public List<Kiosco> getKioscos() {
        return kioscos;
    }

    public void setKioscos(List<Kiosco> kioscos) {
        this.kioscos = kioscos;
    }

    public boolean isAutoservicio() {
        return kiosco.getAutoservicio() == null || kiosco.getAutoservicio() == 'S';
    }

    public void setAutoservicio(boolean autoservicio) {
        kiosco.setAutoservicio(autoservicio ? 'S' : 'N');
    }

    public void setWsNotificacion(WSNotification wsNotificacion) {
        this.wsNotificacion = wsNotificacion;
    }

    public boolean isConnected(Kiosco kios) {

        return wsNotificacion.isConnected(kios);
    }

    public void salvar() {

        if (kiosco.getIdkiosco() == null) {

            String token = UUID.randomUUID().toString().toUpperCase();
            kiosco.setIdkiosco(token);
            kiosco.setVersionKiosco("--");

            kioscoCtrl.create(kiosco);

            MessageUtils.sendSuccessfulMessage("Nueva kiosco Creado");
        } else {
            kioscoCtrl.edit(kiosco);
            MessageUtils.sendSuccessfulMessage("Kiosco actualizada");
        }

        kioscos = kioscoCtrl.findAll();
        nuevo();
    }

    public void nuevo() {
        kiosco = new Kiosco();
        kioscos = kioscoCtrl.findAll();
    }

    public void refreshKiosco(Kiosco k) {
        Message ms = new Message(MessageType.REFRESH);
        wsNotificacion.sendMessage(k.getIdsucursal(), ms);
    }

    public void buscar() {

    }

}
