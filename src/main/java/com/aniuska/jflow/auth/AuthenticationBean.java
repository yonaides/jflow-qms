package com.aniuska.jflow.auth;

import com.aniuska.jflow.ejb.SessionesFacade;
import com.aniuska.jflow.ejb.UsuarioFacade;
import com.aniuska.jflow.entity.Session;
import com.aniuska.jflow.entity.Usuario;
import com.aniuska.jflow.utils.MenuController;
import com.aniuska.utils.MessageUtils;
import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author hectorvent@gmail.com
 */
@Named
@SessionScoped
public class AuthenticationBean implements Serializable {

    private static final Logger LOGGER = LogManager.getLogger(AuthenticationBean.class);
    private static final long serialVersionUID = 7765876811740798583L;

    @EJB
    UsuarioFacade usuarioFacade;
    @EJB
    SessionesFacade sessionCtrl;
    @Inject
    MenuController menuController;
    private String username = "";
    private String password;
    private boolean remenberMe;
    private boolean loggedIn;
    private Usuario usuario;
    private Set<String> rols;
    private Session session;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public boolean isRemenberMe() {
        return remenberMe;
    }

    public void setRemenberMe(boolean remenberMe) {
        this.remenberMe = remenberMe;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        LOGGER.info(session);

        this.session = session;
        menuController.setSession(session);
    }

    public void refreshRols() {
        rols.clear();
        usuario = usuarioFacade.find(usuario.getIdoperador());
        usuario.getRolList().stream().forEach(rol -> rols.add(rol.getNombre()));
    }

    public String doLogin() {

        boolean success = true;
        username = username.toLowerCase().trim();
        LOGGER.info("Intento de iniciar session, usuario = {}, exitoso = {}", username, (success ? "SI" : "NO"));

        usuario = usuarioFacade.find(username);
        rols = new HashSet();

        if (success && usuario != null && 'S' == usuario.getHabilitado()) {

            usuario.getRolList().stream().forEach(rol -> rols.add(rol.getNombre()));
            loggedIn = true;
            setMainContent();
            return RedirectPath.MAIN;
        }

        loggedIn = false;

        MessageUtils.sendErrorMessage("errorLogin",
                "Usuario o Contraseña invalidos!!");
        return RedirectPath.LOGIN1;
    }

    public boolean hasRol(String rolName) {
        try {
            return rols.contains(rolName);
        } catch (Exception ex) {
            LOGGER.log(Level.FATAL, "Error al realizar comparar el rol {0}", ex);
            return false;
        }
    }

    public String doLogout() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().invalidateSession();
        return RedirectPath.LOGOUT;
    }

    private void setMainContent() {

        //session = sessionCtrl.getOpenSesssion(usuario);
        session = sessionCtrl.isOpenSession(usuario);

        menuController.setSession(session);

        if (rols.contains(RolEnum.CREAR_TURNO)) {
            menuController.setPagina("ticket/generar_ticket_anonimo");
        } else if (rols.contains(RolEnum.ATENDER_TURNO)) {
            menuController.setPagina("ticket/colaticket");
        } else {
            menuController.setPagina("usuario/perfil");
        }
    }

}
