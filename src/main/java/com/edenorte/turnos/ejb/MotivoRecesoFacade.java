/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edenorte.turnos.ejb;

import com.edenorte.turnos.entity.MotivoReceso;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 
 * @author hventura@citrus.com.do
 */
@Stateless
public class MotivoRecesoFacade extends AbstractFacade<MotivoReceso> {

    @PersistenceContext(unitName = "Turnos")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MotivoRecesoFacade() {
        super(MotivoReceso.class);
    }

}
