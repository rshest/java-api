package io.split.api.dtos.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListResultDTO<T> {
    private List<T> objects;
    private Integer offset;
    private Integer limit;
    private Long totalCount;

    public ListResultDTO() {
        objects = new ArrayList<>();
    }

    public ListResultDTO(Builder builder) {
        objects = builder.objects;
        offset = builder.offset;
        limit = builder.limit;
        totalCount = builder.totalCount;
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

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
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

        Builder() {
            this.objects = new ArrayList<>();
        }

        Builder(ListResultDTO prototype) {
            offset = prototype.offset;
            limit = prototype.limit;
            totalCount = prototype.totalCount;
            objects = prototype.objects;
        }

        public ListResultDTO<T> build() {
            return new ListResultDTO<>(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListResultDTO<?> that = (ListResultDTO<?>) o;

        if (objects != null ? !objects.equals(that.objects) : that.objects != null) return false;
        if (offset != null ? !offset.equals(that.offset) : that.offset != null) return false;
        if (limit != null ? !limit.equals(that.limit) : that.limit != null) return false;
        return totalCount != null ? totalCount.equals(that.totalCount) : that.totalCount == null;

    }

    @Override
    public int hashCode() {
        int result = objects != null ? objects.hashCode() : 0;
        result = 31 * result + (offset != null ? offset.hashCode() : 0);
        result = 31 * result + (limit != null ? limit.hashCode() : 0);
        result = 31 * result + (totalCount != null ? totalCount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ListResultDTO{" +
                "objects=" + objects +
                ", offset=" + offset +
                ", limit=" + limit +
                ", totalCount=" + totalCount +
                '}';
    }
}