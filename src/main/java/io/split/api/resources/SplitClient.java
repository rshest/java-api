package io.split.api.resources;

import io.split.api.client.HttpClient;
import io.split.api.client.utils.EncodingUtil;
import io.split.api.dtos.Split;
import io.split.api.dtos.result.ListResultDTO;

public class SplitClient {
    private HttpClient _client;

    public SplitClient(HttpClient client) {
        this._client = client;
    }

    public Split get(String name) {
        String result = _client.get("/v1/splits/%s", name);
        return EncodingUtil.parse(result, Split.class);
    }

    public Boolean delete(String name) {
        String result = _client.delete("/v1/splits/%s", name);
        return EncodingUtil.parse(result, Boolean.class);
    }

    public Split create(Split split, String trafficTypeNameOrIds) {
        String result = _client.post(split,
                "/v1/splits/trafficTypes/%s", trafficTypeNameOrIds);
        return EncodingUtil.parse(result, Split.class);
    }

    public ListResultDTO<Split> list() {
        return list(null, null);
    }

    public ListResultDTO<Split> list(Integer offset, Integer limit) {
        String queryParams = (offset != null ? String.format("offset=%s", offset) : "")
                + (limit != null ? String.format("&limit=%s", limit): "");
        String result = _client
                .get("/v1/splits?%s", queryParams);

        return EncodingUtil.parseListResult(result, Split.class);
    }
}
