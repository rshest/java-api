package io.split.api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TrafficType {
    private String id;
    private String name;
    private String displayAttributeId;

    public TrafficType() {

    }

    private TrafficType(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.displayAttributeId = builder.displayAttributeId;
    }

    @JsonProperty
    public String id() {
        return id;
    }

    @JsonProperty
    public String name() {
        return name;
    }

    @JsonProperty
    public String displayAttributeId() {
        return displayAttributeId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDisplayAttributeId(String displayAttributeId) {
        this.displayAttributeId = displayAttributeId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(TrafficType other) {
        return new Builder(other);
    }

    public static class Builder {
        private String id;
        private String name;
        private String displayAttributeId;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder displayAttributeId(String displayAttributeId) {
            this.displayAttributeId = displayAttributeId;
            return this;
        }

        Builder() {
        }

        Builder(TrafficType prototype) {
            id = prototype.id;
            name = prototype.name;
            displayAttributeId = prototype.displayAttributeId;
        }

        public TrafficType build() {
            return new TrafficType(this);
        }
    }
}
