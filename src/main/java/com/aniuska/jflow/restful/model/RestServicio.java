/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.restful.model;

/**
 *
 * @author hventura@citrus.com.do
 */
public class RestServicio {

    private int servicioId;
    private String nombre;
    private String descripcion;

    public int getServicioId() {
        return servicioId;
    }

    public void setServicioId(int servicioId) {
        this.servicioId = servicioId;
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

    @Override
    public String toString() {
        return "RestServicio{" + "servicioId=" + servicioId + ", nombre=" + nombre + ", descripcion=" + descripcion + '}';
    }

}
