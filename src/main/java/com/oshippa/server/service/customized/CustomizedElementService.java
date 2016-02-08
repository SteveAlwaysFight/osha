package com.oshippa.server.service.customized;

import com.oshippa.common.exception.ValidationException;
import com.oshippa.server.helper.groovy.GroovyFactory;
import com.oshippa.server.helper.groovy.basedao.GBaseDao;
import com.oshippa.server.transfd.customizedRecord.CreateCustomizedRecordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by steve on 1/15/16.
 */
@Service
public abstract class CustomizedElementService<T> {



    @Autowired
    protected ApplicationContext context;

    @Autowired
    protected GroovyFactory groovyFactory;

    public abstract GBaseDao getDao(T entity);

    public abstract GBaseDao getDao(String entityId);

    public boolean insert(CreateCustomizedRecordRequest recordRequest,String userId) {
        return getDao(recordRequest.getElementId()).insert(recordRequest.getMap(),userId);
    }

    public Map get(String elementId,int customizedId,String userId ){
        return getDao(elementId).get(customizedId,userId);
    }

    public List list(String elementId,String userId){
        return getDao(elementId).list(userId);
    }

    public boolean delete(String elementId,int customizedId,String userId){
        return getDao(elementId).delete(customizedId,userId);
    }




}
