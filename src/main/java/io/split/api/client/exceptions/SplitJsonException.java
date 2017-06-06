package io.split.api.client.exceptions;

public class SplitJsonException extends SplitException {
    public SplitJsonException(Exception e) {
        super(" ",e);
    }
}
