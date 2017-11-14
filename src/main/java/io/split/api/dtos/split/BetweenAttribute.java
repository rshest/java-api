package io.split.api.dtos.split;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BetweenAttribute {
    private Long from;
    private Long to;

    public BetweenAttribute() {
    }

    private BetweenAttribute(Builder builder) {
        this.from = builder.from;
        this.to = builder.to;
    }

    @JsonProperty
    public Long from() {
        return this.from;
    }

    @JsonProperty
    public Long to() {
        return this.to;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public void setTo(Long to) {
        this.to = to;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(BetweenAttribute other) {
        return new Builder(other);
    }

    public static class Builder {
        private Long from;
        private Long to;

        public Builder from(Long from) {
            this.from = from;
            return this;
        }

        public Builder to(Long to) {
            this.to = to;
            return this;
        }

        Builder() { }

        Builder(BetweenAttribute prototype) {
            from = prototype.from;
            to = prototype.to;
        }

        public BetweenAttribute build() {
            return new BetweenAttribute(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BetweenAttribute that = (BetweenAttribute) o;

        if (from != null ? !from.equals(that.from) : that.from != null) return false;
        return to != null ? to.equals(that.to) : that.to == null;

    }

    @Override
    public int hashCode() {
        int result = from != null ? from.hashCode() : 0;
        result = 31 * result + (to != null ? to.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BetweenAttribute{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}
