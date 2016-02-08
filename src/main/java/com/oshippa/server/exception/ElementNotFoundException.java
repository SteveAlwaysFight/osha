package com.oshippa.server.exception;

import com.oshippa.common.exception.BaseWebApplicationException;
import com.oshippa.common.model.Element;

/**
 * Created by steve on 1/20/16.
 */
public class ElementNotFoundException extends BaseWebApplicationException{
    public ElementNotFoundException(String element) {
        super(404, "Element Not Found", "No "+element +" could be found for that Id");

    }

}
