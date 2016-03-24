/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edenorte.turnos.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
 * @author hventura@citrus.com.do
 */
@Entity
@Table(name = "USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "IDOPERADOR")
    private String idoperador;
    @Size(max = 15)
    @Column(name = "NUMERO_EMP")
    private String numeroEmp;
    @Size(max = 155)
    @Column(name = "CORREO_ELECTRONICO")
    private String correoElectronico;
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 100)
    @Column(name = "APELLIDO")
    private String apellido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HABILITADO")
    private Character habilitado;
    @ManyToMany(mappedBy = "usuarioList")
    private List<Rol> rolList;
    @OneToMany(mappedBy = "idoperador")
    private List<TurnoDetalle> turnoDetalleList;
    @OneToMany(mappedBy = "idusuarioIngreso")
    private List<Cliente> clienteList;
    @JoinColumn(name = "IDOFICINA", referencedColumnName = "IDOFICINA")
    @ManyToOne(optional = false)
    private Oficina idoficina;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idoperador")
    private List<Sessiones> sessionesList;

    public Usuario() {
    }

    public Usuario(String idoperador) {
        this.idoperador = idoperador;
    }

    public Usuario(String idoperador, Character habilitado) {
        this.idoperador = idoperador;
        this.habilitado = habilitado;
    }

    public String getIdoperador() {
        return idoperador;
    }

    public void setIdoperador(String idoperador) {
        this.idoperador = idoperador;
    }

    public String getNumeroEmp() {
        return numeroEmp;
    }

    public void setNumeroEmp(String numeroEmp) {
        this.numeroEmp = numeroEmp;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Character getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Character habilitado) {
        this.habilitado = habilitado;
    }

    @XmlTransient
    public List<Rol> getRolList() {
        return rolList;
    }

    public void setRolList(List<Rol> rolList) {
        this.rolList = rolList;
    }

    @XmlTransient
    public List<TurnoDetalle> getTurnoDetalleList() {
        return turnoDetalleList;
    }

    public void setTurnoDetalleList(List<TurnoDetalle> turnoDetalleList) {
        this.turnoDetalleList = turnoDetalleList;
    }

    @XmlTransient
    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    public Oficina getIdoficina() {
        return idoficina;
    }

    public void setIdoficina(Oficina idoficina) {
        this.idoficina = idoficina;
    }

    @XmlTransient
    public List<Sessiones> getSessionesList() {
        return sessionesList;
    }

    public void setSessionesList(List<Sessiones> sessionesList) {
        this.sessionesList = sessionesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idoperador != null ? idoperador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idoperador == null && other.idoperador != null) || (this.idoperador != null && !this.idoperador.equals(other.idoperador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido;
    }

}
