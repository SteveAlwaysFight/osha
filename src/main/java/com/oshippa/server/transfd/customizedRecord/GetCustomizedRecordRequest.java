/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oshippa.server.transfd.customizedRecord;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by steve on 1/23/16.
 */
public class GetCustomizedRecordRequest {
    @NotNull
    @Valid
    private String elementId;
    @NotNull
    @Valid
    private CustomizedRecordType type;
    @NotNull
    @Valid
    private int recordId;

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public CustomizedRecordType getType() {
        return type;
    }

    public void setType(CustomizedRecordType type) {
        this.type = type;
    }
}
