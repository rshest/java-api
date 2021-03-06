package io.split.api.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang.StringUtils;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
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

    @JsonProperty
    public String trafficTypeId() {
        return this.trafficTypeId;
    }

    @JsonProperty
    public String organizationId() {
        return this.organizationId;
    }

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

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(Identity other) {
        return new Builder(other);
    }

    public static class Builder {
        private String key;
        private String trafficTypeId;
        private String organizationId;
        private String environmentId;
        private long timestamp;
        private Map<String, String> values;

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
            if (value == null) {
                values.remove(name);
            } else {
                this.values.put(name, Long.toString(value.getTime()));
            }
            return this;
        }

        public Builder addValue(String name, String value) {
            if (value == null) {
                values.remove(name);
            } else {
                this.values.put(name, value);
            }
            return this;
        }

        public Builder addValue(String name, Collection<String> values) {
            if (values == null) {
                values.remove(name);
            } else {
                String concatenatedValue = StringUtils.join(values, ",");
                this.values.put(name, concatenatedValue);
            }
            return this;
        }

        Builder() {
            values = new HashMap<>();
            timestamp = System.currentTimeMillis();
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

    @Override
    public String toString() {
        return "Identity{" +
                "key='" + key + '\'' +
                ", trafficTypeId='" + trafficTypeId + '\'' +
                ", organizationId='" + organizationId + '\'' +
                ", environmentId='" + environmentId + '\'' +
                ", timestamp=" + timestamp +
                ", values=" + values +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Identity identity = (Identity) o;

        if (timestamp != identity.timestamp) return false;
        if (key != null ? !key.equals(identity.key) : identity.key != null) return false;
        if (trafficTypeId != null ? !trafficTypeId.equals(identity.trafficTypeId) : identity.trafficTypeId != null)
            return false;
        if (organizationId != null ? !organizationId.equals(identity.organizationId) : identity.organizationId != null)
            return false;
        if (environmentId != null ? !environmentId.equals(identity.environmentId) : identity.environmentId != null)
            return false;
        return values != null ? values.equals(identity.values) : identity.values == null;

    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (trafficTypeId != null ? trafficTypeId.hashCode() : 0);
        result = 31 * result + (organizationId != null ? organizationId.hashCode() : 0);
        result = 31 * result + (environmentId != null ? environmentId.hashCode() : 0);
        result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
        result = 31 * result + (values != null ? values.hashCode() : 0);
        return result;
    }
}
