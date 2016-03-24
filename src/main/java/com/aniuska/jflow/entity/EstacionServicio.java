/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hventura@citrus.com.do
 */
@Entity
@Table(name = "ESTACION_SERVICIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstacionServicio.findAll", query = "SELECT e FROM EstacionServicio e")})
public class EstacionServicio implements Serializable {

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDESTACION_SERVICIO")
    @SequenceGenerator(name = "SEC_ESTACION_SERVICIO", sequenceName = "SEC_ESTACION_SERVICIO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_ESTACION_SERVICIO")
    private Integer idestacionServicio;
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HABILITADO")
    private Character habilitado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRIORITARIO")
    private short prioritario;

    @JoinColumn(name = "IDESTACION", referencedColumnName = "IDESTACION")
    @ManyToOne(optional = false)
    private Estacion idestacion;
    @JoinColumn(name = "IDSERVICIO", referencedColumnName = "IDSERVICIO")
    @ManyToOne(optional = false)
    private Servicio idservicio;

    public EstacionServicio() {
    }

    public EstacionServicio(Integer idestacionServicio) {
        this.idestacionServicio = idestacionServicio;
    }

    public EstacionServicio(Integer idestacionServicio, Character habilitado, short prioritario) {
        this.idestacionServicio = idestacionServicio;
        this.habilitado = habilitado;
        this.prioritario = prioritario;
    }

    public Character getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Character habilitado) {
        this.habilitado = habilitado;
    }

    public short getPrioritario() {
        return prioritario;
    }

    public void setPrioritario(short prioritario) {
        this.prioritario = prioritario;
    }

    public Integer getIdestacionServicio() {
        return idestacionServicio;
    }

    public void setIdestacionServicio(Integer idestacionServicio) {
        this.idestacionServicio = idestacionServicio;
    }

    public Estacion getIdestacion() {
        return idestacion;
    }

    public void setIdestacion(Estacion idestacion) {
        this.idestacion = idestacion;
    }

    public Servicio getIdservicio() {
        return idservicio;
    }

    public void setIdservicio(Servicio idservicio) {
        this.idservicio = idservicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idestacionServicio != null ? idestacionServicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstacionServicio)) {
            return false;
        }
        EstacionServicio other = (EstacionServicio) object;
        if ((this.idestacionServicio == null && other.idestacionServicio != null) || (this.idestacionServicio != null && !this.idestacionServicio.equals(other.idestacionServicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.edenorte.turnos.entity.EstacionServicio[ idestacionServicio=" + idestacionServicio + " ]";
    }

}
