/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aniuska.jflow.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
 * @author hventura@citrus.com.do
 */
@Entity
@Table(name = "MOTIVO_RECESO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MotivoReceso.findAll", query = "SELECT m FROM MotivoReceso m")})
public class MotivoReceso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDMOTIVO_RECESO")
    @SequenceGenerator(name = "SEC_MOTIVO_RECESO", sequenceName = "SEC_MOTIVO_RECESO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_MOTIVO_RECESO")
    private Integer idmotivoReceso;
    @Size(max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idmotivoReceso")
    private List<Espera> esperaList;

    public MotivoReceso() {
    }

    public MotivoReceso(Integer idmotivoReceso) {
        this.idmotivoReceso = idmotivoReceso;
    }

    public Integer getIdmotivoReceso() {
        return idmotivoReceso;
    }

    public void setIdmotivoReceso(Integer idmotivoReceso) {
        this.idmotivoReceso = idmotivoReceso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Espera> getEsperaList() {
        return esperaList;
    }

    public void setEsperaList(List<Espera> esperaList) {
        this.esperaList = esperaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmotivoReceso != null ? idmotivoReceso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MotivoReceso)) {
            return false;
        }
        MotivoReceso other = (MotivoReceso) object;
        if ((this.idmotivoReceso == null && other.idmotivoReceso != null) || (this.idmotivoReceso != null && !this.idmotivoReceso.equals(other.idmotivoReceso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.edenorte.turnos.entity.MotivoReceso[ idmotivoReceso=" + idmotivoReceso + " ]";
    }

}
