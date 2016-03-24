/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edenorte.turnos.managedbean;

import com.edenorte.turnos.auth.AuthenticationBean;
import com.edenorte.turnos.ejb.OficinaFacade;
import com.edenorte.turnos.entity.Oficina;
import com.edenorte.turnos.utils.GenericType;
import com.edenorte.utils.date.DateRange;
import com.edenorte.utils.report.JasperReportExcepcion;
import com.edenorte.utils.report.JasperReportUtils;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author hventura@citrus.com.do
 */
@Named
@ViewScoped
public class ReporteBean implements Serializable {

    private static final Logger LOGGER = LogManager.getLogger(ReporteBean.class);

    @Resource(lookup = "jdbc/turnos")
    private DataSource dataSource;

    @EJB
    private OficinaFacade oficinaCtrl;
    private Oficina oficina;
    private DateRange dateRange;

    @ManagedProperty(value = "#{authenticationBean}")
    private AuthenticationBean authenticationBean;
    private List<GenericType> reportes;
    private String reportFile;

    @PostConstruct
    public void init() {

        dateRange = new DateRange();
        dateRange.setFirstDate(new Date());
        dateRange.setEndDate(new Date());

        reportes = Arrays.asList(new GenericType("Clientes en Proceso", "ClientesEnProceso"),
                new GenericType("Eficiencia Estaciones", "EficienciaEstaciones"),
                new GenericType("Eficiencia Representante", "EficienciaUsuarios")
        );
    }

    public Oficina getOficina() {
        return oficina;
    }

    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public void setDateRange(DateRange dateRange) {
        this.dateRange = dateRange;
    }

    public void setOficinaCtrl(OficinaFacade oficinaCtrl) {
        this.oficinaCtrl = oficinaCtrl;
    }

    public List<Oficina> getOficinas() {
        return oficinaCtrl.findAll();
    }

    public AuthenticationBean getAuthenticationBean() {
        return authenticationBean;
    }

    public void setAuthenticationBean(AuthenticationBean authenticationBean) {
        this.authenticationBean = authenticationBean;
    }

    public List<GenericType> getReportes() {
        return reportes;
    }

    public String getReportFile() {
        return reportFile;
    }

    public void setReportFile(String reportFile) {
        this.reportFile = reportFile;
    }

    public void imprimirReporte() {

        try {

//            System.out.println("DateRange : " + dateRange);
//            System.out.println("Oficina = " + oficina.getNombre());
            LOGGER.info("Imprimiendo reporte");

//            Oficina oficina = authenticationBean.getUsuario().getIdoficina();
            JasperReportUtils.newReport()
                    .setJaspersDir("/com/edenorte/turnos/reports/")
                    .setJasperFile(reportFile)
                    .setDataSource(dataSource)
                    .put("oficinaNombre", oficina.getNombre())
                    .put("oficinaId", oficina.getIdoficina())
                    .put("fechaIni", dateRange.getFirstDate())
                    .put("fechaFin", dateRange.getEndDate())
                    .executeReport();

            LOGGER.info("Reporte ejecutado");
        } catch (JasperReportExcepcion ex) {

            LOGGER.error("Error en el reporte : ", "Error", ex);
        }

    }

}
