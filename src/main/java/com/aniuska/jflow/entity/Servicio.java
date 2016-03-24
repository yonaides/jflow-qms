/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
 * @author hventura@citrus.com.do
 */
@Entity
@Table(name = "SERVICIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Servicio.findAll", query = "SELECT s FROM Servicio s")})
public class Servicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDSERVICIO")
    @SequenceGenerator(name = "SEC_SERVICIO", sequenceName = "SEC_SERVICIO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_SERVICIO")
    private Integer idservicio;
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 150)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "TIEMPO_PROM")
    private BigInteger tiempoProm;
    @Size(max = 200)
    @Column(name = "PATH_ICON")
    private String pathIcon;
    @Size(max = 3)
    @Column(name = "PREFIJO")
    private String prefijo;
    @ManyToMany(mappedBy = "servicioList")
    private List<Oficina> oficinaList;
    @OneToMany( mappedBy = "idservicio")
    private List<TurnoDetalle> turnoDetalleList;
    @OneToMany( mappedBy = "idservicio")
    private List<EstacionServicio> estacionServicioList;

    public Servicio() {
    }

    public Servicio(Integer idservicio) {
        this.idservicio = idservicio;
    }

    public Integer getIdservicio() {
        return idservicio;
    }

    public void setIdservicio(Integer idservicio) {
        this.idservicio = idservicio;
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

    public BigInteger getTiempoProm() {
        return tiempoProm;
    }

    public void setTiempoProm(BigInteger tiempoProm) {
        this.tiempoProm = tiempoProm;
    }

    public String getPathIcon() {
        return pathIcon;
    }

    public void setPathIcon(String pathIcon) {
        this.pathIcon = pathIcon;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    @XmlTransient
    public List<Oficina> getOficinaList() {
        return oficinaList;
    }

    public void setOficinaList(List<Oficina> oficinaList) {
        this.oficinaList = oficinaList;
    }

    @XmlTransient
    public List<TurnoDetalle> getTurnoDetalleList() {
        return turnoDetalleList;
    }

    public void setTurnoDetalleList(List<TurnoDetalle> turnoDetalleList) {
        this.turnoDetalleList = turnoDetalleList;
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
        hash += (idservicio != null ? idservicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servicio)) {
            return false;
        }
        Servicio other = (Servicio) object;
        if ((this.idservicio == null && other.idservicio != null) || (this.idservicio != null && !this.idservicio.equals(other.idservicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.edenorte.turnos.entity.Servicio[ idservicio=" + idservicio + " ]";
    }

}
