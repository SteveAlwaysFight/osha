/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oshippa.server.service;

import com.oshippa.common.db.service.IBaseGenericService;
import com.oshippa.server.model.Module;
import com.oshippa.server.transfd.module.CreateModuleRequest;

/**
 * Created by steve on 1/26/16.
 */
public interface ModuleService extends IBaseGenericService<Module,String>{
    Module createModuleFromRequest(CreateModuleRequest request);
    Module getModule(String id);

}
