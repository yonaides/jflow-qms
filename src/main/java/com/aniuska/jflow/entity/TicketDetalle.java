/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hectorvent@gmail.com
 */
@Entity
@Table(name = "TICKET_DETALLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TicketDetalle.findAll", query = "SELECT t FROM TicketDetalle t")})
public class TicketDetalle implements Serializable {

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIEMPO_ESPERA")
    private BigDecimal tiempoEspera;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIEMPO_PROCESO")
    private BigDecimal tiempoProceso;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDTICKET_DETALLE")
    @SequenceGenerator(name = "SEC_TICKET_DETALLE", sequenceName = "SEC_TICKET_DETALLE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_TICKET_DETALLE")
    private BigDecimal idticketDetalle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "FECHA_INICIO_ATENCION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicioAtencion;
    @Column(name = "FECHA_FIN_ATENCION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinAtencion;
    @Lob
    @Column(name = "OBSERVACION")
    private String observacion;
    @JoinColumn(name = "IDESTACION", referencedColumnName = "IDESTACION")
    @ManyToOne
    private Estacion idestacion;
    @JoinColumn(name = "IDESTADO", referencedColumnName = "IDESTADO")
    @ManyToOne(optional = false)
    private Estado idestado;
    @JoinColumn(name = "IDMOTIVO_ABANDONO", referencedColumnName = "IDMOTIVO_ABANDONO")
    @ManyToOne
    private MotivoAbandono idmotivoAbandono;
    @JoinColumn(name = "IDSERVICIO", referencedColumnName = "IDSERVICIO")
    @ManyToOne(optional = false)
    private Servicio idservicio;
    @JoinColumn(name = "IDTICKET", referencedColumnName = "IDTICKET")
    @ManyToOne(optional = false)
    private Ticket idticket;
    @JoinColumn(name = "IDOPERADOR", referencedColumnName = "IDOPERADOR")
    @ManyToOne
    private Usuario idoperador;

    public TicketDetalle() {
    }

    public TicketDetalle(BigDecimal idturnoDetalle) {
        this.idticketDetalle = idturnoDetalle;
    }

    public TicketDetalle(BigDecimal idturnoDetalle, Date fechaInicio) {
        this.idticketDetalle = idturnoDetalle;
        this.fechaInicio = fechaInicio;
    }

    public BigDecimal getIdticketDetalle() {
        return idticketDetalle;
    }

    public void setIdticketDetalle(BigDecimal idticketDetalle) {
        this.idticketDetalle = idticketDetalle;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaInicioAtencion() {
        return fechaInicioAtencion;
    }

    public void setFechaInicioAtencion(Date fechaInicioAtencion) {
        this.fechaInicioAtencion = fechaInicioAtencion;
    }

    public Date getFechaFinAtencion() {
        return fechaFinAtencion;
    }

    public void setFechaFinAtencion(Date fechaFinAtencion) {
        this.fechaFinAtencion = fechaFinAtencion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Estacion getIdestacion() {
        return idestacion;
    }

    public void setIdestacion(Estacion idestacion) {
        this.idestacion = idestacion;
    }

    public Estado getIdestado() {
        return idestado;
    }

    public void setIdestado(Estado idestado) {
        this.idestado = idestado;
    }

    public MotivoAbandono getIdmotivoAbandono() {
        return idmotivoAbandono;
    }

    public void setIdmotivoAbandono(MotivoAbandono idmotivoAbandono) {
        this.idmotivoAbandono = idmotivoAbandono;
    }

    public Servicio getIdservicio() {
        return idservicio;
    }

    public void setIdservicio(Servicio idservicio) {
        this.idservicio = idservicio;
    }

    public Ticket getIdticket() {
        return idticket;
    }

    public void setIdticket(Ticket idticket) {
        this.idticket = idticket;
    }

    public Usuario getIdoperador() {
        return idoperador;
    }

    public void setIdoperador(Usuario idoperador) {
        this.idoperador = idoperador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idticketDetalle != null ? idticketDetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TicketDetalle)) {
            return false;
        }
        TicketDetalle other = (TicketDetalle) object;
        if ((this.idticketDetalle == null && other.idticketDetalle != null) || (this.idticketDetalle != null && !this.idticketDetalle.equals(other.idticketDetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aniuska.turnos.entity.TurnoDetalle[ idturnoDetalle=" + idticketDetalle + " ]";
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

}
