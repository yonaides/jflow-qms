/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.utils;

import com.aniuska.jflow.entity.Session;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author hectorvent@gmail.com
 */
@Named
@SessionScoped
public class MenuController implements Serializable {

    private final String version = "2.0.0.4";
    private String pagina;
    private Session session = null;

    @PostConstruct
    public void init() {
        pagina = "sucursal/sucursal";
    }

    public String getPagina() {
        return pagina;
    }

    public Session getSession() {
        return session;
    }

    public String getVersion() {
        return version;
    }

    public void setSession(Session session) {
        this.session = session;
        pagina = (session == null ? "ticket/iniciar_session" : "ticket/colatickets");

    }

    public void setPagina(String pagina) {

        if (pagina.equals("ticket/colatickets")) {
            if (session == null) {
                this.pagina = "ticket/iniciar_session";
            } else {
                this.pagina = pagina;
            }
        } else {
            this.pagina = pagina;
        }
    }
}
