package io.split.api.dtos.split;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Matcher {

    private ConditionType type;
    private Boolean negate;
    private String attribute;
    private Boolean bool;
    private String string;
    private Long number;
    private Long date;
    private List<String> strings;
    private BetweenAttribute between;
    private Depends depends;

    public Matcher() {
    }

    private Matcher(Builder builder) {
        this.type = builder.type;
        this.negate = builder.negate;
        this.attribute = builder.attribute;
        this.bool = builder.bool;
        this.string = builder.string;
        this.number = builder.number;
        this.date = builder.date;
        this.strings = builder.strings;
        this.between = builder.between;
        this.depends = builder.depends;
    }

    @JsonProperty
    public ConditionType type() {
        return this.type;
    }

    @JsonProperty
    public Boolean negate() {
        return this.negate;
    }

    @JsonProperty
    public String attribute() {
        return this.attribute;
    }

    @JsonProperty
    public Boolean bool() {
        return this.bool;
    }

    @JsonProperty
    public String string() {
        return this.string;
    }

    @JsonProperty
    public Long number() {
        return this.number;
    }

    @JsonProperty
    public Long date() {
        return this.date;
    }

    @JsonProperty
    public List<String> strings() {
        return this.strings;
    }

    @JsonProperty
    public BetweenAttribute between() {
        return this.between;
    }

    @JsonProperty
    public Depends depends() {
        return this.depends;
    }

    public void setType(ConditionType type) {
        this.type = type;
    }

    public void setNegate(Boolean negate) {
        this.negate = negate;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public void setString(String string) {
        this.string = string;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public void setStrings(List<String> strings) {
        this.strings = strings;
    }

    public void setBetween(BetweenAttribute between) {
        this.between = between;
    }

    public void setDepends(Depends depends) {
        this.depends = depends;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(Matcher other) {
        return new Builder(other);
    }

    public static class Builder {
        private ConditionType type;
        private Boolean negate;
        private String attribute;
        private Boolean bool;
        private String string;
        private Long number;
        private Long date;
        private List<String> strings;
        private BetweenAttribute between;
        private Depends depends;

        public Builder type(ConditionType type) {
            this.type = type;
            return this;
        }

        public Builder negate(Boolean negate) {
            this.negate = negate;
            return this;
        }

        public Builder attribute(String attribute) {
            this.attribute = attribute;
            return this;
        }

        public Builder bool(Boolean bool) {
            this.bool = bool;
            return this;
        }

        public Builder string(String string) {
            this.string = string;
            return this;
        }

        public Builder number(Long number) {
            this.number = number;
            return this;
        }

        public Builder date(Long date) {
            this.date = date;
            return this;
        }

        public Builder strings(List<String> strings) {
            this.strings = strings;
            return this;
        }

        public Builder between(BetweenAttribute between) {
            this.between = between;
            return this;
        }

        public Builder depends(Depends depends) {
            this.depends = depends;
            return this;
        }

        Builder() { }

        Builder(Matcher prototype) {
            type = prototype.type;
            negate = prototype.negate;
            attribute = prototype.attribute;
            bool = prototype.bool;
            string = prototype.string;
            number = prototype.number;
            date = prototype.date;
            strings = prototype.strings;
            between = prototype.between;
            depends = prototype.depends;
        }

        public Matcher build() {
            return new Matcher(this);
        }
    }
}
