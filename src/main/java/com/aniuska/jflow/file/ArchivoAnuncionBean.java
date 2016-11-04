/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.file;

import com.aniuska.jflow.ejb.ArchivoFacade;
import com.aniuska.jflow.entity.Archivo;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author hectorvent@gmail.com
 */
@Named
@ViewScoped
public class ArchivoAnuncionBean implements Serializable {

    @EJB
    ArchivoFacade archivoCtrl;
    private List<Archivo> archivos;
    private static final Logger LOGGER = LogManager.getLogger(ArchivoAnuncionBean.class);
    private final Map<String, String> icons = new HashMap();

    @PostConstruct
    public void init() {
        archivos = archivoCtrl.findAll();
        icons.put("jpg", "fa-picture-o");
        icons.put("jpeg", "fa-picture-o");
        icons.put("png", "fa-picture-o");
        icons.put("avi", "fa-video-camera");
    }

    public List<Archivo> getArchivos() {
        return archivos;
    }

    public void setArchivos(List<Archivo> archivos) {
        this.archivos = archivos;
    }

    public void handleFileUpload(FileUploadEvent event) {

        try {

            UploadedFile uf = event.getFile();

            File dir = new File(ArchivosConfig.FILES_DIR);

            if (!dir.exists()) {
                dir.mkdirs();
            }

            UUID uuid = UUID.randomUUID();
            String fileID = uuid.toString();

            String format = uf.getFileName()
                    .substring(uf.getFileName().lastIndexOf(".") + 1);

            File file = new File(dir, fileID);
            Files.copy(event.getFile().getInputstream(), file.toPath());

            Archivo ar = new Archivo();
            ar.setId(fileID);
            ar.setFomato(format);
            ar.setNombre(uf.getFileName());
            ar.setTamano(uf.getSize());
            ar.setTipoContenido(uf.getContentType());

            System.out.println(ar);
            archivoCtrl.create(ar);

        } catch (IOException ex) {
            LOGGER.error(ex);
        }

    }

    public String getFileTypeIcon(String fileType) {
        return icons.get(fileType);
    }

    public String humanReadableByteCount(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) {
            return bytes + " B";
        }
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }
}
