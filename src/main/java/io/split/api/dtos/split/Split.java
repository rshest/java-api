package io.split.api.dtos.split;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.split.api.dtos.TrafficType;
import io.split.api.dtos.URN;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Split {
    private String name;
    private String description;
    private URN trafficType;
    private Long creationTime;

    public Split() {
    }

    private Split(Builder builder) {
        this.name = builder.name;
        this.description = builder.description;
        this.creationTime = builder.creationTime;
        this.trafficType = builder.trafficType;
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
    public URN trafficType() {
        return this.trafficType;
    }

    @JsonProperty
    public Long creationTime() {
        return this.creationTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTrafficType(URN trafficType) {
        this.trafficType = trafficType;
    }

    public void setCreationTime(Long creationTime) {
        this.creationTime = creationTime != null ? creationTime : System.currentTimeMillis();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(Split other) {
        return new Builder(other);
    }

    public static class Builder {
        private String name;
        private String description;
        private URN trafficType;
        private Long creationTime;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder trafficType(URN trafficType) {
            this.trafficType = trafficType;
            return this;
        }

        public Builder trafficType(TrafficType trafficType) {
            this.trafficType = URN
                    .builder()
                    .name(trafficType.name())
                    .id(trafficType.id())
                    .build();
            return this;
        }

        public Builder creationTime(Long creationTime) {
            this.creationTime = creationTime;
            return this;
        }

        Builder() { }

        Builder(Split prototype) {
            name = prototype.name;
            description = prototype.description;
            trafficType = prototype.trafficType;
            creationTime = prototype.creationTime;
        }

        public Split build() {
            return new Split(this);
        }
    }

    @Override
    public String toString() {
        return "Split{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", trafficType=" + trafficType +
                ", creationTime=" + creationTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Split split = (Split) o;

        if (name != null ? !name.equals(split.name) : split.name != null) return false;
        if (description != null ? !description.equals(split.description) : split.description != null) return false;
        if (trafficType != null ? !trafficType.equals(split.trafficType) : split.trafficType != null) return false;
        return creationTime != null ? creationTime.equals(split.creationTime) : split.creationTime == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (trafficType != null ? trafficType.hashCode() : 0);
        result = 31 * result + (creationTime != null ? creationTime.hashCode() : 0);
        return result;
    }
}
