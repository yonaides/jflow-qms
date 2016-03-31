/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.auth;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author hectorvent@gmail.com
 */
@ManagedBean
@ApplicationScoped
public class RolEnum {

    public static final String CREAR_TURNO = "CREAR_TURNO";
    public static final String ATENDER_TURNO = "ATENDER_TURNO";
    public static final String REPORTE_OFICINA = "REPORTE_OFICINA";
    public static final String REPORTE_FULL = "REPORTE_FULL";
    public static final String ADM_SISTEMA = "ADM_SISTEMA";

    public String getCrearTurno() {
        return CREAR_TURNO;
    }

    public String getAtenderTurno() {
        return ATENDER_TURNO;
    }

    public String getReporteOficina() {
        return REPORTE_OFICINA;
    }

    public String getReporteFull() {
        return REPORTE_FULL;
    }

    public String getAdmSistema() {
        return ADM_SISTEMA;
    }

}
