package io.split.api.dtos.result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultDTO<T> {
    private List<T> objects;
    private List<FailureDTO<T>> failed;
    private Map<String, String> metadata;

    public ResultDTO() {
        objects = new ArrayList<>();
        failed = new ArrayList<>();
        metadata = new HashMap<>();
    }

    public List<T> objects() {
        return objects;
    }

    public List<FailureDTO<T>> failed() {
        return failed;
    }

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
}
