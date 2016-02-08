package com.oshippa.server.message;


/**
 * Created by steve on 1/16/16.
 */
public class EntityException extends Throwable implements ExceptionMessage {
    public EntityException() {
        super();
    }
    private MessageCode retCd ;  //异常对应的返回码
    private String msgDes;  //异常对应的描述信息


    public EntityException(String message) {
        super(message);
        msgDes = message;
    }

    public EntityException(MessageCode retCd, String msgDes) {
        super();
        this.retCd = retCd;
        this.msgDes = msgDes;
    }


    @Override
    public MessageCode getMessageCode() {
        return this.retCd;
    }

    @Override
    public String getMessageContent() {
        return msgDes;
    }
}
