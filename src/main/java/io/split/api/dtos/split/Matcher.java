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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Matcher matcher = (Matcher) o;

        if (type != matcher.type) return false;
        if (negate != null ? !negate.equals(matcher.negate) : matcher.negate != null) return false;
        if (attribute != null ? !attribute.equals(matcher.attribute) : matcher.attribute != null) return false;
        if (bool != null ? !bool.equals(matcher.bool) : matcher.bool != null) return false;
        if (string != null ? !string.equals(matcher.string) : matcher.string != null) return false;
        if (number != null ? !number.equals(matcher.number) : matcher.number != null) return false;
        if (date != null ? !date.equals(matcher.date) : matcher.date != null) return false;
        if (strings != null ? !strings.equals(matcher.strings) : matcher.strings != null) return false;
        if (between != null ? !between.equals(matcher.between) : matcher.between != null) return false;
        return depends != null ? depends.equals(matcher.depends) : matcher.depends == null;

    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (negate != null ? negate.hashCode() : 0);
        result = 31 * result + (attribute != null ? attribute.hashCode() : 0);
        result = 31 * result + (bool != null ? bool.hashCode() : 0);
        result = 31 * result + (string != null ? string.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (strings != null ? strings.hashCode() : 0);
        result = 31 * result + (between != null ? between.hashCode() : 0);
        result = 31 * result + (depends != null ? depends.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Matcher{" +
                "type=" + type +
                ", negate=" + negate +
                ", attribute='" + attribute + '\'' +
                ", bool=" + bool +
                ", string='" + string + '\'' +
                ", number=" + number +
                ", date=" + date +
                ", strings=" + strings +
                ", between=" + between +
                ", depends=" + depends +
                '}';
    }
}
