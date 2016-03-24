package com.aniuska.jflow.quartz;

import com.aniuska.jflow.ejb.TurnoFacade;
import com.aniuska.jflow.entity.Turno;
import com.aniuska.jflow.entity.TurnoDetalle;
import com.aniuska.jflow.utils.Estados;
import java.util.Date;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class CierreAutomatico implements Job {

    private TurnoFacade turnoCtrl;
    private final Logger LOG = LogManager.getLogger(CierreAutomatico.class);

    @Override
    public void execute(JobExecutionContext context)
            throws JobExecutionException {

        LOG.info("Corriendo proceso de cierre de turnos...");
        ejecutarProceso();
    }

    private Object getEJB(String name) throws NamingException {
        final Context cx = new InitialContext();
        return cx.lookup("java:global/TurnosWebApp-2.0.0.3/" + name);
    }

    public void ejecutarProceso() {

        try {
            turnoCtrl = (TurnoFacade) getEJB("TurnoFacade");
        } catch (NamingException ex) {
            LOG.error("Error: ", ex);
            return;
        }

        LOG.info("Iniciando proceso de cierre de Turnos En Espera y En Proceso");
        List<Turno> turnos = turnoCtrl.findTurnosAbiertos();

        for (Turno turno : turnos) {
            LOG.info("Cerrando turno {} - {}", turno.getIdturno(), turno.getHappyNumber());

            for (TurnoDetalle td : turno.getTurnoDetalleList()) {
                td.setFechaInicioAtencion(new Date());
                td.setFechaFinAtencion(new Date());
                td.setIdestado(Estados.CIERRE_AUTOMATICO);
            }

            turno.setFechaFin(new Date());
            turno.setIdestado(Estados.CIERRE_AUTOMATICO);

            turnoCtrl.edit(turno);
        }

        LOG.info("Finalizando proceso de cierre, se cerraron {}", turnos.size());
    }

}
