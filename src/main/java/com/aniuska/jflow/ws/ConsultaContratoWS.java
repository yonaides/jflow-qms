/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.ws;

/**
 *
 * @author hventura@citrus.com.do
 */
public class ConsultaContratoWS {

//    public static void main(String[] args) {
//
//        DatosCliente dc = ConsultaContratoWS.findClienteByNic(8833984);
//
//        System.out.println("Nombre : " + dc.getNombreCliente());
//        System.out.println("Apellido : " + dc.getApellidos());
//        System.out.println("Cedula : " + dc.getCedulaCliente());
//        System.out.println("Telefono : " + dc.getTelefonos());
//    }

    public static DatosCliente findClienteByNic(Integer nic) {
        WsConsultasICODENService service = new WsConsultasICODENService();
        WsConsultasICODEN con = service.getWsConsultasICODENPort();
        DatosCliente dc = con.getDatosCliente(nic);
        return dc;
    }

}
