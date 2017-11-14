package io.split.api.dtos.split;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Condition {
    private Combiner combiner;
    private List<Matcher> matchers;

    public Condition() {
    }

    private Condition(Builder builder) {
        this.combiner = builder.combiner;
        this.matchers = builder.matchers;
    }

    @JsonProperty
    public Combiner combiner() {
        return this.combiner;
    }

    @JsonProperty
    public List<Matcher> matchers() {
        return this.matchers;
    }


    public void setCombiner(Combiner combiner) {
        this.combiner = combiner;
    }

    public void setMatchers(List<Matcher> matchers) {
        this.matchers = matchers;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(Condition other) {
        return new Builder(other);
    }

    public static class Builder {
        private Combiner combiner;
        private List<Matcher> matchers;

        public Builder combiner(Combiner combiner) {
            this.combiner = combiner;
            return this;
        }

        public Builder matchers(List<Matcher> matchers) {
            this.matchers  = matchers;
            return this;
        }


        Builder() { }

        Builder(Condition prototype) {
            combiner = prototype.combiner;
            matchers = prototype.matchers;
        }

        public Condition build() {
            return new Condition(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Condition condition = (Condition) o;

        if (combiner != condition.combiner) return false;
        return matchers != null ? matchers.equals(condition.matchers) : condition.matchers == null;

    }

    @Override
    public int hashCode() {
        int result = combiner != null ? combiner.hashCode() : 0;
        result = 31 * result + (matchers != null ? matchers.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Condition{" +
                "combiner=" + combiner +
                ", matchers=" + matchers +
                '}';
    }
}
