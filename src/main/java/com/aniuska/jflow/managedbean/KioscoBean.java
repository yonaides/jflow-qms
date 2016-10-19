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
public class KioscoBean implements Serializable {

    private final long serialVersionUID = 23L;

    @EJB
    SucursalFacade sucursalCtrl;
    @EJB
    KioscoFacade kioscoCtrl;
    @EJB
    WSKioscoInf wsKioscoInf;
    @EJB
    WSPrinter wsPrinter;

    private List<String> selectedOptions;
    private Kiosco kiosco;
    private List<Kiosco> kioscos;
    private String busqueda;
    private String vista = "consulta";

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

    public Kiosco getKiosco() {
        return kiosco;
    }

    public void setKiosco(Kiosco kiosco) {
        this.kiosco = kiosco;
    }

    public void editarKiosco(Kiosco kiosco) {
        this.kiosco = kiosco;
       vista = "editar";
    }

    public List<Kiosco> getKioscos() {
        return kioscos;
    }

    public boolean isAutoservicio() {
        return kiosco.getAutoservicio() == null || kiosco.getAutoservicio() == 'S';
    }

    public void setAutoservicio(boolean autoservicio) {
        kiosco.setAutoservicio(autoservicio ? 'S' : 'N');
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

    public boolean isConnected(Kiosco k) {

        if ("PRINTER".equals(k.getTipoDispositivo())) {
            return wsPrinter.isConnected(k);
        } else {
            return wsKioscoInf.isConnected(k);
        }

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

        nuevo();
    }

    public void nuevo() {
        kiosco = new Kiosco();
        kioscos = kioscoCtrl.findAll();
    }

    public void refreshKiosco(Kiosco k) {

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
