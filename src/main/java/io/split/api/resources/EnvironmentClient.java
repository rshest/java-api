package io.split.api.resources;

import io.split.api.dtos.Environment;
import io.split.api.client.HttpClient;
import io.split.api.client.utils.Json;

import java.util.List;
import java.util.Objects;

public class EnvironmentClient {
    private HttpClient _client;

    public EnvironmentClient(HttpClient client) {
        this._client = client;
    }

    public List<Environment> list() {
        String result = _client.get("/v1/environments");
        return Json.parseList(result, Environment.class);
    }

    public Environment get(String name) {
        for (Environment environment : list()) {
            if (Objects.equals(name, environment.name())) {
                return environment;
            }
        }
        return null;
    }
}
