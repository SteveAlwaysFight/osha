package com.oshippa.common.exception;

/**
 * Created by steve on 1/20/16.
 */
public class BadRequestException extends  BaseWebApplicationException{
    public BadRequestException( String developerMessage) {
        super(400, "Bad Request ", developerMessage);
    }
}
