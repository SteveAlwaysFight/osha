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
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

/**
 * Created by steve on 1/22/16.
 */

@XmlRootElement
public class CreateCustomizedRecordRequest {
    @Valid
    @NotNull
    private String elementId;
    @Valid
    @NotNull
    private Map map;
    @Valid
    @NotNull
    private CustomizedRecordType type;

    public CustomizedRecordType getType() {
        return type;
    }

    public void setType(CustomizedRecordType type) {
        this.type = type;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
