/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.managedbean;

import com.aniuska.jflow.auth.AuthenticationBean;
import com.aniuska.jflow.ejb.ClienteFacade;
import com.aniuska.jflow.ejb.EsperaFacade;
import com.aniuska.jflow.ejb.MotivoAbandonoFacade;
import com.aniuska.jflow.ejb.MotivoRecesoFacade;
import com.aniuska.jflow.ejb.ServicioFacade;
import com.aniuska.jflow.ejb.TurnoFacade;
import com.aniuska.jflow.entity.Cliente;
import com.aniuska.jflow.entity.Espera;
import com.aniuska.jflow.entity.Estado;
import com.aniuska.jflow.entity.MotivoAbandono;
import com.aniuska.jflow.entity.MotivoReceso;
import com.aniuska.jflow.entity.Servicio;
import com.aniuska.jflow.entity.Sessiones;
import com.aniuska.jflow.entity.Turno;
import com.aniuska.jflow.entity.TurnoDetalle;
import com.aniuska.jflow.utils.Estados;
import com.aniuska.jflow.utils.TimeUtils;
import com.aniuska.jflow.websocket.Message;
import com.aniuska.jflow.websocket.MessageType;
import com.aniuska.jflow.websocket.WSNotification;
import com.aniuska.jflow.ws.ConsultaContratoWS;
import com.aniuska.jflow.ws.DatosCliente;
import com.edenorte.utils.MessageUtils;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author hventura@citrus.com.do
 */
@Named
@ViewScoped
public class ColaTurnoBean implements Serializable {

    private final long serialVersionUID = 75L;

    @EJB
    private TurnoFacade turnoCtrl;
    @EJB
    private ServicioFacade servicioCtrl;
    @EJB
    private MotivoRecesoFacade movitoRecesoCtrl;
    @EJB
    private MotivoAbandonoFacade motivoAbandonoCtrl;
    @EJB
    private ClienteFacade clienteCtrl;
    @EJB
    private WSNotification wsNotificacion;
    @EJB
    private EsperaFacade esperaCtrl;

    private TurnoDetalle turnoDetalle;
    private Cliente cliente;
    private Servicio servicio;
    private MotivoAbandono motivoAbandono;
    private MotivoReceso motivoReceso;
    private int tipoOpcionTurno = 0;
    private Espera espera;
    private boolean tomarTurno = true;

    @Inject
    private AuthenticationBean authenticationBean;
    @Inject
    private SessionBean sessionBean;

    @PostConstruct
    public void init() {
        turnoDetalle = new TurnoDetalle();
        cliente = new Cliente();

        servicio = null;
        motivoAbandono = null;
        motivoReceso = null;
        tipoOpcionTurno = 0;
        tomarTurno = true;

        espera = esperaCtrl.findEsperaActiva(authenticationBean.getSession());
    }

    public void setTurnoCtrl(TurnoFacade turnoCtrl) {
        this.turnoCtrl = turnoCtrl;
    }

    public void setClienteCtrl(ClienteFacade clienteCtrl) {
        this.clienteCtrl = clienteCtrl;
    }

    public void setAuthenticationBean(AuthenticationBean authenticationBean) {
        this.authenticationBean = authenticationBean;
    }

    public TurnoDetalle getTurnoDetalle() {
        return turnoDetalle;
    }

    public void setTurnoDetalle(TurnoDetalle turnoDetalle) {
        this.turnoDetalle = turnoDetalle;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean isTitular() {
        return this.cliente.getTitular() == (short) 1;
    }

    public void setTitular(boolean titu) {
        this.cliente.setTitular((short) (titu ? 1 : 0));
    }

    public void setWsNotificacion(WSNotification wsNotificacion) {
        this.wsNotificacion = wsNotificacion;
    }

    public void setServicioCtrl(ServicioFacade servicioCtrl) {
        this.servicioCtrl = servicioCtrl;
    }

    public void setMovitoRecesoCtrl(MotivoRecesoFacade movitoRecesoCtrl) {
        this.movitoRecesoCtrl = movitoRecesoCtrl;
    }

    public void setMotivoAbandonoCtrl(MotivoAbandonoFacade motivoAbandonoCtrl) {
        this.motivoAbandonoCtrl = motivoAbandonoCtrl;
    }

    public List<MotivoAbandono> getMotivoAbandonos() {
        return motivoAbandonoCtrl.findAll();
    }

    public List<MotivoReceso> getMotivoRecesos() {
        return movitoRecesoCtrl.findAll();
    }

    public List<Servicio> getServicios() {
        return servicioCtrl.findAll();
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public MotivoAbandono getMotivoAbandono() {
        return motivoAbandono;
    }

    public void setMotivoAbandono(MotivoAbandono motivoAbandono) {
        this.motivoAbandono = motivoAbandono;
    }

    public MotivoReceso getMotivoReceso() {
        return motivoReceso;
    }

    public void setMotivoReceso(MotivoReceso motivoReceso) {
        this.motivoReceso = motivoReceso;
    }

    public int getTipoOpcionTurno() {
        return tipoOpcionTurno;
    }

    public void setTipoOpcionTurno(int tipoOpcionTurno) {
        this.tipoOpcionTurno = tipoOpcionTurno;
    }

    public EsperaFacade getEsperaCtrl() {
        return esperaCtrl;
    }

    public void setEsperaCtrl(EsperaFacade esperaCtrl) {
        this.esperaCtrl = esperaCtrl;
    }

    public Espera getEspera() {
        return espera;
    }

    public boolean isTomarTurno() {
        return tomarTurno;
    }

    public void setTomarTurno(boolean tomarTurno) {
        this.tomarTurno = tomarTurno;
    }

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

    public void terminarTurno() {

        //index 0, 1- Terminar turno y llamar proximo cliente.
        //index 1, 2- Terminar turno y asignar otro servicio.
        //index 2, 3- Terminar turno y tomar un receso.
        //index 3, 4- Abandonar turno y llamar al proximo.
        //index 4, 5- Terminar turno y finalizar dia.
        Estado estado = Estados.ATENDIDO;
        List<TurnoDetalle> detTurnos = new ArrayList();

        //index 1, 2- Terminar turno y asignar otro servicio.
        if (tipoOpcionTurno == 1) {
            if (servicio == null) {
                MessageUtils.sendErrorMessage("Debe sellecionar un servicio");
                return;
            }

            TurnoDetalle dt = new TurnoDetalle();
            dt.setFechaInicio(new Date());

            dt.setIdservicio(servicio);
            dt.setTiempoEspera(BigDecimal.ZERO);
            dt.setTiempoProceso(BigDecimal.ZERO);

            if (tomarTurno) {

                Sessiones sec = authenticationBean.getSession();
                dt.setIdestado(Estados.EN_PROCESO);
                dt.setFechaInicioAtencion(new Date());
                dt.setIdoperador(authenticationBean.getUsuario());
                dt.setIdestacion(sec.getIdestacion());

                BigDecimal tdiff = TimeUtils.getDiffTimeMinutes(
                        dt.getFechaInicio(),
                        dt.getFechaInicioAtencion()
                );
                dt.setTiempoEspera(tdiff);
            } else {
                dt.setIdestado(Estados.EN_ESPERA);
            }

            detTurnos.add(dt);
        }

        //index 2, 3- Terminar turno y tomar un receso.
        if (tipoOpcionTurno == 2) {
            if (motivoReceso == null) {
                MessageUtils.sendErrorMessage("Debe sellecionar motivo de receso");
                return;
            }

            espera = new Espera();
            espera.setFechaInicio(new Date());
            espera.setEnespera('S');
            espera.setIdmotivoReceso(motivoReceso);
            espera.setIdsession(authenticationBean.getSession());
        }

        //index 3, 4- Abandonar turno y llamar al proximo.
        if (tipoOpcionTurno == 3) {
            if (motivoAbandono == null) {
                MessageUtils.sendErrorMessage("Debe sellecionar motivo de abandono");
                return;
            }

            estado = Estados.ABANDONADO;
            turnoDetalle.setIdmotivoAbandono(motivoAbandono);
        }

        if (turnoDetalle.getIdturno() != null) {

            // Si el Id del cliente es null, es que se debe crear0
            if (cliente.getIdcliente() == null) {
                cliente.setIdusuarioIngreso(authenticationBean.getUsuario());
                clienteCtrl.create(cliente);
            }

            Turno turno = turnoDetalle.getIdturno();
            turno.setIdcliente(cliente);
            turno.setFechaFin(new Date());

            // Si la lista esta vacia quiere decir que no hay otro servicio
            turno.setIdestado(detTurnos.isEmpty() ? estado : Estados.EN_ESPERA);

            turnoDetalle.setFechaFinAtencion(turno.getFechaFin());
            turnoDetalle.setIdestado(estado);

            BigDecimal td = TimeUtils.getDiffTimeMinutes(
                    turnoDetalle.getFechaInicioAtencion(),
                    turnoDetalle.getFechaFinAtencion()
            );
            turnoDetalle.setTiempoProceso(td);

            detTurnos.add(turnoDetalle);
            for (TurnoDetalle detTurno : detTurnos) {
                detTurno.setIdturno(turno);
            }

            turno.setTurnoDetalleList(detTurnos);
            turnoCtrl.edit(turno);

            MessageUtils.sendSuccessfulMessage("Turno concluido!");

            quitarTurno();
            //------------------------------------------
            //index 0, 1- Terminar turno y llamar proximo cliente.
            //index 1, 2- Terminar turno y asignar otro servicio.
            //index 3, 4- Abandonar turno y llamar al proximo.
            if (tipoOpcionTurno == 0 || tipoOpcionTurno == 1 || tipoOpcionTurno == 3) {
                init();
                siguiente();
            }

            //index 2, 3- Terminar turno y tomar un receso.
            if (tipoOpcionTurno == 2) {
                esperaCtrl.create(espera);
                init();
            }

            //index 4, 5- Terminar turno y finalizar dia.
            if (tipoOpcionTurno == 4) {
                init();
                terminarDia();
            }

        }
    }

    public void siguiente() {

        Sessiones ses = authenticationBean.getSession();
        TurnoDetalle td = turnoCtrl.turnoPendienteDetalle(ses);
        Turno turno;

        if (td == null) {

            td = turnoCtrl.nextTurnoDetalle(ses);

            if (td == null) {
                init();
                MessageUtils.sendSuccessfulMessage("No hay turnos pendientes!!");
                return;
            }

            turnoDetalle = td;
            turnoDetalle.setFechaInicioAtencion(new Date());
            turnoDetalle.setIdoperador(authenticationBean.getUsuario());
            turnoDetalle.setIdestado(Estados.EN_PROCESO);
            turnoDetalle.setIdestacion(ses.getIdestacion());
            turnoDetalle.setTiempoEspera(new BigDecimal(2));

            BigDecimal tdiff = TimeUtils.getDiffTimeMinutes(
                    turnoDetalle.getFechaInicio(),
                    turnoDetalle.getFechaInicioAtencion()
            );
            turnoDetalle.setTiempoEspera(tdiff);

            turno = turnoDetalle.getIdturno();
            turno.setIdestado(Estados.EN_PROCESO);
            turnoDetalle.setIdturno(turno);
            turno.setTurnoDetalleList(asList(turnoDetalle));

            // Actualizamos el siquiente turno, en proceso y quien es el Operador quien lo va atender.
            turnoCtrl.edit(turno);

            llamarTurno(false);
        } else {
            turnoDetalle = td;
            MessageUtils.sendErrorMessage("Esto es un turno pendiente!");
            turno = turnoDetalle.getIdturno();
        }

        // Si el cliente es ID = 1 es que el cliente por defecto se debe crear uno nuevo
        if (turno.getIdcliente().getIdcliente().equals(new BigDecimal(1))) {
            cliente = new Cliente();
        } else {
            cliente = turno.getIdcliente();
        }

        MessageUtils.sendSuccessfulMessage("Llamando siquiente turno");

    }

    public void llamarTurno(boolean rellamar) {

        if (rellamar) {
            MessageUtils.sendSuccessfulMessage("Rellamando turno!");
        }

        Turno turno = turnoDetalle.getIdturno();
        Message ms = new Message(MessageType.CALL);
        ms.put("turno", turno.getHappyNumber());
        ms.put("puesto", turnoDetalle.getIdestacion().getNumeroEstacion() + "");
        ms.put("especial", turno.getPrioridad() == 2);
        ms.put("rellamar", rellamar);
        
        
        

        wsNotificacion.sendMessage(authenticationBean.getUsuario().getIdoficina(), ms);
    }

    public void quitarTurno() {

        Turno turno = turnoDetalle.getIdturno();
        Message ms = new Message(MessageType.REMOVE);
        ms.put("turno", turno.getHappyNumber());
        ms.put("puesto", turnoDetalle.getIdestacion().getNumeroEstacion() + "");
        ms.put("especial", turno.getPrioridad() == 2);
        ms.put("rellamar", false);

        wsNotificacion.sendMessage(authenticationBean.getUsuario().getIdoficina(), ms);
    }

    public void buscarCliente() {

        Cliente cli = clienteCtrl.findByIdentificacion(cliente.getCedula());

        if (cli == null) {
            String cedula = cliente.getCedula();
            MessageUtils.sendSuccessfulMessage("Cliente no se encuentra registrado!");
            cliente = new Cliente();
            cliente.setCedula(cedula);
        } else {
            MessageUtils.sendSuccessfulMessage("Cliente existe!");
            cliente = cli;
        }
    }

    public void terminarEspera() {
        esperaCtrl.terminarEspera(espera);
        init();
    }

    public void buscarNic() {

        Cliente cli = clienteCtrl.findByContrato(cliente.getContrato());

        if (cli != null) {
            cliente = cli;
        } else {

            DatosCliente dc = ConsultaContratoWS.findClienteByNic(cliente.getContrato().intValue());

            if (dc == null) {
                MessageUtils.sendSuccessfulMessage("Error al consultar los clientes!!");
                return;
            }

            if ("M02".equals(dc.getMensaje().getCodigo())) {
                MessageUtils.sendSuccessfulMessage("Este contrato no existe!!");
                return;
            }

            cliente.setApellido(dc.getApellidos());
            cliente.setCedula(dc.getCedulaCliente());
            cliente.setNombre(dc.getNombreCliente());
            cliente.setTelefono(dc.getTelefonos());
            cliente.setTitular((short) 1);
        }

    }

    private void terminarDia() {
        sessionBean.cerrarSession();

        // Ejecutar actualización de SPA
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("content");
    }

}
