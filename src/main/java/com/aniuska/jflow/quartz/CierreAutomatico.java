package com.aniuska.jflow.quartz;

import com.aniuska.jflow.ejb.TicketFacade;
import com.aniuska.jflow.entity.Ticket;
import com.aniuska.jflow.entity.TicketDetalle;
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

    private TicketFacade turnoCtrl;
    private final Logger LOG = LogManager.getLogger(CierreAutomatico.class);

    @Override
    public void execute(JobExecutionContext context)
            throws JobExecutionException {

        LOG.info("Corriendo proceso de cierre de turnos...");
        ejecutarProceso();
    }

    private Object getEJB(String name) throws NamingException {
        final Context cx = new InitialContext();
        return cx.lookup("java:global/jflow-qms/" + name);
    }

    public void ejecutarProceso() {

        try {
            turnoCtrl = (TicketFacade) getEJB("TicketFacade");
        } catch (NamingException ex) {
            LOG.error("Error: ", ex);
            return;
        }

        LOG.info("Iniciando proceso de cierre de Turnos En Espera y En Proceso");
        List<Ticket> turnos = turnoCtrl.findTurnosAbiertos();

        for (Ticket turno : turnos) {
            LOG.info("Cerrando turno {} - {}", turno.getIdturno(), turno.getHappyNumber());

            for (TicketDetalle td : turno.getTicketDetalleList()) {
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
