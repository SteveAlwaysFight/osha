package com.oshippa.common.exception;

/**
 * Created by steve on 1/20/16.
 */
public class NullParametersException extends BadRequestException {
    public NullParametersException(String developerMessage) {
        super("null parameters ["+developerMessage+"]");
    }
}
