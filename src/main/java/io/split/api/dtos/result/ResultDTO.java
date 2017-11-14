package io.split.api.dtos.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultDTO<T> {
    private List<T> objects;
    private List<FailureDTO<T>> failed;
    private Map<String, String> metadata;
    private Integer offset;
    private Integer limit;
    private Integer count;
    private Integer total;

    public ResultDTO() {
        objects = new ArrayList<>();
        failed = new ArrayList<>();
        metadata = new HashMap<>();
    }

    public ResultDTO(Builder builder) {
        objects = builder.objects;
        failed = builder.failed;
        metadata = builder.metadata;
        offset = builder.offset;
        limit = builder.limit;
        count = builder.count;
        total = builder.total;
    }

    @JsonProperty
    public List<T> objects() {
        return objects;
    }

    @JsonProperty
    public List<FailureDTO<T>> failed() {
        return failed;
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
    public Integer count() {
        return count;
    }

    @JsonProperty
    public Integer total() {
        return total;
    }

    @JsonProperty
    public Map<String, String> metadata() {
        return metadata;
    }

    public void setObjects(List<T> objects) {
        this.objects = objects;
    }

    public void setFailed(List<FailureDTO<T>> failed) {
        this.failed = failed;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static class Builder<T> {
        private Integer offset;
        private Integer limit;
        private List<T> objects;
        private List<FailureDTO<T>> failed;
        private Map<String, String> metadata;
        private Integer count;
        private Integer total;

        public Builder offset(Integer offset) {
            this.offset = offset;
            return this;
        }

        public Builder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

        public Builder count(Integer count) {
            this.count = count;
            return this;
        }

        public Builder total(Integer total) {
            this.total = total;
            return this;
        }

        public Builder objects(List<T> objects) {
            this.objects = objects;
            return this;
        }

        public Builder failed(List<FailureDTO<T>> failed) {
            this.failed = failed;
            return this;
        }

        public Builder metadata(Map<String, String> metadata) {
            this.metadata = metadata;
            return this;
        }

        public Builder metadata(String key, String value) {
            this.metadata.put(key, value);
            return this;
        }

        Builder() {
            this.objects = new ArrayList<>();
            this.failed = new ArrayList<>();
            this.metadata = new HashMap<>();
        }

        Builder(ResultDTO prototype) {
            offset = prototype.offset;
            limit = prototype.limit;
            objects = prototype.objects;
            failed = prototype.failed;
            total = prototype.total;
            count = prototype.count;
        }

        public ResultDTO<T> build() {
            return new ResultDTO<>(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResultDTO<?> resultDTO = (ResultDTO<?>) o;

        if (objects != null ? !objects.equals(resultDTO.objects) : resultDTO.objects != null) return false;
        if (failed != null ? !failed.equals(resultDTO.failed) : resultDTO.failed != null) return false;
        if (metadata != null ? !metadata.equals(resultDTO.metadata) : resultDTO.metadata != null) return false;
        if (offset != null ? !offset.equals(resultDTO.offset) : resultDTO.offset != null) return false;
        if (limit != null ? !limit.equals(resultDTO.limit) : resultDTO.limit != null) return false;
        if (count != null ? !count.equals(resultDTO.count) : resultDTO.count != null) return false;
        return total != null ? total.equals(resultDTO.total) : resultDTO.total == null;

    }

    @Override
    public int hashCode() {
        int result = objects != null ? objects.hashCode() : 0;
        result = 31 * result + (failed != null ? failed.hashCode() : 0);
        result = 31 * result + (metadata != null ? metadata.hashCode() : 0);
        result = 31 * result + (offset != null ? offset.hashCode() : 0);
        result = 31 * result + (limit != null ? limit.hashCode() : 0);
        result = 31 * result + (count != null ? count.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ResultDTO{" +
                "objects=" + objects +
                ", failed=" + failed +
                ", metadata=" + metadata +
                ", offset=" + offset +
                ", limit=" + limit +
                ", count=" + count +
                ", total=" + total +
                '}';
    }
}