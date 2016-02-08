/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oshippa.server.service.customized;

import com.oshippa.server.helper.groovy.basedao.GBaseDao;
import com.oshippa.server.model.Entity;
import com.oshippa.server.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by steve on 1/22/16.
 */
@Service
public class CustomizedEntityService extends CustomizedElementService<Entity> {

    @Autowired
    EntityService entityService;

    @Override
    public GBaseDao getDao(Entity entity) {
        GBaseDao gBaseDao = (GBaseDao) context.getBean(entity.getTableName() + "_Dao");
        return gBaseDao;
    }

    @Override
    public GBaseDao getDao(String entityId) {
        {
            Entity entity = entityService.getEntityDesc(entityId);
            GBaseDao gBaseDao = null;
            try {
                gBaseDao = (GBaseDao) context.getBean(entity.getTableName() + "_Dao");

            } catch (Exception e) {
                groovyFactory.addGroovyFile(entity);
                gBaseDao = (GBaseDao) context.getBean(entity.getTableName() + "_Dao");
            }
            return gBaseDao;
        }
    }
}
