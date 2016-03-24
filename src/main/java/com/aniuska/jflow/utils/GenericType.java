/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.utils;

import java.io.Serializable;

/**
 *
 * @author hventura@citrus.com.do
 */
public class GenericType implements Serializable {

    private String key;
    private String value;

    public GenericType(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public GenericType() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "GenericType{" + "key=" + key + ", value=" + value + '}';
    }

}
