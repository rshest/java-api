package io.split.api.dtos;

public class Environment {
    private String id;
    private String name;

    private Environment() {

    }

    private Environment(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(Environment other) {
        return new Builder(other);
    }

    public static class Builder {
        private String id;
        private String name;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        Builder() {
        }

        Builder(Environment prototype) {
            id = prototype.id;
            name = prototype.name;
        }

        public Environment build() {
            return new Environment(this);
        }
    }
}
