/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oshippa.server.transfd.module;

import com.oshippa.common.model.Element;
import com.oshippa.server.model.Module;
import com.oshippa.server.transfd.ElementResponse;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by steve on 1/26/16.
 */
@XmlRootElement
public class ModuleResponse extends ElementResponse {
    public ModuleResponse(Module element) {
        super(element);
    }
}
