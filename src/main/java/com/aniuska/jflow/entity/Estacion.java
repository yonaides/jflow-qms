/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.entity;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hectorvent@gmail.com
 */
@Entity
@Table(name = "ESTACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estacion.findAll", query = "SELECT e FROM Estacion e")})
public class Estacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDESTACION")
    @SequenceGenerator(name = "SEC_ESTACION", sequenceName = "SEC_ESTACION", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_ESTACION")
    private Integer idestacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 155)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_ESTACION")
    private int numeroEstacion;
    @JoinColumn(name = "IDSUCURSAL", referencedColumnName = "IDSUCURSAL")
    @ManyToOne(optional = false)
    private Sucursal idsucursal;
    @JoinColumn(name = "IDTIPO_ESTACION", referencedColumnName = "IDTIPO_ESTACION")
    @ManyToOne(optional = false)
    private TipoEstacion idtipoEstacion;
    @OneToMany(mappedBy = "idestacion")
    private List<TicketDetalle> turnoDetalleList;
    @OneToMany( mappedBy = "idestacion")
    private List<Session> sessionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idestacion")
    private List<EstacionServicio> estacionServicioList;

    public Estacion() {
    }

    public Estacion(Integer idestacion) {
        this.idestacion = idestacion;
    }

    public Estacion(Integer idestacion, String nombre, String descripcion, int numeroEstacion) {
        this.idestacion = idestacion;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.numeroEstacion = numeroEstacion;
    }

    public Integer getIdestacion() {
        return idestacion;
    }

    public void setIdestacion(Integer idestacion) {
        this.idestacion = idestacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNumeroEstacion() {
        return numeroEstacion;
    }

    public void setNumeroEstacion(int numeroEstacion) {
        this.numeroEstacion = numeroEstacion;
    }

    public Sucursal getIdsucursal() {
        return idsucursal;
    }

    public void setIdsucursal(Sucursal idsucursal) {
        this.idsucursal = idsucursal;
    }

    public TipoEstacion getIdtipoEstacion() {
        return idtipoEstacion;
    }

    public void setIdtipoEstacion(TipoEstacion idtipoEstacion) {
        this.idtipoEstacion = idtipoEstacion;
    }

    @XmlTransient
    public List<TicketDetalle> getTurnoDetalleList() {
        return turnoDetalleList;
    }

    public void setTurnoDetalleList(List<TicketDetalle> turnoDetalleList) {
        this.turnoDetalleList = turnoDetalleList;
    }

    @XmlTransient
    public List<Session> getSessionesList() {
        return sessionesList;
    }

    public void setSessionesList(List<Session> sessionesList) {
        this.sessionesList = sessionesList;
    }

    @XmlTransient
    public List<EstacionServicio> getEstacionServicioList() {
        return estacionServicioList;
    }

    public void setEstacionServicioList(List<EstacionServicio> estacionServicioList) {
        this.estacionServicioList = estacionServicioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idestacion != null ? idestacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estacion)) {
            return false;
        }
        Estacion other = (Estacion) object;
        if ((this.idestacion == null && other.idestacion != null) || (this.idestacion != null && !this.idestacion.equals(other.idestacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aniuska.turnos.entity.Estacion[ idestacion=" + idestacion + " ]";
    }

}
