/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.managedbean;

import com.aniuska.jflow.auth.AuthenticationBean;
import com.aniuska.jflow.ejb.SucursalFacade;
import com.aniuska.jflow.entity.Sucursal;
import com.aniuska.jflow.utils.GenericType;
import com.aniuska.utils.date.DateRange;
import com.aniuska.utils.report.JasperReportExcepcion;
import com.aniuska.utils.report.JasperReportUtils;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author hectorvent@gmail.com
 */
@Named
@ViewScoped
public class ReporteBean implements Serializable {

    private static final Logger LOGGER = LogManager.getLogger(ReporteBean.class);

    @Resource(lookup = "jdbc/jflow")
    DataSource dataSource;

    @Inject
    AuthenticationBean authenticationBean;
    @EJB
    SucursalFacade sucursalCtrl;
    private Sucursal sucursal;
    private DateRange dateRange;
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

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public void setDateRange(DateRange dateRange) {
        this.dateRange = dateRange;
    }

    public List<Sucursal> getSucursales() {
        return sucursalCtrl.findAll();
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
            LOGGER.info("Imprimiendo reporte");
            JasperReportUtils.newReport()
                    .setJaspersDir("/com/aniuska/jflow/reports/")
                    .setJasperFile(reportFile)
                    .setDataSource(dataSource)
                    .put("sucursalNombre", sucursal.getNombre())
                    .put("sucursalId", sucursal.getIdsucursal())
                    .put("fechaIni", dateRange.getFirstDate())
                    .put("fechaFin", dateRange.getEndDate())
                    .executeReport();

            LOGGER.info("Reporte ejecutado");
        } catch (JasperReportExcepcion ex) {

            LOGGER.error("Error en el reporte : ", "Error", ex);
        }

    }

}
