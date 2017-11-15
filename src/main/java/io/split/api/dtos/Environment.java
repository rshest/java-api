package io.split.api.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Environment {
    private String id;
    private String name;

    private Environment() {

    }

    private Environment(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    @JsonProperty
    public String id() {
        return id;
    }

    @JsonProperty
    public String name() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(Environment other) {
        return new Builder(other);
    }

    public static class Builder {
        private String id;
        private String name;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        Builder() {
        }

        Builder(Environment prototype) {
            id = prototype.id;
            name = prototype.name;
        }

        public Environment build() {
            return new Environment(this);
        }
    }

    @Override
    public String toString() {
        return "Environment{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Environment that = (Environment) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
