package io.split.api.dtos.result;

public class FailureDTO<T> {
    private T object;
    private Integer status;
    private String message;

    public FailureDTO() {
        object = null;
        status = null;
        message = null;
    }

    public T object() {
        return object;
    }

    public Integer status() {
        return status;
    }

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
