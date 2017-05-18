package io.split.api.client;

import io.split.api.dtos.Environment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EnvironmentClient {
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
