/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.file;

import com.aniuska.jflow.ejb.ArchivoFacade;
import com.aniuska.jflow.entity.Archivo;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author hventurar@edenorte.com.do
 */
@WebServlet("/archivo/*")
public class ArchivoServlet extends HttpServlet {

    @EJB
    ArchivoFacade archivoCtrl;
    private static final Logger LOGGER = LogManager.getLogger(ArchivoServlet.class);

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    /**
     *
     * @param request
     * @param response
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        String fileId = request.getPathInfo();

        if (fileId == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            LOGGER.info("PathParam is null");
            return;
        }

        fileId = fileId.substring(1);

        if (fileId.length() != 36) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            LOGGER.info("PathParam is : " + fileId);
            return;
        }

        Archivo ar = archivoCtrl.find(fileId);

        if (ar == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        try {
            response.setContentLengthLong(ar.getTamano());
            response.setContentType(ar.getTipoContenido());
            response.setHeader("Content-Disposition",
                    String.format("attachment; filename=%s", ar.getNombre()));

            response.setHeader("X-File-Name", ar.getNombre());
            response.setHeader("X-File-Id", ar.getId());
//            response.setHeader("X-File-Id", ar.getId());

            File file = new File(ArchivosConfig.FILES_DIR, ar.getId());

            OutputStream out = response.getOutputStream();
            try (FileInputStream in = new FileInputStream(file)) {
                byte[] buffer = new byte[4096];
                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
            }
            out.flush();

        } catch (IOException ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            LOGGER.error(ex);
        }

    }

}
