/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oshippa.server.service.customized;

import com.oshippa.auth.service.BaseService;
import com.oshippa.server.transfd.customizedRecord.CreateCustomizedRecordRequest;
import com.oshippa.server.transfd.customizedRecord.CustomizedRecordType;
import com.oshippa.server.transfd.customizedRecord.GetCustomizedRecordRequest;
import com.oshippa.server.transfd.customizedRecord.ListCustomizedRecordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.List;
import java.util.Map;

/**
 * Created by steve on 1/22/16.
 */
@Service
public class CustomizedElementServiceFactory extends BaseService {
    @Autowired
    CustomizedEntityService customizedEntityService;

    @Autowired
    public CustomizedElementServiceFactory(Validator validator) {
        super(validator);
    }


    public boolean insert(CreateCustomizedRecordRequest recordRequest,String userid){
        validate(recordRequest);
        return getService(recordRequest.getType()).insert(recordRequest,userid);
    }

    public List list(ListCustomizedRecordRequest recordRequest,String userid){
        validate(recordRequest);
        return getService(recordRequest.getType()).list(recordRequest.getElementId(),userid);
    }


    public Map get(GetCustomizedRecordRequest recordRequest,String userid){
        validate(recordRequest);
        return getService(recordRequest.getType()).get(recordRequest.getElementId(),recordRequest.getRecordId(),userid);
    }

    public boolean delete(GetCustomizedRecordRequest recordRequest,String userid){
        validate(recordRequest);
        return  getService(recordRequest.getType()).delete(recordRequest.getElementId(),recordRequest.getRecordId(),userid);
    }

    private CustomizedElementService getService(CustomizedRecordType type) {
        switch (type) {
            case ENTITY:
                return customizedEntityService;
            case REPORT:
                return null;
            default:
                return null;
        }


    }
}
