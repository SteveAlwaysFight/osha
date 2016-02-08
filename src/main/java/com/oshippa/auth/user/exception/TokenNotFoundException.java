package com.oshippa.auth.user.exception;


import com.oshippa.common.exception.BaseWebApplicationException;

/**
 * @version 1.0
 * @author: Iain Porter
 * @since 13/05/2013
 */
public class TokenNotFoundException extends BaseWebApplicationException {

    public TokenNotFoundException() {
        super(404, "Token Not Found", "No token could be found for that Id");
    }
}
