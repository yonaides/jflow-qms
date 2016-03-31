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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "ESTADO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estado.findAll", query = "SELECT e FROM Estado e")})
public class Estado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDESTADO")
    private Integer idestado;
    @Size(max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idestado")
    private List<TicketDetalle> turnoDetalleList;
    @OneToMany(mappedBy = "idestado")
    private List<Ticket> turnoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idestado")
    private List<Session> sessionesList;

    public Estado() {
    }

    public Estado(Integer idestado) {
        this.idestado = idestado;
    }

    public Integer getIdestado() {
        return idestado;
    }

    public void setIdestado(Integer idestado) {
        this.idestado = idestado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<TicketDetalle> getTurnoDetalleList() {
        return turnoDetalleList;
    }

    public void setTurnoDetalleList(List<TicketDetalle> turnoDetalleList) {
        this.turnoDetalleList = turnoDetalleList;
    }

    @XmlTransient
    public List<Ticket> getTurnoList() {
        return turnoList;
    }

    public void setTurnoList(List<Ticket> turnoList) {
        this.turnoList = turnoList;
    }

    @XmlTransient
    public List<Session> getSessionesList() {
        return sessionesList;
    }

    public void setSessionesList(List<Session> sessionesList) {
        this.sessionesList = sessionesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idestado != null ? idestado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estado)) {
            return false;
        }
        Estado other = (Estado) object;
        if ((this.idestado == null && other.idestado != null) || (this.idestado != null && !this.idestado.equals(other.idestado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aniuska.turnos.entity.Estado[ idestado=" + idestado + " ]";
    }

}
