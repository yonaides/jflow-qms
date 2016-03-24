/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edenorte.turnos.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hventura@citrus.com.do
 */
@Entity
@Table(name = "ESPERA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Espera.findAll", query = "SELECT e FROM Espera e")})
public class Espera implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDESPERA")
    @SequenceGenerator(name = "SEC_ESPERA", sequenceName = "SEC_ESPERA", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_ESPERA")
    private BigDecimal idespera;
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "FECHA_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Column(name = "ENESPERA")
    private Character enespera;
    @JoinColumn(name = "IDMOTIVO_RECESO", referencedColumnName = "IDMOTIVO_RECESO")
    @ManyToOne(optional = false)
    private MotivoReceso idmotivoReceso;
    @JoinColumn(name = "IDSESSION", referencedColumnName = "IDSESSION")
    @ManyToOne(optional = false)
    private Sessiones idsession;

    public Espera() {
    }

    public Espera(BigDecimal idespera) {
        this.idespera = idespera;
    }

    public BigDecimal getIdespera() {
        return idespera;
    }

    public void setIdespera(BigDecimal idespera) {
        this.idespera = idespera;
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

    public Character getEnespera() {
        return enespera;
    }

    public void setEnespera(Character enespera) {
        this.enespera = enespera;
    }

    public MotivoReceso getIdmotivoReceso() {
        return idmotivoReceso;
    }

    public void setIdmotivoReceso(MotivoReceso idmotivoReceso) {
        this.idmotivoReceso = idmotivoReceso;
    }

    public Sessiones getIdsession() {
        return idsession;
    }

    public void setIdsession(Sessiones idsession) {
        this.idsession = idsession;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idespera != null ? idespera.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Espera)) {
            return false;
        }
        Espera other = (Espera) object;
        if ((this.idespera == null && other.idespera != null) || (this.idespera != null && !this.idespera.equals(other.idespera))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.edenorte.turnos.entity.Espera[ idespera=" + idespera + " ]";
    }

}
