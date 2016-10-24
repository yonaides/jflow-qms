/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.managedbean;

import com.aniuska.jflow.ejb.DispositivoFacade;
import com.aniuska.jflow.ejb.SucursalFacade;
import com.aniuska.jflow.entity.Dispositivo;
import com.aniuska.jflow.websocket.Message;
import com.aniuska.jflow.websocket.MessageType;
import com.aniuska.jflow.websocket.WSKioscoInf;
import com.aniuska.jflow.websocket.WSPrinter;
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
public class DispositivoBean implements Serializable {

    private final long serialVersionUID = 23L;

    @EJB
    SucursalFacade sucursalCtrl;
    @EJB
    DispositivoFacade DispositivoCtrl;
    @EJB
    WSKioscoInf wsKioscoInf;
    @EJB
    WSPrinter wsPrinter;

    private List<String> selectedOptions;
    private Dispositivo dispositivo;
    private List<Dispositivo> dispositivos;
    private String busqueda;
    private String vista = "consulta";

    @PostConstruct
    public void init() {
        dispositivo = new Dispositivo();
        dispositivos = DispositivoCtrl.findAll();
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public Dispositivo getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }

    public void editarKiosco(Dispositivo kiosco) {
        this.dispositivo = kiosco;
       vista = "editar";
    }

    public List<Dispositivo> getDispositivos() {
        return dispositivos;
    }

    public List<String> getSelectedOptions() {
        return selectedOptions;
    }

    public void setSelectedOptions(List<String> selectedOptions) {
        this.selectedOptions = selectedOptions;
    }

    public String getVista() {
        return vista;
    }

    public void setVista(String vista) {
        this.vista = vista;
    }

    public boolean isConnected(Dispositivo k) {

        if ("PRINTER".equals(k.getTipoDispositivo())) {
            return wsPrinter.isConnected(k);
        } else {
            return wsKioscoInf.isConnected(k);
        }

    }

    public void salvar() {

        if (dispositivo.getIddispositivo() == null) {

            String token = UUID.randomUUID().toString().toUpperCase();
            dispositivo.setIddispositivo(token);
            dispositivo.setVersionDispositivo("--");
            DispositivoCtrl.create(dispositivo);

            MessageUtils.sendSuccessfulMessage("Nueva dispositivo Creado");
        } else {
            DispositivoCtrl.edit(dispositivo);
            MessageUtils.sendSuccessfulMessage("dispositivo actualizada");
        }

        nuevo();
    }

    public void nuevo() {
        dispositivo = new Dispositivo();
        dispositivos = DispositivoCtrl.findAll();
    }

    public void refreshKiosco(Dispositivo k) {

        Message ms = new Message(MessageType.REFRESH);
        if ("PRINTER".equals(k.getTipoDispositivo())) {
            wsPrinter.sendMessage(k, ms);
        } else {
            wsKioscoInf.sendMessage(k, ms);
        }

    }

    public void buscar() {

        for (String selectedOption : selectedOptions) {
            System.out.println(selectedOption);
        }
    }

}
