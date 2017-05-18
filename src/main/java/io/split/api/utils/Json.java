package io.split.api.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.split.api.dtos.result.ResultDTO;

import java.lang.reflect.Type;

public class Json {

    private static final Gson _json = new Gson();

    public static String toJson(Object obj) {
        return _json.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> clz) {
        return _json.fromJson(json, clz);
    }

    public static <T> ResultDTO<T> fromJsonResult(String json, Class<T> clz) {
        Type resultType = new TypeToken<ResultDTO<T>>() {
        }.getType();
        return _json.fromJson(json, resultType);
    }
}
