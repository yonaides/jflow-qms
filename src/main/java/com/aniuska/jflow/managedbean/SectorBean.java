/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.managedbean;

import com.aniuska.jflow.ejb.SectorFacade;
import com.aniuska.jflow.entity.Sector;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author hventura@citrus.com.do
 */
@ViewScoped
@Named
public class SectorBean implements Serializable {

    @EJB
    private SectorFacade sectorCtrl;

    public void setSectorCtrl(SectorFacade sectorCtrl) {
        this.sectorCtrl = sectorCtrl;
    }

    public List<Sector> getSectores() {
        return sectorCtrl.findAll();
    }

}
