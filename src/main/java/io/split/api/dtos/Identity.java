package io.split.api.dtos;

import org.apache.commons.lang.StringUtils;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Identity {
    private String key;
    private String trafficTypeId;
    private String environmentId;
    private Map<String, String> values;
    private long timestamp;

    public Identity() {

    }

    private Identity(Builder builder) {
        this.key = builder.key;
        this.trafficTypeId = builder.trafficTypeId;
        this.environmentId = builder.environmentId;
        this.values = builder.values;
        this.timestamp = builder.timestamp;
    }

    public String key() {
        return key;
    }

    public String trafficTypeId() {
        return trafficTypeId;
    }

    public String environmentId() {
        return environmentId;
    }

    public Map<String, String> values() {
        return values;
    }

    public long timestamp() {
        return timestamp;
    }

    public void setTrafficTypeId(String trafficTypeId) {
        this.trafficTypeId = trafficTypeId;
    }

    public void setEnvironmentId(String environmentId) {
        this.environmentId = environmentId;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValues(Map<String, String> values) {
        this.values = values;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(Identity other) {
        return new Builder(other);
    }

    public static class Builder {
        private String trafficTypeId;
        private String environmentId;
        private String key;
        private Map<String, String> values;
        private long timestamp;

        public Builder trafficTypeId(String trafficTypeId) {
            this.trafficTypeId = trafficTypeId;
            return this;
        }

        public Builder trafficType(TrafficType trafficType) {
            this.trafficTypeId = trafficType.id();
            return this;
        }

        public Builder environmentId(String environmentId) {
            this.environmentId = environmentId;
            return this;
        }

        public Builder environment(Environment environment) {
            this.environmentId = environment.id();
            return this;
        }

        public Builder key(String key) {
            this.key = key;
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

        public Builder addValue(String name, boolean value) {
            this.values.put(name, Boolean.toString(value));
            return this;
        }

        public Builder addValue(String name, int value) {
            this.values.put(name, Integer.toString(value));
            return this;
        }

        public Builder addValue(String name, long value) {
            this.values.put(name, Long.toString(value));
            return this;
        }

        public Builder addValue(String name, float value) {
            this.values.put(name, Float.toString(value));
            return this;
        }

        public Builder addValue(String name, double value) {
            this.values.put(name, Double.toString(value));
            return this;
        }

        public Builder addValue(String name, Date value) {
            this.values.put(name, Long.toString(value.getTime()));
            return this;
        }

        public Builder addValue(String name, String value) {
            this.values.put(name, value);
            return this;
        }

        public Builder addValue(String name, Collection<String> values) {
            String concatenatedValue = StringUtils.join(values, ",");
            this.values.put(name, concatenatedValue);
            return this;
        }

        Builder() {
            values = new HashMap<>();
        }

        Builder(Identity prototype) {
            key = prototype.key;
            trafficTypeId = prototype.trafficTypeId;
            environmentId = prototype.environmentId;
            values = prototype.values;
            timestamp = prototype.timestamp;
        }

        public Identity build() {
            return new Identity(this);
        }
    }
}
