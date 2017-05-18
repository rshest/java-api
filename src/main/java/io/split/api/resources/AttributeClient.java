package io.split.api.resources;

import io.split.api.dtos.Attribute;
import io.split.api.dtos.TrafficType;
import io.split.client.HttpClient;
import io.split.client.utils.Json;

import java.util.List;

public class AttributeClient {
    private HttpClient _client;

    public AttributeClient(HttpClient client) {
        this._client = client;
    }

    public List<Attribute> list(TrafficType trafficType) {
        return list(trafficType.id());
    }

    public List<Attribute> list(String trafficTypeId) {
        String result = _client.get(
                "/v1/trafficTypes/{trafficTypeId}/schema",
                trafficTypeId
        );
        return Json.parseList(result, Attribute.class);
    }

    public Attribute create(Attribute attribute) {
        String result = _client.put(
                "/v1/trafficTypes/{trafficTypeId}/schema",
                attribute.trafficTypeId()
        );
        return Json.parse(result, Attribute.class);
    }

    public boolean delete(Attribute attribute) {
        return delete(attribute.trafficTypeId(), attribute.id());
    }

    public boolean delete(String trafficTypeId, String attributeId) {
        String result = _client.get(
                "/v1/trafficTypes/{trafficTypeId}/schema/{attributeId}",
                trafficTypeId,
                attributeId
        );
        return Json.parse(result, Boolean.class);
    }
}
