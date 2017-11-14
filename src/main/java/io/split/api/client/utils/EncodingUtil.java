package io.split.api.client.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.split.api.client.exceptions.SplitJsonException;
import io.split.api.dtos.result.ListResultDTO;
import io.split.api.dtos.result.ResultDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EncodingUtil {
    private static final ObjectMapper _mapper = new ObjectMapper();

    public static String encode(Object object) throws SplitJsonException {
        try {
            return _mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new SplitJsonException(e);
        }
    }

    public static <T> T parse(String json, Class<T> objectType) throws SplitJsonException {
        try {
            return _mapper.readValue(json, objectType);
        } catch (IOException e) {
            throw new SplitJsonException(e);
        }
    }

    public static <T> List<T> parseList(String jsonString, Class<T> clazz) throws SplitJsonException {
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
            throw new SplitJsonException(e);
        }
    }

    public static <T> ResultDTO<T> parseResult(String jsonString, Class<T> clazz) throws SplitJsonException {
        TypeReference resultType = new TypeReference<ResultDTO<T>>() {
        };
        try {
            return _mapper.readValue(jsonString, resultType);
        } catch (IOException e) {
            throw new SplitJsonException(e);
        }
    }

    public static <T> ListResultDTO<T> parseListResult(String jsonString, Class<T> objectType) {
        JsonNode node = null;
        try {
            node = _mapper.readValue(jsonString, JsonNode.class);
            Iterator<JsonNode> successfulNodes = node.findValues("objects").get(0).elements();
            List<T> objects = new ArrayList<>();
            while (successfulNodes.hasNext()) {
                JsonNode ni = successfulNodes.next();
                if (ni.size() > 0) {
                    objects.add(_mapper.readValue(ni.toString(), objectType));
                }
            }
            int offset = node.findValue("offset").asInt();
            int limit = node.findValue("limit").asInt();
            long totalCount = node.findValue("totalCount").asLong();
            return ListResultDTO
                    .builder()
                    .limit(limit)
                    .offset(offset)
                    .totalCount(totalCount)
                    .objects(objects)
                    .build();
        } catch (IOException e) {
            throw new IllegalArgumentException("Error parsing result", e);
        }
    }

}
