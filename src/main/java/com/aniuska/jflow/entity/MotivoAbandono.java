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
 * @author hventura@citrus.com.do
 */
@Entity
@Table(name = "MOTIVO_ABANDONO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MotivoAbandono.findAll", query = "SELECT m FROM MotivoAbandono m")})
public class MotivoAbandono implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDMOTIVO_ABANDONO")
    @SequenceGenerator(name = "SEC_MOTIVO_ABANDONO", sequenceName = "SEC_MOTIVO_ABANDONO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_MOTIVO_ABANDONO")
    private Integer idmotivoAbandono;
    @Size(max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "idmotivoAbandono")
    private List<TurnoDetalle> turnoDetalleList;

    public MotivoAbandono() {
    }

    public MotivoAbandono(Integer idmotivoAbandono) {
        this.idmotivoAbandono = idmotivoAbandono;
    }

    public Integer getIdmotivoAbandono() {
        return idmotivoAbandono;
    }

    public void setIdmotivoAbandono(Integer idmotivoAbandono) {
        this.idmotivoAbandono = idmotivoAbandono;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<TurnoDetalle> getTurnoDetalleList() {
        return turnoDetalleList;
    }

    public void setTurnoDetalleList(List<TurnoDetalle> turnoDetalleList) {
        this.turnoDetalleList = turnoDetalleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmotivoAbandono != null ? idmotivoAbandono.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MotivoAbandono)) {
            return false;
        }
        MotivoAbandono other = (MotivoAbandono) object;
        if ((this.idmotivoAbandono == null && other.idmotivoAbandono != null) || (this.idmotivoAbandono != null && !this.idmotivoAbandono.equals(other.idmotivoAbandono))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.edenorte.turnos.entity.MotivoAbandono[ idmotivoAbandono=" + idmotivoAbandono + " ]";
    }

}
