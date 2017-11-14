package io.split.api.dtos.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FailureDTO<T> {
    private T object;
    private Integer status;
    private String message;

    public FailureDTO() {
        object = null;
        status = null;
        message = null;
    }

    private FailureDTO(FailureDTO.Builder<T> builder) {
        this.object = builder.object;
        this.status = builder.status;
        this.message = builder.message;
    }

    @JsonProperty
    public T object() {
        return object;
    }

    @JsonProperty
    public Integer status() {
        return status;
    }

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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder<T> {
        private String message;
        private Integer status;
        private T object;

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder status(Integer status) {
            this.status = status;
            return this;
        }

        public Builder object(T object) {
            this.object = object;
            return this;
        }

        Builder() { }

        Builder(FailureDTO<T> prototype) {
            object = prototype.object;
            status = prototype.status;
            message = prototype.message;
        }

        public FailureDTO<T> build() {
            return new FailureDTO(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FailureDTO<?> that = (FailureDTO<?>) o;

        if (object != null ? !object.equals(that.object) : that.object != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        return message != null ? message.equals(that.message) : that.message == null;

    }

    @Override
    public int hashCode() {
        int result = object != null ? object.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FailureDTO{" +
                "object=" + object +
                ", status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
