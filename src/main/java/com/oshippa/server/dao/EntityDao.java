package com.oshippa.server.dao;


import com.oshippa.server.model.Entity;
import com.oshippa.common.db.dao.IBaseGenericDao;

/**
 * Created by steve on 1/15/16.
 */
public interface EntityDao extends IBaseGenericDao<Entity,String> {
    void deleteEntity(String id);
    String getEntityTableName(String id);
}
