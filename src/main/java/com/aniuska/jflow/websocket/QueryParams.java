/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.jflow.websocket;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author hventurar@edenorte.com.do
 */
public class QueryParams {

    private final Map<String, String> params = new HashMap();

    public QueryParams() {
    }

    public QueryParams(String queryString) {
        procesar(queryString);
    }

    public QueryParams parser(String queryString) {
        procesar(queryString);
        return this;
    }

    public String get(String key) {
        return params.get(key);
    }

    private void procesar(String query) {
        String queryParams[] = query.split("&");
        for (String param : queryParams) {
            String[] kv = param.split("=");
            this.params.put(kv[0], kv[1]);
        }
    }

}
