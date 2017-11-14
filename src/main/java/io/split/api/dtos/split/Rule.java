package io.split.api.dtos.split;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Rule {

    private List<Bucket> buckets;
    private Condition condition;

    public Rule() {
    }

    private Rule(Builder builder) {
        this.buckets = builder.buckets;
        this.condition = builder.condition;
    }

    @JsonProperty
    public Condition condition() {
        return this.condition;
    }

    @JsonProperty
    public List<Bucket> buckets() {
        return this.buckets;
    }

    public void setBuckets(List<Bucket> buckets) {
        this.buckets = buckets;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(Rule other) {
        return new Builder(other);
    }

    public static class Builder {
        private List<Bucket> buckets;
        private Condition condition;

        public Builder buckets(List<Bucket>  buckets) {
            this.buckets = buckets;
            return this;
        }

        public Builder condition(Condition condition) {
            this.condition = condition;
            return this;
        }


        Builder() { }

        Builder(Rule prototype) {
            buckets = prototype.buckets;
            condition = prototype.condition;
        }

        public Rule build() {
            return new Rule(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rule rule = (Rule) o;

        if (buckets != null ? !buckets.equals(rule.buckets) : rule.buckets != null) return false;
        return condition != null ? condition.equals(rule.condition) : rule.condition == null;

    }

    @Override
    public int hashCode() {
        int result = buckets != null ? buckets.hashCode() : 0;
        result = 31 * result + (condition != null ? condition.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "buckets=" + buckets +
                ", condition=" + condition +
                '}';
    }
}
