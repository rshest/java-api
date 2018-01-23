package io.split.api.dtos.segments;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.split.api.dtos.URN;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SegmentDefinition {
    private String name;
    private URN environment;
    private URN trafficType;
    private Long creationTime;

    public SegmentDefinition() {
    }

    private SegmentDefinition(Builder builder) {
        this.name = builder.name;
        this.environment = builder.environment;
        this.trafficType = builder.trafficType;
        this.creationTime = builder.creationTime;
    }

    @JsonProperty
    public String name() {
        return this.name;
    }

    @JsonProperty
    public URN environment() {
        return this.environment;
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

    public void setEnvironment(URN environment) {
        this.environment = environment;
    }

    public void setTrafficType(URN trafficType) {
        this.trafficType = trafficType;
    }

    public void setCreationTime(Long creationTime) {
        this.creationTime = creationTime;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(SegmentDefinition other) {
        return new Builder(other);
    }

    public static class Builder {
        private String name;
        private URN environment;
        private URN trafficType;
        private Long creationTime;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder environment(URN environment) {
            this.environment = environment;
            return this;
        }

        public Builder trafficType(URN trafficType) {
            this.trafficType = trafficType;
            return this;
        }

        public Builder creationTime(Long creationTime) {
            this.creationTime = creationTime;
            return this;
        }

        Builder() { }

        Builder(SegmentDefinition prototype) {
            name = prototype.name;
            environment = prototype.environment;
            creationTime = prototype.creationTime;
            trafficType = prototype.trafficType;
        }

        public SegmentDefinition build() {
            return new SegmentDefinition(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SegmentDefinition that = (SegmentDefinition) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (environment != null ? !environment.equals(that.environment) : that.environment != null) return false;
        if (trafficType != null ? !trafficType.equals(that.trafficType) : that.trafficType != null) return false;
        return creationTime != null ? creationTime.equals(that.creationTime) : that.creationTime == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (environment != null ? environment.hashCode() : 0);
        result = 31 * result + (trafficType != null ? trafficType.hashCode() : 0);
        result = 31 * result + (creationTime != null ? creationTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SegmentDefinition{" +
                "name='" + name + '\'' +
                ", environment=" + environment +
                ", trafficType=" + trafficType +
                ", creationTime=" + creationTime +
                '}';
    }


}
