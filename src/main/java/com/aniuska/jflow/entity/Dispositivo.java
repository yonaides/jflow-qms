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
@Table(name = "DISPOSITIVO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dispositivo.findAll", query = "SELECT d FROM Dispositivo d")})
public class Dispositivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "IDDISPOSITIVO")
    private String iddispositivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 55)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "ULTIMA_CONEXION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaConexion;

    @JoinColumn(name = "IDSUCURSAL", referencedColumnName = "IDSUCURSAL")
    @ManyToOne(optional = false)
    private Sucursal idsucursal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 55)
    @Column(name = "VERSION_DISPOSITIVO")
    private String versionDispositivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 55)
    @Column(name = "TIPO_DISPOSITIVO")
    private String tipoDispositivo;

    public Dispositivo() {
    }

    public Dispositivo(String idkiosco) {
        this.iddispositivo = idkiosco;
    }

    public Dispositivo(String idkiosco, String descripcion) {
        this.iddispositivo = idkiosco;
        this.descripcion = descripcion;
    }

    public String getIddispositivo() {
        return iddispositivo;
    }

    public void setIddispositivo(String iddispositivo) {
        this.iddispositivo = iddispositivo;
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

    public Sucursal getIdsucursal() {
        return idsucursal;
    }

    public void setIdsucursal(Sucursal idsucursal) {
        this.idsucursal = idsucursal;
    }

    public String getVersionDispositivo() {
        return versionDispositivo;
    }

    public void setVersionDispositivo(String versionDispositivo) {
        this.versionDispositivo = versionDispositivo;
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
        hash += (iddispositivo != null ? iddispositivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dispositivo)) {
            return false;
        }
        Dispositivo other = (Dispositivo) object;
        return !((this.iddispositivo == null && other.iddispositivo != null) || (this.iddispositivo != null && !this.iddispositivo.equals(other.iddispositivo)));
    }

    @Override
    public String toString() {
        return "Dispositivo{" + "iddispositivo=" + iddispositivo + ", descripcion=" + descripcion + ", ultimaConexion=" + ultimaConexion + ", idsucursal=" + idsucursal + ", versionDispositivo=" + versionDispositivo + ", tipoDispositivo=" + tipoDispositivo + '}';
    }

}
