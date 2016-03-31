/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.entity;

import java.io.Serializable;

/**
 *
 * @author hectorvent@gmail.com
 */
public class LogFile implements Serializable {

    private String name;
    private String path;
    private boolean directory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isDirectory() {
        return directory;
    }

    public void setDirectory(boolean directory) {
        this.directory = directory;
    }

    @Override
    public String toString() {
        return name;
    }

}
