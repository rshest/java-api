package io.split.api.client.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.split.api.dtos.result.ResultDTO;

import java.lang.reflect.Type;
import java.util.List;

public class Json {

    private static final Gson _json = new Gson();

    public static String toJson(Object obj) {
        return _json.toJson(obj);
    }

    public static <T> T parse(String json, Class<T> clz) {
        return _json.fromJson(json, clz);
    }

    public static <T> List<T> parseList(String json, Class<T> clz) {
        Type resultType = new TypeToken<List<T>>() {
        }.getType();
        return _json.fromJson(json, resultType);
    }

    public static <T> ResultDTO<T> parseResult(String json, Class<T> clz) {
        Type resultType = new TypeToken<ResultDTO<T>>() {
        }.getType();
        return _json.fromJson(json, resultType);
    }
}
