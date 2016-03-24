/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author hventura@citrus.com.do
 */
public class TurnoMonitoreo implements Serializable {

    private String turno;
    private String servicio;
    private Date fecha;
    private boolean especial;
    private String nic;
    private String cliente;
    private BigDecimal tiempoEspera;
    private BigDecimal tiempoProceso;
    private String usuario;
    private String estacion;

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isEspecial() {
        return especial;
    }

    public void setEspecial(boolean especial) {
        this.especial = especial;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getTiempoEspera() {
        return tiempoEspera;
    }

    public void setTiempoEspera(BigDecimal tiempoEspera) {
        this.tiempoEspera = tiempoEspera;
    }

    public BigDecimal getTiempoProceso() {
        return tiempoProceso;
    }

    public void setTiempoProceso(BigDecimal tiempoProceso) {
        this.tiempoProceso = tiempoProceso;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEstacion() {
        return estacion;
    }

    public void setEstacion(String estacion) {
        this.estacion = estacion;
    }

//    @Override
//    public int compareTo(TurnoMonitoreo o) {
//        return o.rangoTiempo.subtract(rangoTiempo).intValue();
//    }
    @Override
    public String toString() {
        return "TurnoMonitoreo{" + "turno=" + turno + ", servicio=" + servicio + ", fecha=" + fecha + ", especial=" + especial + ", nic=" + nic + ", cliente=" + cliente + ", tiempoEspera=" + tiempoEspera + ", tiempoProceso=" + tiempoProceso + ", usuario=" + usuario + ", estacion=" + estacion + '}';
    }

}
