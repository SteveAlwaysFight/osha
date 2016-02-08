package com.oshippa.auth.user.exception;

import com.oshippa.common.exception.BaseWebApplicationException;


public class AuthenticationException extends BaseWebApplicationException {

    public AuthenticationException() {
        super(401, "Authentication Error", "Authentication Error. The username or password were incorrect");
    }
}
