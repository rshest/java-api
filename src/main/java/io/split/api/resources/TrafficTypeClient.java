package io.split.api.resources;

import io.split.api.dtos.TrafficType;
import io.split.api.client.HttpClient;
import io.split.api.client.utils.EncodingUtil;

import java.util.List;
import java.util.Objects;

public class TrafficTypeClient {
    private HttpClient _client;

    public TrafficTypeClient(HttpClient client) {
        this._client = client;
    }

    public List<TrafficType> list() {
        String result = _client.get("/v1/trafficTypes");
        return EncodingUtil.parseList(result, TrafficType.class);
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
