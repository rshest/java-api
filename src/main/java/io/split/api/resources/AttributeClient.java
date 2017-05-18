package io.split.api.resources;

import io.split.api.dtos.Attribute;
import io.split.api.dtos.TrafficType;
import io.split.client.HttpClient;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class AttributeClient {
    private HttpClient _client;

    public AttributeClient(HttpClient client) {
        this._client = client;
    }

    public List<Attribute> list(String trafficTypeId) {
        return new ArrayList<>();
    }

    public List<Attribute> list(TrafficType trafficType) {
        return list(trafficType.id());
    }

    public Attribute create(Attribute attribute) throws IllegalArgumentException {
        return attribute;
    }

    public boolean delete(String trafficTypeId, String propertyId) throws NoSuchElementException {
        return true;
    }

    public boolean delete(Attribute attribute) throws NoSuchElementException {
        delete(attribute.trafficTypeId(), attribute.id());
        return true;
    }
}
