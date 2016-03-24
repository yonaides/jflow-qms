/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edenorte.turnos.managedbean;

import com.edenorte.turnos.auth.AuthenticationBean;
import com.edenorte.turnos.ejb.ClienteFacade;
import com.edenorte.turnos.ejb.OficinaFacade;
import com.edenorte.turnos.ejb.ServicioFacade;
import com.edenorte.turnos.ejb.TurnoFacade;
import com.edenorte.turnos.entity.Cliente;
import com.edenorte.turnos.entity.Oficina;
import com.edenorte.turnos.entity.Servicio;
import com.edenorte.turnos.entity.Turno;
import com.edenorte.turnos.entity.TurnoDetalle;
import com.edenorte.turnos.utils.Estados;
import com.edenorte.turnos.websocket.Message;
import com.edenorte.turnos.websocket.MessageType;
import com.edenorte.turnos.websocket.WSPrinter;
import com.edenorte.turnos.ws.ConsultaContratoWS;
import com.edenorte.turnos.ws.DatosCliente;
import com.edenorte.utils.MessageUtils;
import com.edenorte.utils.date.DateUtils;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author hventura@citrus.com.do
 */
@Named
@ViewScoped
public class GenerarTurnoBean implements Serializable {

    private final long serialVersionUID = 23L;

    @EJB
    private TurnoFacade turnoCtrl;
    @EJB
    private ClienteFacade clienteCtrl;
    @EJB
    private ServicioFacade servicioCtrl;
    @EJB
    private OficinaFacade ctrlOficina;
    @Inject
    private AuthenticationBean authenticationBean;

    @EJB
    private WSPrinter wsPrinter;

    private TurnoDetalle turnoDetalle;
    private Cliente cliente;
    private boolean prioritario;

    @PostConstruct
    public void init() {
        turnoDetalle = new TurnoDetalle();
        cliente = new Cliente();
        prioritario = false;
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

    public void setServicioCtrl(ServicioFacade servicioCtrl) {
        this.servicioCtrl = servicioCtrl;
    }

    public TurnoDetalle getTurnoDetalle() {
        return turnoDetalle;
    }

    public List<Servicio> getServicios() {
        Oficina oficina = authenticationBean.getUsuario().getIdoficina();
        return servicioCtrl.findByOficina(oficina);
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

    public void setCtrlOficina(OficinaFacade ctrlOficina) {
        this.ctrlOficina = ctrlOficina;
    }

    public boolean isTitular() {
        return this.cliente.getTitular() == (short) 1;
    }

    public void setTitular(boolean titu) {
        this.cliente.setTitular((short) (titu ? 1 : 0));
    }

    public boolean isPrioritario() {
        return prioritario;
    }

    public void setPrioritario(boolean prioritario) {
        this.prioritario = prioritario;
    }

    public WSPrinter getWsPrinter() {
        return wsPrinter;
    }

    public void setWsPrinter(WSPrinter wsPrinter) {
        this.wsPrinter = wsPrinter;
    }

    public List<Turno> getTurnos() {
        Oficina oficina = authenticationBean.getUsuario().getIdoficina();
        return turnoCtrl.findLast10(oficina);
    }

    public boolean isPrinterConnected() {
        Oficina ofi = authenticationBean.getUsuario().getIdoficina();
        return wsPrinter.isConnected(ofi);
    }

    public void salvar() {

        if (cliente.getIdcliente() == null) {
            cliente.setFechaIngreso(new Date());
            cliente.setIdusuarioIngreso(authenticationBean.getUsuario());
            clienteCtrl.create(cliente);
        }

        Oficina ofi = authenticationBean.getUsuario().getIdoficina();
        ofi = ctrlOficina.find(ofi.getIdoficina());
//        Oficina ofi = authenticationBean.getUsuario().getIdoficina();
        int num = ofi.getSecuencia() + 1;

        // Se el numero es 100 resetiar a 1, (1-99)
        if (num == 100) {
            num = 1;
        }
        ofi.setSecuencia(num);

        // Se actualiza la secuencia de la oficina
        ctrlOficina.edit(ofi);
        Servicio ser = turnoDetalle.getIdservicio();

        Turno turno = new Turno();
        turno.setFechaCreacion(new Date());
        turno.setHappyNumber(ser.getPrefijo() + "-" + num);
        turno.setIdoficina(ofi);
        turno.setIdcliente(cliente);
        turno.setPrioridad(prioritario ? 2 : 1);
        turno.setIdestado(Estados.EN_ESPERA);

        turnoDetalle.setIdturno(turno);
        turnoDetalle.setFechaInicio(turno.getFechaCreacion());
        turnoDetalle.setIdestado(Estados.EN_ESPERA);
        turnoDetalle.setTiempoEspera(BigDecimal.ZERO);
        turnoDetalle.setTiempoProceso(BigDecimal.ZERO);

        turno.setTurnoDetalleList(Arrays.asList(turnoDetalle));

        try {
            turnoCtrl.create(turno);
            MessageUtils.sendSuccessfulMessage("Turno creado " + turno.getHappyNumber());

            imprimir(turno);

            init();
        } catch (Exception ex) {
            MessageUtils.sendErrorMessage("Error creando turno ");
        }
    }

    public void nuevo() {
        init();
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

    public void imprimir(Turno turno) {

        if (!isPrinterConnected()) {

            MessageUtils.sendSuccessfulMessage("El printer no esta conectado!");
            return;
        }

        Message mes = new Message(MessageType.PRINT);
        Cliente cli = turno.getIdcliente();
        TurnoDetalle td = turno.getTurnoDetalleList().get(0);

        mes.put("contrato", cli.getContrato().toString())
                .put("fecha", DateUtils.dateTime2String(turno.getFechaCreacion()))
                .put("nombreCliente", cli.getNombre() + " " + cli.getApellido())
                .put("oficina", turno.getIdoficina().getNombre())
                .put("servicio", td.getIdservicio().getNombre())
                .put("turno", turno.getHappyNumber());

        wsPrinter.sendMessage(turno.getIdoficina(), mes);

        MessageUtils.sendSuccessfulMessage("El turno se envio a imprimir!");
    }

}
