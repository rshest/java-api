package io.split.api.dtos.split;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Treatment {
    private String name;
    private String description;
    private List<String> keys;
    private List<String> segments;
    public Treatment() {
    }

    private Treatment(Builder builder) {
        this.name = builder.name;
        this.description = builder.description;
        this.keys = builder.keys;
        this.segments = builder.segments;
    }

    @JsonProperty
    public String name() {
        return this.name;
    }

    @JsonProperty
    public String description() {
        return this.description;
    }

    @JsonProperty
    public List<String> keys() {
        return this.keys;
    }

    @JsonProperty
    public List<String> segments() {
        return this.segments;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }

    public void setSegments(List<String> segments) {
        this.segments = segments;
    }
    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(Treatment other) {
        return new Builder(other);
    }

    public static class Builder {
        private String name;
        private String description;
        private List<String> keys;
        private List<String> segments;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder keys(List<String> keys) {
            this.keys = keys;
            return this;
        }

        public Builder segments(List<String> segments) {
            this.segments = segments;
            return this;
        }

        Builder() { }

        Builder(Treatment prototype) {
            name = prototype.name;
            description = prototype.description;
            segments = prototype.segments;
            keys = prototype.keys;
        }

        public Treatment build() {
            return new Treatment(this);
        }
    }
}
