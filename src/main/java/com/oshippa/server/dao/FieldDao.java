package com.oshippa.server.dao;


import com.oshippa.common.db.dao.IBaseGenericDao;
import com.oshippa.server.model.Field;

import java.util.List;

/**
 * Created by steve on 1/15/16.
 */
public interface FieldDao extends IBaseGenericDao<Field,String> {
    List<Field> findFieldByEntityId(String entity_id);
    void deleteFieldByEntityId(String entity_id);
}
