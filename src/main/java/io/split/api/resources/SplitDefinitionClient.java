package io.split.api.resources;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonpatch.JsonPatch;
import io.split.api.client.HttpClient;
import io.split.api.client.utils.EncodingUtil;
import io.split.api.dtos.result.ListResultDTO;
import io.split.api.dtos.split.SplitDefinition;

import java.io.IOException;

public class SplitDefinitionClient {
    private HttpClient _client;

    public SplitDefinitionClient(HttpClient client) {
        this._client = client;
    }

    public SplitDefinition get(String environmentNameOrId, String name) {
        String result = _client.get("/v1/splits/%s/environments/%s", name, environmentNameOrId);
        return EncodingUtil.parse(result, SplitDefinition.class);
    }

    public SplitDefinition configure(String environmentNameOrId,
                                             String name, SplitDefinition splitDefinition) {
        String result = _client.post(splitDefinition, "/v1/splits/%s/environments/%s", name, environmentNameOrId);
        return EncodingUtil.parse(result, SplitDefinition.class);
    }

    public Boolean unconfigure(String environmentNameOrId, String name) {
        String result = _client.delete("/v1/splits/%s/environments/%s", name, environmentNameOrId);
        return EncodingUtil.parse(result, Boolean.class);
    }

    public ListResultDTO<SplitDefinition> list(String environmentNameOrId,
                                                        Integer offset, Integer limit) {
        String queryParams = (offset != null ? String.format("offset=%s", offset) : "")
                + (limit != null  ? String.format("&limit=%s", limit): "");
        String result = _client.get("/v1/splits/environments/%s?%s", environmentNameOrId, queryParams);
        return EncodingUtil.parseListResult(result, SplitDefinition.class);
    }

    public SplitDefinition update(String environmentNameOrId,
                                          String name,
                                          JsonNode modify) throws IOException {
        JsonPatch jsonPatch = JsonPatch.fromJson(modify);
        String result = _client.patch(jsonPatch, "/v1/splits/%s/environments/%s", name, environmentNameOrId);
        return EncodingUtil.parse(result, SplitDefinition.class);
    }

    public Boolean kill(String environmentNameOrId, String name) {
        String result = _client.put(null, "/v1/splits/%s/environments/%s/kill", name, environmentNameOrId);
        return EncodingUtil.parse(result, Boolean.class);
    }

    public Boolean restore(String environmentNameOrId, String name) {
        String result = _client.put(null, "/v1/splits/%s/environments/%s/restore", name, environmentNameOrId);
        return EncodingUtil.parse(result, Boolean.class);
    }
}
