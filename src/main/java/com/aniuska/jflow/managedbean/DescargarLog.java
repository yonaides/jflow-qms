/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.managedbean;

import com.aniuska.jflow.entity.LogFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.TreeNode;

/**
 *
 * @author hventura@citrus.com.do
 */
@Named
@ViewScoped
public class DescargarLog implements Serializable {

    private TreeNode root;
    private TreeNode selectedNode;

    public void ListFiles(TreeNode tn, File file) {

        LogFile log = new LogFile();
        log.setName(file.getName());
        log.setPath(file.getPath());

        if (file.isDirectory()) {
            System.out.println("Es un directorio");

            log.setDirectory(true);
            TreeNode node0 = new DefaultTreeNode(log, tn);

            for (File object : file.listFiles()) {
                ListFiles(node0, object);
            }
        } else {

            log.setDirectory(false);
            DefaultTreeNode defaultTreeNode = new DefaultTreeNode("document", log, tn);
        }

    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    @PostConstruct
    public void init() {

        System.out.println("Iniciando.....");
        root = new DefaultTreeNode("document", null);
        File file1 = new File(System.getProperty("user.dir") + "/../logs");

        ListFiles(root, file1);

        file1 = new File(System.getProperty("user.dir") + "/logs");
        ListFiles(root, file1);

//        root = new DefaultTreeNode("document", null);
//        TreeNode node0 = new DefaultTreeNode("Node 0", root);
//        TreeNode node1 = new DefaultTreeNode("Node 1", root);
//        
//        TreeNode node00 = new DefaultTreeNode("Node 0.0", node0);
//        TreeNode node01 = new DefaultTreeNode("Node 0.1", node0);
//        
//        TreeNode node10 = new DefaultTreeNode("Node 1.0", node1);
//        
//        node1.getChildren().add(new DefaultTreeNode("Node 1.1"));
//        node00.getChildren().add(new DefaultTreeNode("Node 0.0.0"));
//        node00.getChildren().add(new DefaultTreeNode("Node 0.0.1"));
//        node01.getChildren().add(new DefaultTreeNode("Node 0.1.0"));
//        node10.getChildren().add(new DefaultTreeNode("Node 1.0.0"));
//        root.getChildren().add(new DefaultTreeNode("Node 2"));
    }

    public TreeNode getRoot() {
        return root;
    }

    public StreamedContent getFile(LogFile lf) {

        File file1 = new File(lf.getPath());
        StreamedContent file = null;
        try {
            file = new DefaultStreamedContent(new FileInputStream(file1), "text/plain", "log.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DescargarLog.class.getName()).log(Level.SEVERE, null, ex);
        }

        return file;
    }

    public StreamedContent getFile() {
        LogFile lf = (LogFile) selectedNode.getData();
        return getFile(lf);
    }

}
