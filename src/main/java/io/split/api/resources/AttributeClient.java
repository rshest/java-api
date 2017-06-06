package io.split.api.resources;

import io.split.api.client.HttpClient;
import io.split.api.client.exceptions.SplitException;
import io.split.api.client.utils.EncodingUtil;
import io.split.api.dtos.Attribute;
import io.split.api.dtos.TrafficType;

import java.util.List;

public class AttributeClient {
    private HttpClient _client;

    public AttributeClient(HttpClient client) {
        this._client = client;
    }

    public List<Attribute> list(TrafficType trafficType) throws SplitException {
        return list(trafficType.id());
    }

    public List<Attribute> list(String trafficTypeId) throws SplitException {
        String result = _client.get(
                "/v1/trafficTypes/%s/schema",
                trafficTypeId
        );
        return EncodingUtil.parseList(result, Attribute.class);
    }

    public Attribute create(Attribute attribute) throws SplitException {
        String result = _client.put(
                attribute,
                "/v1/trafficTypes/%s/schema",
                attribute.trafficTypeId()
        );
        return EncodingUtil.parse(result, Attribute.class);
    }

    public boolean delete(Attribute attribute) throws SplitException {
        return delete(attribute.trafficTypeId(), attribute.id());
    }

    public boolean delete(String trafficTypeId, String attributeId) throws SplitException {
        String result = _client.delete(
                "/v1/trafficTypes/%s/schema/%s",
                trafficTypeId,
                attributeId
        );
        return EncodingUtil.parse(result, Boolean.class);
    }
}
