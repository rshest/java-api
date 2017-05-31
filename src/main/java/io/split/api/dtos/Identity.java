package io.split.api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

public class Identity {
    private String key;
    private String trafficTypeId;
    private String organizationId;
    private String environmentId;
    private long timestamp;
    private Map<String, String> values;

    public Identity() {
        this.timestamp = System.currentTimeMillis();
        this.values = new HashMap<>();
    }

    private Identity(Builder builder) {
        this.key = builder.key;
        this.trafficTypeId = builder.trafficTypeId;
        this.organizationId = builder.organizationId;
        this.environmentId = builder.environmentId;
        this.timestamp = builder.timestamp;
        this.values = builder.values;
    }

    @JsonProperty
    public String key() {
        return this.key;
    }

    @Nullable
    @JsonProperty
    public String trafficTypeId() {
        return this.trafficTypeId;
    }

    @Nullable
    @JsonProperty
    public String organizationId() {
        return this.organizationId;
    }

    @Nullable
    @JsonProperty
    public String environmentId() {
        return this.environmentId;
    }

    @JsonProperty
    public long timestamp() {
        return this.timestamp;
    }

    @JsonProperty
    public Map<String, String> values() {
        return this.values;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setTrafficTypeId(String trafficTypeId) {
        this.trafficTypeId = trafficTypeId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public void setEnvironmentId(String environmentId) {
        this.environmentId = environmentId;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp != null ? timestamp : System.currentTimeMillis();
    }

    public void setValues(Map<String, String> values) {
        this.values = values;
    }

    public static class Builder {
        private String key;
        private String trafficTypeId;
        private String organizationId;
        private String environmentId;
        private long timestamp = System.currentTimeMillis();
        private Map<String, String> values = new HashMap<>();

        public Builder key(String key) {
            this.key = key;
            return this;
        }

        public Builder trafficTypeId(String trafficTypeId) {
            this.trafficTypeId = trafficTypeId;
            return this;
        }

        public Builder organizationId(String organizationId) {
            this.organizationId = organizationId;
            return this;
        }

        public Builder environmentId(String environmentId) {
            this.environmentId = environmentId;
            return this;
        }

        public Builder timestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder values(Map<String, String> values) {
            this.values = values;
            return this;
        }

        Builder() {

        }

        Builder(Identity prototype) {
            key = prototype.key;
            trafficTypeId = prototype.trafficTypeId;
            organizationId = prototype.organizationId;
            environmentId = prototype.environmentId;
            timestamp = prototype.timestamp;
            values = prototype.values;
        }

        public Identity build() {
            return new Identity(this);
        }
    }
}
