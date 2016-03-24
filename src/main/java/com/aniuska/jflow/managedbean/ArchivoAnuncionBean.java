/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.managedbean;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author hventura@citrus.com.do
 */
@Named
@ViewScoped
public class ArchivoAnuncionBean implements Serializable {

    public void handleFileUpload(FileUploadEvent event) {

        try {
            UploadedFile uf = event.getFile();

            File dir = new File(System.getProperty("user.home") + "/files/" + uf.getFileName());
            Files.copy(event.getFile().getInputstream(), dir.toPath());
        } catch (IOException ex) {
            Logger.getLogger(ArchivoAnuncionBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
