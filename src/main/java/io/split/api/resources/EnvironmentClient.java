package io.split.api.resources;

import io.split.api.dtos.Environment;
import io.split.client.HttpClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EnvironmentClient {
    private HttpClient _client;

    public EnvironmentClient(HttpClient client) {
        this._client = client;
    }

    public List<Environment> list() {
        return new ArrayList<>();
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
