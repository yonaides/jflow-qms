/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.managedbean;

import com.aniuska.jflow.auth.AuthenticationBean;
import com.aniuska.jflow.ejb.TicketFacade;
import com.aniuska.jflow.entity.Sucursal;
import com.aniuska.jflow.entity.Ticket;
import com.aniuska.jflow.entity.TicketDetalle;
import com.aniuska.jflow.entity.TicketMonitoreo;
import com.aniuska.jflow.utils.Estados;
import com.aniuska.jflow.utils.TimeUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author hectorvent@gmail.com
 */
@Named
@ViewScoped
public class MonitorearTicket implements Serializable {

    private final long serialVersionUID = 23L;
    private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(MonitorearTicket.class.getName());

    @EJB
    private TicketFacade ticketCtrl;
    @Inject
    private AuthenticationBean authenticationBean;

    @PostConstruct
    public void init() {

    }

    public void setTicketCtrl(TicketFacade ticketCtrl) {
        this.ticketCtrl = ticketCtrl;
    }

    public void setAuthenticationBean(AuthenticationBean authenticationBean) {
        this.authenticationBean = authenticationBean;
    }

    public List<TicketMonitoreo> getTicketsEspera() {

        Sucursal sucursal = authenticationBean.getUsuario().getIdsucursal();
        List<TicketDetalle> tickets = ticketCtrl.findTurnosBySucursalAndEstado(sucursal, Estados.EN_ESPERA);
        List<TicketMonitoreo> mTickets = new ArrayList();

        for (TicketDetalle td : tickets) {
            TicketMonitoreo tm = new TicketMonitoreo();

            Ticket turno = td.getIdticket();
            tm.setCliente(turno.getIdcliente().toString());
            tm.setEspecial(turno.getPrioridad() == 2);
            tm.setFecha(turno.getFechaCreacion());
            tm.setNic(turno.getIdcliente().getContrato().toString());
            tm.setTiempoEspera(TimeUtils.getDiffTimeMinutes(turno.getFechaCreacion(), new Date()));
            tm.setServicio(td.getIdservicio().getNombre());
            tm.setTicket(turno.getHappyNumber());

            mTickets.add(tm);
        }

        Collections.sort(mTickets, new Comparator<TicketMonitoreo>() {
            @Override
            public int compare(TicketMonitoreo o1, TicketMonitoreo o2) {
                return o2.getTiempoEspera().subtract(o1.getTiempoEspera()).intValue();
            }
        });

        return mTickets;
    }

    public List<TicketMonitoreo> getTicketsProceso() {

        Sucursal sucursal = authenticationBean.getUsuario().getIdsucursal();
        List<TicketDetalle> turnos = ticketCtrl.findTurnosBySucursalAndEstado(sucursal, Estados.EN_PROCESO);
        List<TicketMonitoreo> mTurnos = new ArrayList();

        for (TicketDetalle td : turnos) {

            TicketMonitoreo tm = new TicketMonitoreo();

            Ticket turno = td.getIdticket();
            tm.setCliente(turno.getIdcliente().toString());
            tm.setEspecial(turno.getPrioridad() == 2);
            tm.setFecha(turno.getFechaCreacion());
            tm.setNic(turno.getIdcliente().getContrato().toString());
            tm.setTiempoEspera(td.getTiempoEspera());

            tm.setEstacion(td.getIdestacion().getNombre());

            tm.setUsuario(td.getIdoperador().toString());
            tm.setTiempoProceso(TimeUtils.getDiffTimeMinutes(td.getFechaInicioAtencion(), new Date()));
            tm.setServicio(td.getIdservicio().getNombre());
            tm.setTicket(turno.getHappyNumber());

            mTurnos.add(tm);
        }

        Collections.sort(mTurnos, new Comparator<TicketMonitoreo>() {
            @Override
            public int compare(TicketMonitoreo o1, TicketMonitoreo o2) {
                return o2.getTiempoProceso().subtract(o1.getTiempoProceso()).intValue();
            }
        });

        return mTurnos;
    }

}
