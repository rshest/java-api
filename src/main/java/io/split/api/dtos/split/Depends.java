package io.split.api.dtos.split;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Depends {
    private String splitName;
    private List<String> treatments;

    public Depends() {
    }

    private Depends(Builder builder) {
        this.splitName = builder.splitName;
        this.treatments = builder.treatments;
    }

    @JsonProperty
    public String splitName() {
        return this.splitName;
    }

    @JsonProperty
    public List<String> treatments() {
        return this.treatments;
    }

    public void setSplitName(String splitName) {
        this.splitName = splitName;
    }

    public void setTreatments(List<String> treatments) {
        this.treatments = treatments;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(Depends other) {
        return new Builder(other);
    }

    public static class Builder {
        private String splitName;
        private List<String> treatments;

        public Builder splitName(String splitName) {
            this.splitName = splitName;
            return this;
        }

        public Builder treatments(List<String> treatments) {
            this.treatments = treatments;
            return this;
        }

        Builder() { }

        Builder(Depends prototype) {
            splitName = prototype.splitName;
            treatments = prototype.treatments;
        }

        public Depends build() {
            return new Depends(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Depends depends = (Depends) o;

        if (splitName != null ? !splitName.equals(depends.splitName) : depends.splitName != null) return false;
        return treatments != null ? treatments.equals(depends.treatments) : depends.treatments == null;

    }

    @Override
    public int hashCode() {
        int result = splitName != null ? splitName.hashCode() : 0;
        result = 31 * result + (treatments != null ? treatments.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Depends{" +
                "splitName='" + splitName + '\'' +
                ", treatments=" + treatments +
                '}';
    }
}
