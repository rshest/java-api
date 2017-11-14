package io.split.api.dtos.result;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultDTO<T> {
    private List<T> objects;
    private List<FailureDTO<T>> failed;
    private Map<String, String> metadata;
    private Integer offset;
    private Integer limit;
    private Long totalCount;

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

    @JsonProperty
    public Map<String, String> metadata() {
        return metadata;
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

    public void setFailed(List<FailureDTO<T>> failed) {
        this.failed = failed;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
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
}
