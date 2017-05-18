package io.split.api.resources;

import io.split.api.dtos.TrafficType;
import io.split.client.HttpClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TrafficTypeClient {
    private HttpClient _client;

    public TrafficTypeClient(HttpClient client) {
        this._client = client;
    }

    public List<TrafficType> list() {
        return new ArrayList<>();
    }

    public TrafficType get(String name) {
        for (TrafficType trafficType : list()) {
            if (Objects.equals(name, trafficType.name())) {
                return trafficType;
            }
        }
        return null;
    }
}
