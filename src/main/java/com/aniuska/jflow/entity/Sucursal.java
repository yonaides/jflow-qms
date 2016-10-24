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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "SUCURSAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sucursal.findAll", query = "SELECT o FROM Sucursal o")})
public class Sucursal implements Serializable {

    @JoinColumn(name = "IDSECTOR", referencedColumnName = "IDSECTOR")
    @ManyToOne(optional = false)
    private Sector idsector;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDSUCURSAL")
    @SequenceGenerator(name = "SEC_SUCURSAL", sequenceName = "SEC_SUCURSAL", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_SUCURSAL")
    private Integer idsucursal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 150)
    @Column(name = "DIRECCION")
    private String direccion;
    @Size(max = 52)
    @Column(name = "TELEFONO")
    private String telefono;
    @Size(max = 65)
    @Column(name = "CORDENADAS")
    private String cordenadas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_SUCURSAL")
    private int numeroSucursal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SECUENCIA")
    private int secuencia;
    @JoinTable(name = "SUCURSAL_SERVICIO", joinColumns = {
        @JoinColumn(name = "IDSUCURSAL", referencedColumnName = "IDSUCURSAL")}, inverseJoinColumns = {
        @JoinColumn(name = "IDSERVICIO", referencedColumnName = "IDSERVICIO")})
    @ManyToMany
    private List<Servicio> servicioList;
    @OneToMany(mappedBy = "idsucursal")
    private List<Estacion> estacionList;
    @OneToMany(mappedBy = "idsucursal")
    private List<Dispositivo> dispositivoList;
    @OneToMany(mappedBy = "idsucursal")
    private List<Usuario> usuarioList;
    @OneToMany(mappedBy = "idsucursal")
    private List<Ticket> turnoList;

    public Sucursal() {
    }

    public Sucursal(Integer sucursal) {
        this.idsucursal = sucursal;
    }

    public Sucursal(Integer sucursal, String nombre, int numeroOficina, int secuencia) {
        this.idsucursal = sucursal;
        this.nombre = nombre;
        this.numeroSucursal = numeroOficina;
        this.secuencia = secuencia;
    }

    public Integer getIdsucursal() {
        return idsucursal;
    }

    public void setIdsucursal(Integer idsucursal) {
        this.idsucursal = idsucursal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCordenadas() {
        return cordenadas;
    }

    public void setCordenadas(String cordenadas) {
        this.cordenadas = cordenadas;
    }

    public int getNumeroSucursal() {
        return numeroSucursal;
    }

    public void setNumeroSucursal(int numeroSucursal) {
        this.numeroSucursal = numeroSucursal;
    }

    public int getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
    }

    @XmlTransient
    public List<Servicio> getServicioList() {
        return servicioList;
    }

    public void setServicioList(List<Servicio> servicioList) {
        this.servicioList = servicioList;
    }

    @XmlTransient
    public List<Estacion> getEstacionList() {
        return estacionList;
    }

    public void setEstacionList(List<Estacion> estacionList) {
        this.estacionList = estacionList;
    }

    @XmlTransient
    public List<Dispositivo> getDispositivoList() {
        return dispositivoList;
    }

    public void setDispositivoList(List<Dispositivo> dispositivoList) {
        this.dispositivoList = dispositivoList;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @XmlTransient
    public List<Ticket> getTurnoList() {
        return turnoList;
    }

    public void setTurnoList(List<Ticket> turnoList) {
        this.turnoList = turnoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsucursal != null ? idsucursal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sucursal)) {
            return false;
        }
        Sucursal other = (Sucursal) object;
        if ((this.idsucursal == null && other.idsucursal != null) || (this.idsucursal != null && !this.idsucursal.equals(other.idsucursal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Oficina{" + "idsucursal=" + idsucursal + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono=" + telefono + ", cordenadas=" + cordenadas + ", numeroOficina=" + numeroSucursal + ", secuencia=" + secuencia + '}';
    }

    public Sector getIdsector() {
        return idsector;
    }

    public void setIdsector(Sector idsector) {
        this.idsector = idsector;
    }

}
