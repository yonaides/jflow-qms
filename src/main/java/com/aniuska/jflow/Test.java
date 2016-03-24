/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow;

//import com.edenorte.utils.auth.WsAutenticacion;

import com.edenorte.utils.auth.WsAutenticacion;


/**
 *
 * @author hector
 */
public class Test {

    public static void main(String[] args) {

        
        boolean success = WsAutenticacion.login("hventurar", "quisquella1");
        
        System.out.println(success);

    }

}
