/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "TIPO_ESTACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoEstacion.findAll", query = "SELECT t FROM TipoEstacion t")})
public class TipoEstacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDTIPO_ESTACION")
    @SequenceGenerator(name = "SEC_TIPO_ESTACION", sequenceName = "SEC_TIPO_ESTACION", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_TIPO_ESTACION")
    private Integer idtipoEstacion;
    @Size(max = 65)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 200)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 100)
    @Column(name = "TIPO_INTERNO")
    private String tipoInterno;
    @OneToMany(mappedBy = "idtipoEstacion")
    private List<Estacion> estacionList;

    public TipoEstacion() {
    }

    public TipoEstacion(Integer idtipoEstacion) {
        this.idtipoEstacion = idtipoEstacion;
    }

    public Integer getIdtipoEstacion() {
        return idtipoEstacion;
    }

    public void setIdtipoEstacion(Integer idtipoEstacion) {
        this.idtipoEstacion = idtipoEstacion;
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

    public String getTipoInterno() {
        return tipoInterno;
    }

    public void setTipoInterno(String tipoInterno) {
        this.tipoInterno = tipoInterno;
    }

    @XmlTransient
    public List<Estacion> getEstacionList() {
        return estacionList;
    }

    public void setEstacionList(List<Estacion> estacionList) {
        this.estacionList = estacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipoEstacion != null ? idtipoEstacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEstacion)) {
            return false;
        }
        TipoEstacion other = (TipoEstacion) object;
        if ((this.idtipoEstacion == null && other.idtipoEstacion != null) || (this.idtipoEstacion != null && !this.idtipoEstacion.equals(other.idtipoEstacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aniuska.turnos.entity.TipoEstacion[ idtipoEstacion=" + idtipoEstacion + " ]";
    }

}
