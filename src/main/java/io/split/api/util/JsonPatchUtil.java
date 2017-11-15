package io.split.api.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.diff.JsonDiff;

/**
 * Created on 11/13/17.
 */
public class JsonPatchUtil {
    private static ObjectMapper mapper = new ObjectMapper();

    public static <T> JsonNode createPatch(T source, T target) {
        JsonNode sourceNode = mapper.valueToTree(source);
        JsonNode targetNode = mapper.valueToTree(target);
        return JsonDiff.asJson(sourceNode, targetNode);
    }
}
