package com.oshippa.auth.sample;

import com.oshippa.auth.resource.BaseResource;
import com.oshippa.auth.user.User;
import com.oshippa.server.message.EntityException;
import com.oshippa.server.model.Entity;
import com.oshippa.server.model.Field;
import com.oshippa.server.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 * Created by iainporter on 13/10/2014.
 */
@Path("/admin/samples")
@Component
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class SampleResource extends BaseResource {

    @Autowired
    EntityService entityService;
    @RolesAllowed({"ROLE_GUEST"})
    @GET
    public Response getSample(@Context SecurityContext sc) throws EntityException {
        User user = loadUserFromSecurityContext(sc);

//        Entity entity = new Entity();
//        Field field = new Field();
//        field.setName("name");
//        field.setDeleted(false);
//        field.setFieldType("string");
//        field.setIfNull(true);
//        Field field2 = new Field();
//        field2.setName("age");
//        field2.setDeleted(false);
//        field2.setFieldType("int");
//        field2.setIfNull(true);
//        entity.setName("new_company");
//        entity.addField(field);
//        entity.addField(field2);
//        entityService.createEntity(entity);
//        System.out.println(entity);




        return Response.ok().entity("{\"message\":\"" + user.getEmailAddress() + " is authorized to access\"}").build();

    }
}
