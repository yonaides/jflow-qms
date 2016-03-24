/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edenorte.turnos.utils;

import com.edenorte.turnos.entity.Sessiones;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author hventura@citrus.com.do
 */
@Named
@SessionScoped
public class MenuController implements Serializable {

    private final String version = "2.0.0.4";
    private String pagina;
    private Sessiones session;

    @PostConstruct
    public void init() {
        pagina = "oficina/oficina";
    }

    public String getPagina() {
        return pagina;
    }

    public Sessiones getSession() {
        return session;
    }

    public String getVersion() {
        return version;
    }
    
    

    public void setSession(Sessiones session) {
        this.session = session;
        pagina = session == null ? "turno/iniciar_session" : "turno/colaturnos";

    }

    public void setPagina(String pagina) {

        if (pagina.equals("turno/colaturnos")) {
            if (session == null) {
                this.pagina = "turno/iniciar_session";
            } else {
                this.pagina = pagina;
            }
        } else {
            this.pagina = pagina;
        }
    }
}
