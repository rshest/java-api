package io.split.api.client.exceptions;

public class SplitException extends RuntimeException {
    public SplitException(String s, Throwable t) {
        super(s, t);
    }
}
