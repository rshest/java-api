package io.split.api.dtos.result;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ListResultDTO<T> {
    private List<T> objects;
    private Integer offset;
    private Integer limit;
    private Long totalCount;

    public ListResultDTO() {
        objects = new ArrayList<>();
    }

    public ListResultDTO(Builder builder) {
        this.objects = builder.objects;
        this.offset = builder.offset;
        this.limit = builder.limit;
        this.totalCount = builder.totalCount;
    }

    @JsonProperty
    public List<T> objects() {
        return objects;
    }

    @JsonProperty
    public Integer offset() {
        return offset;
    }

    @JsonProperty
    public Integer limit() {
        return limit;
    }

    @JsonProperty
    public Long totalCount() {
        return totalCount;
    }

    public void setObjects(List<T> objects) {
        this.objects = objects;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder<T> {
        private Integer offset;
        private Integer limit;
        private Long totalCount;
        private List<T> objects;

        public Builder offset(Integer offset) {
            this.offset = offset;
            return this;
        }

        public Builder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

        public Builder totalCount(Long totalCount) {
            this.totalCount = totalCount;
            return this;
        }

        public Builder objects(List<T> objects) {
            this.objects = objects;
            return this;
        }

        public Builder object(T object) {
            this.objects.add(object);
            return this;
        }

        Builder() {
            this.objects = new ArrayList<T>();
        }

        Builder(ListResultDTO prototype) {
            offset = prototype.offset;
            limit = prototype.limit;
            totalCount = prototype.totalCount;
            objects = prototype.objects;
        }

        public ListResultDTO<T> build() {
            return new ListResultDTO(this);
        }
    }

}
