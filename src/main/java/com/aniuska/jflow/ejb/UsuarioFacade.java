/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.ejb;

import com.aniuska.jflow.entity.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hectorvent@gmail.com
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "JFLOW")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public List<Usuario> findAll(String buscar) {
        String sql = "SELECT U.* FROM USUARIO U "
                + " INNER JOIN OFICINA O ON (U.IDOFICINA = O.IDOFICINA) "
                + "WHERE LOWER(U.IDOPERADOR) LIKE ?1 "
                + "OR LOWER(U.NOMBRE) LIKE ?1 "
                + "OR LOWER(U.APELLIDO) LIKE ?1 ";

        return em.createNativeQuery(sql, Usuario.class)
                .setParameter(1, "%" + buscar.toLowerCase() + "%")
                .getResultList();
    }
    

}
