/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.websocket;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author hventurar@edenorte.com.do
 */
public class MessageEncoder implements Encoder.Text<Message> {

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {
    }

    @Override
    public String encode(Message object) throws EncodeException {
        return GsonUtils.toJson(object);
    }

}
