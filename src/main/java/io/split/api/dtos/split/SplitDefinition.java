package io.split.api.dtos.split;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.split.api.dtos.URN;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SplitDefinition {
    private String name;
    private URN environment;
    private Boolean killed;
    private List<Treatment> treatments;
    private String defaultTreatment;
    private Integer trafficAllocation;
    private List<Rule> rules;
    private List<Bucket> defaultRule;
    private Long creationTime;
    private Long lastUpdateTime;

    public SplitDefinition() {
    }

    private SplitDefinition(Builder builder) {
        this.name = builder.name;
        this.environment = builder.environment;
        this.killed = builder.killed;
        this.treatments = builder.treatments;
        this.defaultTreatment = builder.defaultTreatment;
        this.trafficAllocation = builder.trafficAllocation;
        this.rules = builder.rules;
        this.defaultRule = builder.defaultRule;
        this.creationTime = builder.creationTime;
        this.lastUpdateTime = builder.lastUpdateTime;
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
    public Boolean killed() {
        return this.killed;
    }

    @JsonProperty
    public List<Treatment> treatments() {
        return this.treatments;
    }

    @JsonProperty
    public String defaultTreatment() {
        return this.defaultTreatment;
    }

    @JsonProperty
    public Integer trafficAllocation() {
        return this.trafficAllocation;
    }

    @JsonProperty
    public List<Rule> rules() {
        return this.rules;
    }

    @JsonProperty
    public List<Bucket> defaultRule() {
        return this.defaultRule;
    }

    @JsonProperty
    public Long creationTime() {
        return this.creationTime;
    }

    @JsonProperty
    public Long lastUpdateTime() {
        return this.lastUpdateTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEnvironment(URN environment) {
        this.environment = environment;
    }

    public void setKilled(Boolean killed) {
        this.killed = killed;
    }

    public void setTreatments(List<Treatment> treatments) {
        this.treatments = treatments;
    }

    public void setDefaultTreatment(String defaultTreatment) {
        this.defaultTreatment = defaultTreatment;
    }

    public void setTrafficAllocation(Integer trafficAllocation) {
        this.trafficAllocation = trafficAllocation;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public void setDefaultRule(List<Bucket> defaultRule) {
        this.defaultRule = defaultRule;
    }

    public void setCreationTime(Long creationTime) {
        this.creationTime = creationTime;
    }

    public void setLastUpdateTime(Long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(SplitDefinition other) {
        return new Builder(other);
    }

    public static class Builder {
        private String name;
        private URN environment;
        private Boolean killed;
        private List<Treatment> treatments;
        private String defaultTreatment;
        private Integer trafficAllocation;
        private List<Rule> rules;
        private List<Bucket> defaultRule;
        private Long creationTime;
        private Long lastUpdateTime;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder environment(URN environment) {
            this.environment = environment;
            return this;
        }

        public Builder killed(Boolean killed) {
            this.killed = killed;
            return this;
        }

        public Builder treatments(List<Treatment> treatments) {
            this.treatments = treatments;
            return this;
        }

        public Builder defaultTreatment(String defaultTreatment) {
            this.defaultTreatment = defaultTreatment;
            return this;
        }

        public Builder trafficAllocation(Integer trafficAllocation) {
            this.trafficAllocation = trafficAllocation;
            return this;
        }

        public Builder rules(List<Rule> rules) {
            this.rules = rules;
            return this;
        }

        public Builder defaultRule(List<Bucket> defaultRule) {
            this.defaultRule = defaultRule;
            return this;
        }

        public Builder creationTime(Long creationTime) {
            this.creationTime = creationTime;
            return this;
        }

        public Builder lastUpdateTime(Long lastUpdateTime) {
            this.lastUpdateTime = lastUpdateTime;
            return this;
        }

        Builder() { }

        Builder(SplitDefinition prototype) {
            name = prototype.name;
            environment = prototype.environment;
            killed = prototype.killed;
            treatments = prototype.treatments;
            defaultTreatment = prototype.defaultTreatment;
            trafficAllocation = prototype.trafficAllocation;
            rules = prototype.rules;
            defaultRule = prototype.defaultRule;
            creationTime = prototype.creationTime;
            lastUpdateTime = prototype.lastUpdateTime;
        }

        public SplitDefinition build() {
            return new SplitDefinition(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SplitDefinition that = (SplitDefinition) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (environment != null ? !environment.equals(that.environment) : that.environment != null) return false;
        if (killed != null ? !killed.equals(that.killed) : that.killed != null) return false;
        if (treatments != null ? !treatments.equals(that.treatments) : that.treatments != null) return false;
        if (defaultTreatment != null ? !defaultTreatment.equals(that.defaultTreatment) : that.defaultTreatment != null)
            return false;
        if (trafficAllocation != null ? !trafficAllocation.equals(that.trafficAllocation) : that.trafficAllocation != null)
            return false;
        if (rules != null ? !rules.equals(that.rules) : that.rules != null) return false;
        if (defaultRule != null ? !defaultRule.equals(that.defaultRule) : that.defaultRule != null) return false;
        if (creationTime != null ? !creationTime.equals(that.creationTime) : that.creationTime != null) return false;
        return lastUpdateTime != null ? lastUpdateTime.equals(that.lastUpdateTime) : that.lastUpdateTime == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (environment != null ? environment.hashCode() : 0);
        result = 31 * result + (killed != null ? killed.hashCode() : 0);
        result = 31 * result + (treatments != null ? treatments.hashCode() : 0);
        result = 31 * result + (defaultTreatment != null ? defaultTreatment.hashCode() : 0);
        result = 31 * result + (trafficAllocation != null ? trafficAllocation.hashCode() : 0);
        result = 31 * result + (rules != null ? rules.hashCode() : 0);
        result = 31 * result + (defaultRule != null ? defaultRule.hashCode() : 0);
        result = 31 * result + (creationTime != null ? creationTime.hashCode() : 0);
        result = 31 * result + (lastUpdateTime != null ? lastUpdateTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SplitDefinition{" +
                "name='" + name + '\'' +
                ", environment=" + environment +
                ", killed=" + killed +
                ", treatments=" + treatments +
                ", defaultTreatment='" + defaultTreatment + '\'' +
                ", trafficAllocation=" + trafficAllocation +
                ", rules=" + rules +
                ", defaultRule=" + defaultRule +
                ", creationTime=" + creationTime +
                ", lastUpdateTime=" + lastUpdateTime +
                '}';
    }
}
