/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edenorte.turnos.entity;

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
 * @author hventura@citrus.com.do
 */
@Entity
@Table(name = "OFICINA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Oficina.findAll", query = "SELECT o FROM Oficina o")})
public class Oficina implements Serializable {

    @JoinColumn(name = "IDSECTOR", referencedColumnName = "IDSECTOR")
    @ManyToOne(optional = false)
    private Sector idsector;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDOFICINA")
    @SequenceGenerator(name = "SEC_OFICINA", sequenceName = "SEC_OFICINA", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_OFICINA")
    private Integer idoficina;
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
    @Column(name = "NUMERO_OFICINA")
    private int numeroOficina;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SECUENCIA")
    private int secuencia;
    @JoinTable(name = "OFICINA_SERVICIO", joinColumns = {
        @JoinColumn(name = "IDOFICINA", referencedColumnName = "IDOFICINA")}, inverseJoinColumns = {
        @JoinColumn(name = "IDSERVICIO", referencedColumnName = "IDSERVICIO")})
    @ManyToMany
    private List<Servicio> servicioList;
    @OneToMany(mappedBy = "idoficina")
    private List<Estacion> estacionList;
    @OneToMany(mappedBy = "idoficina")
    private List<Kiosco> kioscoList;
    @OneToMany(mappedBy = "idoficina")
    private List<Usuario> usuarioList;
    @OneToMany(mappedBy = "idoficina")
    private List<Turno> turnoList;

    public Oficina() {
    }

    public Oficina(Integer idoficina) {
        this.idoficina = idoficina;
    }

    public Oficina(Integer idoficina, String nombre, int numeroOficina, int secuencia) {
        this.idoficina = idoficina;
        this.nombre = nombre;
        this.numeroOficina = numeroOficina;
        this.secuencia = secuencia;
    }

    public Integer getIdoficina() {
        return idoficina;
    }

    public void setIdoficina(Integer idoficina) {
        this.idoficina = idoficina;
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

    public int getNumeroOficina() {
        return numeroOficina;
    }

    public void setNumeroOficina(int numeroOficina) {
        this.numeroOficina = numeroOficina;
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
    public List<Kiosco> getKioscoList() {
        return kioscoList;
    }

    public void setKioscoList(List<Kiosco> kioscoList) {
        this.kioscoList = kioscoList;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @XmlTransient
    public List<Turno> getTurnoList() {
        return turnoList;
    }

    public void setTurnoList(List<Turno> turnoList) {
        this.turnoList = turnoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idoficina != null ? idoficina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Oficina)) {
            return false;
        }
        Oficina other = (Oficina) object;
        if ((this.idoficina == null && other.idoficina != null) || (this.idoficina != null && !this.idoficina.equals(other.idoficina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Oficina{" + "idoficina=" + idoficina + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono=" + telefono + ", cordenadas=" + cordenadas + ", numeroOficina=" + numeroOficina + ", secuencia=" + secuencia + '}';
    }

    public Sector getIdsector() {
        return idsector;
    }

    public void setIdsector(Sector idsector) {
        this.idsector = idsector;
    }

}
