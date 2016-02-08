package com.oshippa.server.service;


import com.oshippa.server.message.EntityException;
import com.oshippa.server.model.Entity;
import com.oshippa.server.transfd.entities.CreateEntityRequest;
import com.oshippa.server.transfd.entities.EntityResponse;
import com.oshippa.server.transfd.entities.UpdateEntityRequest;

import java.util.List;

/**
 * Created by steve on 1/14/16.
 */
public interface EntityService {
    Entity createEntity(Entity entity);
    Entity createEntityFromRequest(CreateEntityRequest entityRequest);
    Entity updateEntity(Entity entity);
    Entity updateEntityFromRequest(UpdateEntityRequest entityRequest);
    Entity getEntity(String id);
    void deleteEntity(String id);
    String getEntityTableName(String id);
    List<Entity> listEntities();
    Entity getEntityDesc(String id);
    void createTableFiles(Entity entity);
    /**
     * 判断是否有重复的fields
     * */
    void checkFields(Entity entity) throws EntityException;
}
