/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.managedbean;

import com.aniuska.jflow.ejb.SucursalFacade;
import com.aniuska.jflow.ejb.RolFacade;
import com.aniuska.jflow.ejb.UsuarioFacade;
import com.aniuska.jflow.entity.Sucursal;
import com.aniuska.jflow.entity.Rol;
import com.aniuska.jflow.entity.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author hectorvent@gmail.com
 */
@Named
@ViewScoped
public class AdministrarUsuarioBean implements Serializable {

    private static final long serialVersionUID = 2L;

    @EJB
    UsuarioFacade usuarioCtrl;
    @EJB
    RolFacade rolFacade;
    @EJB
    SucursalFacade sucursalCtrl;
    private Usuario usuario;
    private List<Rol> usuarioRol;
    private List<Rol> rolList;

    // datos consulta
    private List<Usuario> usuarios;
    private String busqueda = "";

    @PostConstruct
    public void init() {
        usuario = new Usuario();
        rolList = rolFacade.findAll();
        usuarios = usuarioCtrl.findAll();
        usuarioRol = new ArrayList();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public List<Rol> getUsuarioRol() {
        return usuarioRol;
    }

    public List<Rol> getRolList() {
        return rolList;
    }

    public void limpiarConsulta() {
        busqueda = "";
        usuarios = new ArrayList();
    }

    public List<Sucursal> getOficinas() {
        return sucursalCtrl.findAll();
    }

    public void habilitarDeshabilitarUsuario(Usuario usuario) {
        this.usuario = usuario;
        usuario.setHabilitado('S' == usuario.getHabilitado() ? 'N' : 'S');
        usuarioCtrl.edit(usuario);
    }

    public void onRowSelect(SelectEvent event) {
        usuario = (Usuario) event.getObject();
        usuarioRol = usuarioCtrl.find(usuario.getIdoperador()).getRolList();
    }

    public boolean hasRol(Rol rol) {
        return usuarioRol.contains(rol);
    }

    public void agregarRolUsuario(Rol rol) {
        rol = rolFacade.find(rol.getId());

        if (usuarioRol.contains(rol)) {

            rol = rolFacade.find(rol.getId());
            rol.getUsuarioList()
                    .remove(usuario);

            rolFacade.edit(rol);

            usuarioRol.remove(rol);
            usuario.setRolList(usuarioRol);

            usuarioCtrl.edit(usuario);
        } else {

            rol.getUsuarioList()
                    .add(usuario);

            rolFacade.edit(rol);

            usuarioRol.add(rol);
            usuario.setRolList(usuarioRol);
            usuarioCtrl.edit(usuario);

        }

    }

    public void nuevoUsuario() {
        usuario = new Usuario();
    }

    public void agregarNuevoUsuario() {

        boolean nuevo = true;
        if (usuarioCtrl.find(usuario.getIdoperador()) != null) {
            nuevo = false;
        }

        if (nuevo) {
            usuario.setHabilitado('S');

            usuarioCtrl.create(usuario);
            usuarios = usuarioCtrl.findAll();
        } else {
            usuarioCtrl.edit(usuario);
        }

        usuario = new Usuario();
    }

    public void buscarUsuarios() {
        usuarios = usuarioCtrl.findAll(busqueda);
    }
}
