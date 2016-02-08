/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oshippa.server.service;

import com.oshippa.common.db.service.BaseGenericServiceImpl;
import com.oshippa.server.dao.ModuleDao;
import com.oshippa.server.exception.ElementNotFoundException;
import com.oshippa.server.model.Entity;
import com.oshippa.server.model.Module;
import com.oshippa.server.transfd.module.CreateModuleRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.List;

/**
 * Created by steve on 1/26/16.
 */
@Service
public class ModuleServiceImpl extends BaseGenericServiceImpl<Module, String> implements ModuleService{
    @Autowired
    ModuleDao moduleDao;
    private static Log logger = LogFactory.getLog(ModuleServiceImpl.class);

    @Override
    public Module createModuleFromRequest(CreateModuleRequest request) {
        validate(request);
        Module module = new Module();
        module.setName(request.getName());
        module.setDescription(request.getDescription());
        save(module);
        return module;
    }

    @Autowired
    public ModuleServiceImpl(Validator validator) {
        super(validator);
    }

    @Override
    protected void validate(Object request) {
        super.validate(request);
    }

    @Override
    public void delete(Module entity) {
        moduleDao.delete(entity);
    }

    @Override
    public Module get(String id) {
        return moduleDao.get(id);
    }

    @Override
    public Module load(String id) {
        return moduleDao.load(id);
    }

    @Override
    public List<Module> loadAll() {
        return moduleDao.loadAll();
    }

    @Override
    public void save(Module entity) {
        moduleDao.save(entity);
    }

    @Override
    public void saveOrUpdate(Module entity) {
        moduleDao.saveOrUpdate(entity);
    }

    @Override
    public void update(Module entity) {
        moduleDao.update(entity);
    }

    @Override
    public Module getModule(String id) {
        Module entity = moduleDao.get(id);
        if (entity == null) {
            logger.error("can't find module id=" + id);
            throw new ElementNotFoundException("module");
        }
        logger.info("found module id=" + entity.getId() + " name=" + entity.getName());
        return entity;
    }
}
