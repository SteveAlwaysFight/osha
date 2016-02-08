package com.oshippa.server.exception;

import com.oshippa.common.exception.BadRequestException;

/**
 * Created by steve on 1/20/16.
 */
public class EntityFieldDuplicatedException extends BadRequestException {
    public EntityFieldDuplicatedException(String developerMessage) {
        super("Entity Fields Duplicated [" + developerMessage+"]");
    }
}
