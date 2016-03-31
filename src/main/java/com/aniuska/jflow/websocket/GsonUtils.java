/**
 * 
 */
package com.aniuska.jflow.websocket;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 *
 * @author hectorvent@gmail.com
 */
public class GsonUtils {

    private static final Gson GSON = new Gson();

    public static String toJson(Object obj) {
        return GSON.toJson(obj);
    }

    public static <T extends Object> T from(String json, Class<T> classOf) {
        return GSON.fromJson(json, classOf);
    }
    
    public static <T extends Object> T from(JsonObject json, Class<T> classOf) {
        return GSON.fromJson(json, classOf);
    }

    public static JsonElement toJsonTree(Object obj) {
        return GSON.toJsonTree(obj);
    }

}
