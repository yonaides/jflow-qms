/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.managedbean;

import com.aniuska.jflow.auth.AuthenticationBean;
import com.aniuska.jflow.ejb.ClienteFacade;
import com.aniuska.jflow.ejb.DispositivoFacade;
import com.aniuska.jflow.ejb.SucursalFacade;
import com.aniuska.jflow.ejb.ServicioFacade;
import com.aniuska.jflow.ejb.TicketFacade;
import com.aniuska.jflow.entity.Cliente;
import com.aniuska.jflow.entity.Sucursal;
import com.aniuska.jflow.entity.Servicio;
import com.aniuska.jflow.entity.Ticket;
import com.aniuska.jflow.entity.TicketDetalle;
import com.aniuska.jflow.utils.Estados;
import com.aniuska.jflow.websocket.Message;
import com.aniuska.jflow.websocket.MessageType;
import com.aniuska.jflow.websocket.WSKioscoInf;
import com.aniuska.jflow.websocket.WSPrinter;
import com.aniuska.utils.MessageUtils;
import com.aniuska.utils.date.DateUtils;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class GenerarTicketBean implements Serializable {

    private final long serialVersionUID = 23L;
    private static final Logger LOG = Logger.getLogger(GenerarTicketBean.class.getName());

    @EJB
    private TicketFacade turnoCtrl;
    @EJB
    private ClienteFacade clienteCtrl;
    @EJB
    private ServicioFacade servicioCtrl;
    @EJB
    private SucursalFacade ctrlOficina;
    
/*    @EJB
    private DispositivoFacade dispositivo;*/
    
    @Inject
    private AuthenticationBean authenticationBean;

    @EJB
    private WSPrinter wsPrinter;
    
    @EJB
    private WSKioscoInf wsKiosko ;

    private TicketDetalle ticketDetalle;
    private Cliente cliente;
    private boolean prioritario = false;

    @PostConstruct
    public void init() {
        ticketDetalle = new TicketDetalle();
        cliente = new Cliente();
    }

    public void setTurnoCtrl(TicketFacade turnoCtrl) {
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

    public TicketDetalle getTicketDetalle() {
        return ticketDetalle;
    }

    public List<Servicio> getServicios() {
        Sucursal sucursal = authenticationBean.getUsuario().getIdsucursal();
        return servicioCtrl.findBySucursal(sucursal);
    }

    public void setTicketDetalle(TicketDetalle ticketDetalle) {
        this.ticketDetalle = ticketDetalle;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setCtrlOficina(SucursalFacade ctrlOficina) {
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

    public List<Ticket> getTurnos() {
        Sucursal sucursal = authenticationBean.getUsuario().getIdsucursal();
        List<Ticket> turnos = turnoCtrl.findLast10(sucursal);
        turnos.forEach(  (tik)-> {
        
            if (Objects.equals(tik.getIdestado().getIdestado(), Estados.EN_ESPERA.getIdestado())) {
                sendMessageToKiosko(tik);
            }
        });
        
        return turnos;
    }

    public boolean isPrinterConnected() {
        Sucursal ofi = authenticationBean.getUsuario().getIdsucursal();
        //return true;
        return wsPrinter.isConnected(ofi);
    }

    public void salvar() {
        
        cliente = clienteCtrl.findAll().stream().findFirst().get();
        
        System.out.println(cliente);
        
        if (cliente.getIdcliente() == null) {
            cliente.setFechaIngreso(new Date());
            cliente.setIdusuarioIngreso(authenticationBean.getUsuario());
            clienteCtrl.create(cliente);
        }
        

        Sucursal ofi = authenticationBean.getUsuario().getIdsucursal();
        ofi = ctrlOficina.find(ofi.getIdsucursal());
        int num = ofi.getSecuencia() + 1;

        // Se el numero es 100 resetiar a 1, (1-99)
        if (num == 100) {
            num = 1;
        }
        
        ofi.setSecuencia(num);
        // Se actualiza la secuencia de la sucursal
        ctrlOficina.edit(ofi);
        Servicio ser = ticketDetalle.getIdservicio();
        
        Ticket turno = new Ticket();
        turno.setFechaCreacion(new Date());
        turno.setHappyNumber(ser.getPrefijo() + "-" + num);
        turno.setIdsucursal(ofi);
        turno.setIdcliente(cliente);
        turno.setPrioridad(prioritario ? 2 : 1);
        turno.setIdestado(Estados.EN_ESPERA);

        ticketDetalle.setIdticket(turno);
        ticketDetalle.setFechaInicio(turno.getFechaCreacion());
        ticketDetalle.setIdestado(Estados.EN_ESPERA);
        ticketDetalle.setTiempoEspera(BigDecimal.ZERO);
        ticketDetalle.setTiempoProceso(BigDecimal.ZERO);

        turno.setTicketDetalleList(Arrays.asList(ticketDetalle));

        try {
            turnoCtrl.create(turno);
            MessageUtils.sendSuccessfulMessage("Turno creado " + turno.getHappyNumber());

            imprimir(turno);

            init();
        } catch (Exception ex) {
            MessageUtils.sendErrorMessage("Error creando turno " );
            LOG.log(Level.SEVERE, "Error al crear el turno " + ex.getMessage());
            
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
            MessageUtils.sendSuccessfulMessage("Error al consultar los clientes!!");
        }
    }

    public void imprimir(Ticket turno) {

        if (!isPrinterConnected()) {
            MessageUtils.sendSuccessfulMessage("El printer no esta conectado!");
            return;
        }

        Cliente cli = turno.getIdcliente();
        TicketDetalle td = turno.getTicketDetalleList().get(0);

        Message mes = new Message(MessageType.PRINT);
        mes.put("cuenta", cli.getContrato().toString())
                .put("fecha", DateUtils.dateTime2String(turno.getFechaCreacion()))
                .put("nombre", cli.toString())
                .put("oficicina", turno.getIdsucursal().getNombre())
                .put("servicio", td.getIdservicio().getNombre())
                .put("turno", turno.getHappyNumber())
                .put("empresa", "JFlow QMS");

        wsPrinter.sendMessage(turno.getIdsucursal(), mes);

        MessageUtils.sendSuccessfulMessage("El turno se envio a imprimir!");
    }
    
    private void sendMessageToKiosko(Ticket turno){
        
        Sucursal ofi = authenticationBean.getUsuario().getIdsucursal();
        
        Message nm = new Message(MessageType.REFRESH);
        nm.put("turno", turno.getHappyNumber());
        
        wsKiosko.sendMessage(ofi, nm);
        
        
    }

}
