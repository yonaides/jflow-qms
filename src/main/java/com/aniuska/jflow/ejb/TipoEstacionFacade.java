/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aniuska.jflow.ejb;

import com.aniuska.jflow.entity.TipoEstacion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 
 * @author hectorvent@gmail.com
 */
@Stateless
public class TipoEstacionFacade extends AbstractFacade<TipoEstacion> {

    @PersistenceContext(unitName = "JFLOW")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoEstacionFacade() {
        super(TipoEstacion.class);
    }

}
