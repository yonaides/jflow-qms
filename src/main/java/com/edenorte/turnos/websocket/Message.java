/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edenorte.turnos.websocket;

import com.google.gson.JsonObject;

/**
 *
 * @author hventura@citrus.com.do
 */
public class Message {

    private String tipoMensaje;
    private JsonObject mensaje = new JsonObject();

    public Message() {
    }

    public Message(String tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }

    public JsonObject getMensaje() {
        return mensaje;
    }

    public Message setMensaje(JsonObject mensaje) {
        this.mensaje = mensaje;
        return this;
    }

    public String getTipoMensaje() {
        return tipoMensaje;
    }

    public Message setTipoMensaje(String tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
        return this;
    }

    public Message put(String key, Object obj) {
        mensaje.add(key, GsonUtils.toJsonTree(obj));
        return this;
    }

    @Override
    public String toString() {
        return "Message{" + "tipoMensaje=" + tipoMensaje + ", mensaje=" + mensaje + '}';
    }

}
