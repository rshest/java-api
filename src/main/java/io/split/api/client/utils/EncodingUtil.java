package io.split.api.client.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.split.api.dtos.result.ResultDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class EncodingUtil {
    private static final Logger _log = LoggerFactory.getLogger(EncodingUtil.class);

    private static final ObjectMapper _mapper = new ObjectMapper();

    public static String encode(Object object) {
        try {
            return _mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            _log.error("Error Parsing Response", e);
            return null;
        }
    }

    public static <T> T parse(String json, Class<T> objectType) {
        try {
            return _mapper.readValue(json, objectType);
        } catch (IOException e) {
            _log.error("Error Parsing Response", e);
            return null;
        }
    }

    public static <T> List<T> parseList(String jsonString, Class<T> clazz) {
        try {
            Iterator<JsonNode> nodes = _mapper.readValue(jsonString, JsonNode.class).elements();
            List<T> list = new ArrayList<>();
            while (nodes.hasNext()) {
                JsonNode ni = nodes.next();
                if (ni.size() > 0) {
                    list.add(_mapper.readValue(ni.toString(), clazz));
                }
            }
            return list;
        } catch (IOException e) {
            _log.error("Error Parsing Response", e);
            return Collections.emptyList();
        }
    }

    public static <T> ResultDTO<T> parseResult(String jsonString, Class<T> clazz) {
        TypeReference resultType = new TypeReference<ResultDTO<T>>() {
        };
        try {
            return _mapper.readValue(jsonString, resultType);
        } catch (IOException e) {
            _log.error("Error Parsing Response", e);
            return null;
        }
    }
}
