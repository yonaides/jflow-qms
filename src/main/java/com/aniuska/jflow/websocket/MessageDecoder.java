/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.websocket;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author hventurar@edenorte.com.do
 */
public class MessageDecoder implements Decoder.Text<Message> {

    @Override
    public Message decode(String message) throws DecodeException {
        Message m = GsonUtils.from(message, Message.class);
        return m;
    }

    @Override
    public boolean willDecode(String s) {
        return true;
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }

}
