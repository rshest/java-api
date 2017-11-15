package io.split.api.client.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.split.api.client.exceptions.SplitJsonException;
import io.split.api.dtos.result.FailureDTO;
import io.split.api.dtos.result.ListResultDTO;
import io.split.api.dtos.result.ResultDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

    public static <T> ResultDTO<T> parseResult(String jsonString, Class<T> objectType) {
        try {
            JsonNode node = _mapper.readValue(jsonString, JsonNode.class);


            List<T> successful = new ArrayList<>();
            List<JsonNode> objectsNode = node.findValues("objects");
            if (objectsNode != null && !objectsNode.isEmpty()) {
                Iterator<JsonNode> successfulNodes = node.findValues("objects").get(0).elements();
                while (successfulNodes.hasNext()) {
                    JsonNode ni = successfulNodes.next();
                    if (ni.size() > 0) {
                        successful.add(_mapper.readValue(ni.toString(), objectType));
                    }
                }
            }

            List<FailureDTO<T>> failed = new ArrayList<>();
            List<JsonNode> failedNode = node.findValues("failed");
            if (failedNode != null && !failedNode.isEmpty()) {
                Iterator<JsonNode> failedNodes = failedNode.get(0).elements();
                while (failedNodes.hasNext()) {
                    JsonNode ni = failedNodes.next();
                    if (ni.size() > 0) {
                        int status = ni.findValue("status").asInt();
                        String message = ni.findValue("message").asText();
                        T object = _mapper.readValue(ni.findValues("object").get(0).toString(), objectType);
                        failed.add(FailureDTO.<T>builder()
                                .status(status)
                                .message(message)
                                .object(object)
                                .build()
                        );
                    }
                }
            }

            Map<String, String> metadata = new HashMap<>();
            List<JsonNode> metadataNode = node.findValues("metadata");
            if (metadataNode != null && !metadata.isEmpty()) {
                _mapper.readValue(
                        metadataNode.get(0).toString(),
                        new TypeReference<HashMap<String, String>>() {
                        }
                );
            }

            ResultDTO.Builder builder = ResultDTO.<T>builder();
            JsonNode offset = node.findValue("offset");
            if (offset != null) {
                builder.offset(offset.asInt());
            }
            JsonNode limit = node.findValue("limit");
            if (limit != null) {
                builder.limit(limit.asInt());
            }

            JsonNode count = node.findValue("count");
            if (count != null) {
                builder.count(count.asInt());
            }

            JsonNode total = node.findValue("total");
            if (total != null) {
                builder.total(total.asInt());
            }

            return builder
                    .objects(successful)
                    .failed(failed)
                    .metadata(metadata)
                    .build();
        } catch (IOException e) {
            throw new SplitJsonException(e);
        }
    }

    public static <T> ListResultDTO<T> parseListResult(String jsonString, Class<T> objectType) {
        try {
            JsonNode node = _mapper.readValue(jsonString, JsonNode.class);

            List<T> successful = new ArrayList<>();
            List<JsonNode> objectsNode = node.findValues("objects");
            if (objectsNode != null && !objectsNode.isEmpty()) {
                Iterator<JsonNode> successfulNodes = node.findValues("objects").get(0).elements();
                while (successfulNodes.hasNext()) {
                    JsonNode ni = successfulNodes.next();
                    if (ni.size() > 0) {
                        successful.add(_mapper.readValue(ni.toString(), objectType));
                    }
                }
            }

            ListResultDTO.Builder builder = ListResultDTO.<T>builder();
            JsonNode offset = node.findValue("offset");
            if (offset != null) {
                builder.offset(offset.asInt());
            }
            JsonNode limit = node.findValue("limit");
            if (limit != null) {
                builder.limit(limit.asInt());
            }

            JsonNode totalCount = node.findValue("totalCount");
            if (totalCount != null) {
                builder.totalCount(totalCount.asLong());
            }

            return builder
                    .objects(successful)
                    .build();
        } catch (IOException e) {
            throw new SplitJsonException(e);
        }
    }

}
