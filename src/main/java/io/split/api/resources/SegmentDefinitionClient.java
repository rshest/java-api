package io.split.api.resources;

import io.split.api.client.HttpClient;
import io.split.api.client.utils.EncodingUtil;
import io.split.api.dtos.result.ListResultDTO;
import io.split.api.dtos.segments.SegmentDefinition;

public class SegmentDefinitionClient {
    private HttpClient _client;

    public SegmentDefinitionClient(HttpClient client) {
        this._client = client;
    }


    public ListResultDTO<SegmentDefinition> list(String environmentNameOrId) {
        return list(environmentNameOrId, null, null);
    }

    public ListResultDTO<SegmentDefinition> list(String environmentNameOrId,
                                                        Integer offset, Integer limit) {
        String queryParams = (offset != null ? String.format("offset=%s", offset) : "")
                + (limit != null  ? String.format("&limit=%s", limit): "");
        String result = _client.get("/v1/segments/environments/%s?%s", environmentNameOrId, queryParams);
        return EncodingUtil.parseListResult(result, SegmentDefinition.class);
    }
}
