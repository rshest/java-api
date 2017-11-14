package io.split.api.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class URN {
    private String id;
    private String name;

    public URN() { }

    private URN(URN.Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    @JsonProperty
    public String id() {
        return id;
    }

    @JsonProperty
    public String name() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(URN other) {
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

        Builder() { }

        Builder(URN prototype) {
            id = prototype.id;
            name = prototype.name;
        }

        public URN build() {
            return new URN(this);
        }
    }
}
