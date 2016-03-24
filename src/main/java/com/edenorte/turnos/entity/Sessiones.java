/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edenorte.turnos.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hventura@citrus.com.do
 */
@Entity
@Table(name = "SESSIONES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sessiones.findAll", query = "SELECT s FROM Sessiones s")})
public class Sessiones implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDSESSION")
    @SequenceGenerator(name = "SEC_SESSION", sequenceName = "SEC_SESSION", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_SESSION")
    private BigDecimal idsession;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "FECHA_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Column(name = "SEGUNDO_ESPERA")
    private BigInteger segundoEspera;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsession")
    private List<Espera> esperaList;
    @JoinColumn(name = "IDESTACION", referencedColumnName = "IDESTACION")
    @ManyToOne(optional = false)
    private Estacion idestacion;
    @JoinColumn(name = "IDESTADO", referencedColumnName = "IDESTADO")
    @ManyToOne(optional = false)
    private Estado idestado;
    @JoinColumn(name = "IDOPERADOR", referencedColumnName = "IDOPERADOR")
    @ManyToOne(optional = false)
    private Usuario idoperador;

    public Sessiones() {
    }

    public Sessiones(BigDecimal idsession) {
        this.idsession = idsession;
    }

    public Sessiones(BigDecimal idsession, Date fechaInicio) {
        this.idsession = idsession;
        this.fechaInicio = fechaInicio;
    }

    public BigDecimal getIdsession() {
        return idsession;
    }

    public void setIdsession(BigDecimal idsession) {
        this.idsession = idsession;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public BigInteger getSegundoEspera() {
        return segundoEspera;
    }

    public void setSegundoEspera(BigInteger segundoEspera) {
        this.segundoEspera = segundoEspera;
    }

    @XmlTransient
    public List<Espera> getEsperaList() {
        return esperaList;
    }

    public void setEsperaList(List<Espera> esperaList) {
        this.esperaList = esperaList;
    }

    public Estacion getIdestacion() {
        return idestacion;
    }

    public void setIdestacion(Estacion idestacion) {
        this.idestacion = idestacion;
    }

    public Estado getIdestado() {
        return idestado;
    }

    public void setIdestado(Estado idestado) {
        this.idestado = idestado;
    }

    public Usuario getIdoperador() {
        return idoperador;
    }

    public void setIdoperador(Usuario idoperador) {
        this.idoperador = idoperador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsession != null ? idsession.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sessiones)) {
            return false;
        }
        Sessiones other = (Sessiones) object;
        if ((this.idsession == null && other.idsession != null) || (this.idsession != null && !this.idsession.equals(other.idsession))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.edenorte.turnos.entity.Sessiones[ idsession=" + idsession + " ]";
    }

}
