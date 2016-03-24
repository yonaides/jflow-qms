package com.aniuska.jflow.auth;

import com.aniuska.jflow.ejb.SessionesFacade;
import com.aniuska.jflow.ejb.UsuarioFacade;
import com.aniuska.jflow.entity.Rol;
import com.aniuska.jflow.entity.Sessiones;
import com.aniuska.jflow.entity.Usuario;
import com.aniuska.jflow.utils.MenuController;
import com.edenorte.utils.MessageUtils;
import com.edenorte.utils.auth.WsAutenticacion;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author hventura@citrus.com.do
 */
@Named
@SessionScoped
public class AuthenticationBean implements Serializable {

    private static final Logger LOGGER = LogManager.getLogger(AuthenticationBean.class);
    private static final long serialVersionUID = 7765876811740798583L;

    private String username = "";
    private String password;
    private boolean remenberMe;
    private boolean loggedIn;
    private Usuario usuario;
    private Set<String> rols;
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private SessionesFacade sessionCtrl;

    private Sessiones session;

    @Inject
    private MenuController menuController;

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

    public MenuController getMenuController() {
        return menuController;
    }

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
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

    public Sessiones getSession() {
        return session;
    }

    public void setSession(Sessiones session) {
        this.session = session;
        menuController.setSession(session);
    }

    public void setSessionCtrl(SessionesFacade sessionCtrl) {
        this.sessionCtrl = sessionCtrl;
    }

    public void refreshRols() {
        usuario = usuarioFacade.find(usuario.getIdoperador());

        rols.clear();
        for (Rol rol : usuario.getRolList()) {
            rols.add(rol.getNombre());
        }
    }

    public String doLogin() {

//        boolean success = true;
        boolean success = WsAutenticacion.login(username, password);

        /// Se usa el nombre de usuario minimizado y trimeado
        username = username.toLowerCase().trim();

        LOGGER.info("Intento de iniciar session, usuario = {}, exitoso = {}", username, (success ? "SI" : "NO"));

        usuario = usuarioFacade.find(username);

        rols = new HashSet();

        if (success && usuario != null && 'S' == usuario.getHabilitado()) {

            for (Rol rol : usuario.getRolList()) {
                rols.add(rol.getNombre());
            }
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
            return false;
        }
    }

    public String doLogout() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().invalidateSession();
        return RedirectPath.LOGOUT;
    }

    private void setMainContent() {

        session = sessionCtrl.getOpenSesssion(usuario);
        menuController.setSession(session);

//        System.out.println("MenuController : " + menuController);
//
        if (rols.contains(RolEnum.CREAR_TURNO)) {
            menuController.setPagina("turno/generar_turno");
        } else if (rols.contains(RolEnum.ATENDER_TURNO)) {
            menuController.setPagina("turno/colaturnos");
        } else {
            menuController.setPagina("usuario/perfil");
        }
    }

}
