package com.oshippa.auth.oauth2;


import com.oshippa.common.exception.BaseWebApplicationException;

/**
 * @version 1.0
 * @author: Iain Porter
 * @since 25/04/2013
 */
public class AuthorizationException extends BaseWebApplicationException {

    public AuthorizationException(String applicationMessage) {
        super(403, "Not authorized", applicationMessage);
    }

}
