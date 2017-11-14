package io.split.api.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
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

    @Override
    public String toString() {
        return "TrafficType{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", displayAttributeId='" + displayAttributeId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrafficType that = (TrafficType) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return displayAttributeId != null ? displayAttributeId.equals(that.displayAttributeId) : that.displayAttributeId == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (displayAttributeId != null ? displayAttributeId.hashCode() : 0);
        return result;
    }
}
