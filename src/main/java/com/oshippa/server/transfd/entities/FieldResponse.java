package com.oshippa.server.transfd.entities;

import com.oshippa.server.model.Field;
import com.oshippa.server.transfd.ElementResponse;

/**
 * Created by steve on 1/20/16.
 */
public class FieldResponse extends ElementResponse {

    private String fieldType;

    private int length;

    private boolean ifNull;

    public FieldResponse(Field element) {
        super(element);
        this.fieldType = element.getFieldType();
        this.length = element.getLength();
        this.ifNull = element.isIfNull();
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isIfNull() {
        return ifNull;
    }

    public void setIfNull(boolean ifNull) {
        this.ifNull = ifNull;
    }
}
