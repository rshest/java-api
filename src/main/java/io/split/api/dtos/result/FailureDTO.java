package io.split.api.dtos.result;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Nullable;

public class FailureDTO<T> {
    private T object;
    private Integer status;
    private String message;

    public FailureDTO() {
        object = null;
        status = null;
        message = null;
    }

    @JsonProperty
    public T object() {
        return object;
    }

    @Nullable
    @JsonProperty
    public Integer status() {
        return status;
    }

    @Nullable
    @JsonProperty
    public String message() {
        return message;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
