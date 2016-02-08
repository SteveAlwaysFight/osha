package com.oshippa.auth.user.exception;

import com.oshippa.common.exception.BaseWebApplicationException;

/**
 * @version 1.0
 * @author: Iain Porter
 * @since 13/05/2013
 */
public class AlreadyVerifiedException extends BaseWebApplicationException {

    public AlreadyVerifiedException() {
        super(409, "Already verified", "The token has already been verified");
    }
}
