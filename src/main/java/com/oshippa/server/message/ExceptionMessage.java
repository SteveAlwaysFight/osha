package com.oshippa.server.message;

/**
 * Created by steve on 1/16/16.
 */
public interface ExceptionMessage {
    MessageCode getMessageCode();
    String getMessageContent();
}
