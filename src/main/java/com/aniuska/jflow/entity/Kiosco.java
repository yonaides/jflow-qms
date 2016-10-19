/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hectorvent@gmail.com
 */
@Entity
@Table(name = "KIOSCO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kiosco.findAll", query = "SELECT k FROM Kiosco k")})
public class Kiosco implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "IDKIOSCO")
    private String idkiosco;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 55)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "ULTIMA_CONEXION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaConexion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AUTOSERVICIO")
    private Character autoservicio;
    @JoinColumn(name = "IDSUCURSAL", referencedColumnName = "IDSUCURSAL")
    @ManyToOne(optional = false)
    private Sucursal idsucursal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 55)
    @Column(name = "VERSION_KIOSCO")
    private String versionKiosco;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 55)
    @Column(name = "TIPO_DISPOSITIVO")
    private String tipoDispositivo;

    public Kiosco() {
    }

    public Kiosco(String idkiosco) {
        this.idkiosco = idkiosco;
    }

    public Kiosco(String idkiosco, String descripcion, Character autoservicio) {
        this.idkiosco = idkiosco;
        this.descripcion = descripcion;
        this.autoservicio = autoservicio;
    }

    public String getIdkiosco() {
        return idkiosco;
    }

    public void setIdkiosco(String idkiosco) {
        this.idkiosco = idkiosco;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getUltimaConexion() {
        return ultimaConexion;
    }

    public void setUltimaConexion(Date ultimaConexion) {
        this.ultimaConexion = ultimaConexion;
    }

    public Character getAutoservicio() {
        return autoservicio;
    }

    public void setAutoservicio(Character autoservicio) {
        this.autoservicio = autoservicio;
    }

    public Sucursal getIdsucursal() {
        return idsucursal;
    }

    public void setIdsucursal(Sucursal idsucursal) {
        this.idsucursal = idsucursal;
    }

    public String getVersionKiosco() {
        return versionKiosco;
    }

    public void setVersionKiosco(String versionKiosco) {
        this.versionKiosco = versionKiosco;
    }

    public String getTipoDispositivo() {
        return tipoDispositivo;
    }

    public void setTipoDispositivo(String tipoDispositivo) {
        this.tipoDispositivo = tipoDispositivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idkiosco != null ? idkiosco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kiosco)) {
            return false;
        }
        Kiosco other = (Kiosco) object;
        return !((this.idkiosco == null && other.idkiosco != null) || (this.idkiosco != null && !this.idkiosco.equals(other.idkiosco)));
    }

    @Override
    public String toString() {
        return "com.aniuska.turnos.entity.Kiosco[ idkiosco=" + idkiosco + " ]";
    }

}
