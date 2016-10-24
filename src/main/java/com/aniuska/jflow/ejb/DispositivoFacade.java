/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.ejb;

import com.aniuska.jflow.entity.Dispositivo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hectorvent@gmail.com
 */
@Stateless
public class DispositivoFacade extends AbstractFacade<Dispositivo> {

    @PersistenceContext(unitName = "JFLOW")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DispositivoFacade() {
        super(Dispositivo.class);
    }

    public Dispositivo getKioscoInf(String token) {
        Dispositivo k = super.find(token);

        // Verificar si es Dispositivo
        if (k != null && "KIOSCOINF".equals(k.getTipoDispositivo())) {
            return k;
        }

        return null;
    }

    public Dispositivo getPrinter(String token) {
        Dispositivo k = super.find(token);

        // Verificar si es Printer
        if (k != null && "PRINTER".equals(k.getTipoDispositivo())) {
            return k;
        }

        return null;
    }

}
