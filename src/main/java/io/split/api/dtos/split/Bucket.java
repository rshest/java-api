package io.split.api.dtos.split;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Bucket {

    private String treatment;
    private Integer size;

    public Bucket() { }

    private Bucket(Builder builder) {
        this.treatment = builder.treatment;
        this.size = builder.size;
    }

    @JsonProperty
    public String treatment() {
        return this.treatment;
    }

    @JsonProperty
    public Integer size() {
        return this.size;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(Bucket other) {
        return new Builder(other);
    }

    public static class Builder {
        private String treatment;
        private Integer size;

        public Builder treatment(String treatment) {
            this.treatment = treatment;
            return this;
        }

        public Builder size(Integer size) {
            this.size = size;
            return this;
        }


        Builder() { }

        Builder(Bucket prototype) {
            treatment = prototype.treatment;
            size = prototype.size;
        }

        public Bucket build() {
            return new Bucket(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bucket bucket = (Bucket) o;

        if (treatment != null ? !treatment.equals(bucket.treatment) : bucket.treatment != null) return false;
        return size != null ? size.equals(bucket.size) : bucket.size == null;

    }

    @Override
    public int hashCode() {
        int result = treatment != null ? treatment.hashCode() : 0;
        result = 31 * result + (size != null ? size.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Bucket{" +
                "treatment='" + treatment + '\'' +
                ", size=" + size +
                '}';
    }
}
