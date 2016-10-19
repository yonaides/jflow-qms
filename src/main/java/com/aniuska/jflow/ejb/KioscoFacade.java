/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.ejb;

import com.aniuska.jflow.entity.Kiosco;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hectorvent@gmail.com
 */
@Stateless
public class KioscoFacade extends AbstractFacade<Kiosco> {

    @PersistenceContext(unitName = "JFLOW")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public KioscoFacade() {
        super(Kiosco.class);
    }

    public Kiosco getKioscoInf(String token) {
        Kiosco k = super.find(token);

        // Verificar si es Kiosco
        if (k != null && "KIOSCOINF".equals(k.getTipoDispositivo())) {
            return k;
        }

        return null;
    }

    public Kiosco getPrinter(String token) {
        Kiosco k = super.find(token);

        // Verificar si es Printer
        if (k != null && "PRINTER".equals(k.getTipoDispositivo())) {
            return k;
        }

        return null;
    }

}
