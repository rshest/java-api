package io.split.api.client.exceptions;

public class SplitRequestException extends SplitException {
    public SplitRequestException(String s) {
        super(s, null);
    }

    public SplitRequestException(String s, Throwable t) {
        super(s, t);
    }
}
