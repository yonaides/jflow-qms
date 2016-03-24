/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.utils;

import com.aniuska.jflow.entity.Estado;

/**
 *
 * @author hventura@citrus.com.do
 */
public class Estados {

    public static final Estado EN_ESPERA = new Estado(1);
    public static final Estado EN_PROCESO = new Estado(2);
    public static final Estado ATENDIDO = new Estado(3);
    public static final Estado ABANDONADO = new Estado(4);
    public static final Estado IMPROCEDENTE = new Estado(5);
    public static final Estado PROCEDENTE = new Estado(6);
    public static final Estado ABIERTA = new Estado(7);
    public static final Estado CERRADA = new Estado(8);
    public static final Estado CANCELADO = new Estado(9);
    public static final Estado CIERRE_AUTOMATICO = new Estado(10);

}
