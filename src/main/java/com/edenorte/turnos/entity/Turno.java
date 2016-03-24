/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edenorte.turnos.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hventura@citrus.com.do
 */
@Entity
@Table(name = "TURNO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Turno.findAll", query = "SELECT t FROM Turno t")})
public class Turno implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDTURNO")
    @SequenceGenerator(name = "SEC_TURNO", sequenceName = "SEC_TURNO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_TURNO")
    private BigDecimal idturno;
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRIORIDAD")
    private int prioridad;
    @Size(max = 4)
    @Basic(optional = false)
    @NotNull
    @Column(name = "HAPPY_NUMBER")
    private String happyNumber;
    @Column(name = "FECHA_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idturno")
    private List<TurnoDetalle> turnoDetalleList;
    @JoinColumn(name = "IDCLIENTE", referencedColumnName = "IDCLIENTE")
    @ManyToOne(optional = false)
    private Cliente idcliente;
    @JoinColumn(name = "IDESTADO", referencedColumnName = "IDESTADO")
    @ManyToOne
    private Estado idestado;
    @JoinColumn(name = "IDOFICINA", referencedColumnName = "IDOFICINA")
    @ManyToOne(optional = false)
    private Oficina idoficina;

    public Turno() {
    }

    public Turno(BigDecimal idturno) {
        this.idturno = idturno;
    }

    public Turno(BigDecimal idturno, int prioridad) {
        this.idturno = idturno;
        this.prioridad = prioridad;
    }

    public BigDecimal getIdturno() {
        return idturno;
    }

    public void setIdturno(BigDecimal idturno) {
        this.idturno = idturno;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public String getHappyNumber() {
        return happyNumber;
    }

    public void setHappyNumber(String happyNumber) {
        this.happyNumber = happyNumber;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @XmlTransient
    public List<TurnoDetalle> getTurnoDetalleList() {
        return turnoDetalleList;
    }

    public void setTurnoDetalleList(List<TurnoDetalle> turnoDetalleList) {
        this.turnoDetalleList = turnoDetalleList;
    }

    public Cliente getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Cliente idcliente) {
        this.idcliente = idcliente;
    }

    public Estado getIdestado() {
        return idestado;
    }

    public void setIdestado(Estado idestado) {
        this.idestado = idestado;
    }

    public Oficina getIdoficina() {
        return idoficina;
    }

    public void setIdoficina(Oficina idoficina) {
        this.idoficina = idoficina;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idturno != null ? idturno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Turno)) {
            return false;
        }
        Turno other = (Turno) object;
        if ((this.idturno == null && other.idturno != null) || (this.idturno != null && !this.idturno.equals(other.idturno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.edenorte.turnos.entity.Turno[ idturno=" + idturno + " ]";
    }

}
