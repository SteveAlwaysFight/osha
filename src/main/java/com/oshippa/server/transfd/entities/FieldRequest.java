package com.oshippa.server.transfd.entities;

import com.oshippa.server.transfd.ElementRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by steve on 1/20/16.
 */
@XmlRootElement
public class FieldRequest extends ElementRequest {
    @NotNull
    @Valid
    private String fieldType;

    private  String id;

    @NotNull
    private int length;

    @NotNull
    private boolean ifNull;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
