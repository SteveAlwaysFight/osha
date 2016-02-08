package com.oshippa.server.transfd.entities;

import com.oshippa.server.model.Entity;
import com.oshippa.server.model.Field;
import com.oshippa.server.transfd.ElementResponse;

import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by steve on 1/20/16.
 */
@XmlRootElement
public class EntityResponse extends ElementResponse{


    private List<FieldResponse> fields = new ArrayList<>();

    public EntityResponse(Entity entity){
        super(entity);
        for (Field field: entity.getFields()){
            fields.add(new FieldResponse(field));
        }

    }

    public List<FieldResponse> getFields() {
        return fields;
    }

    public void setFields(List<FieldResponse> fields) {
        this.fields = fields;
    }
}
