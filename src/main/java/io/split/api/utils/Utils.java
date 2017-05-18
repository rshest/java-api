package io.split.api.utils;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.StringEntity;

import java.io.IOException;

public class Utils {
    public static StringEntity toJsonEntity(Object obj) {
        String json = Json.toJson(obj);
        StringEntity entity = new StringEntity(json, "UTF-8");
        entity.setContentType("application/json");
        return entity;
    }


    public static void forceClose(CloseableHttpResponse response) {
        try {
            if (response != null) {
                response.close();
            }
        } catch (IOException e) {
            // ignore
        }
    }
}
