package io.split.api.dtos.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.internal.Nullable;

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

    @JsonProperty
    public List<T> objects() {
        return objects;
    }

    @JsonProperty
    public List<FailureDTO<T>> failed() {
        return failed;
    }

    @Nullable
    @JsonProperty
    public Integer offset() {
        return offset;
    }

    @Nullable
    @JsonProperty
    public Integer limit() {
        return limit;
    }

    @Nullable
    @JsonProperty
    public Integer count() {
        return count;
    }

    @Nullable
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
}