/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aniuska.jflow.ejb;

import com.aniuska.jflow.entity.MotivoAbandono;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 
 * @author hectorvent@gmail.com
 */
@Stateless
public class MotivoAbandonoFacade extends AbstractFacade<MotivoAbandono> {

    @PersistenceContext(unitName = "JFLOW") 
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MotivoAbandonoFacade() {
        super(MotivoAbandono.class);
    }

}
