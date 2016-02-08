/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oshippa.server.controller;

import com.oshippa.auth.resource.BaseResource;
import com.oshippa.auth.user.User;
import com.oshippa.common.exception.NullParametersException;
import com.oshippa.server.message.EntityException;
import com.oshippa.server.model.Entity;
import com.oshippa.server.service.EntityService;
import com.oshippa.server.transfd.entities.CreateEntityRequest;
import com.oshippa.server.transfd.entities.EntityResponse;
import com.oshippa.server.transfd.entities.UpdateEntityRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;
import java.util.stream.Collectors;

@Path("/admin/entities")
@Component
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class EntityResource extends BaseResource {

    Log log = LogFactory.getLog(EntityResource.class);
    @Autowired
    EntityService entityService;

    @RolesAllowed({"ROLE_USER"})
    @GET
    public List<EntityResponse> getEntities(@Context SecurityContext context) throws EntityException {
        User user = loadUserFromSecurityContext(context);
        if (user != null) {
            log.info("reading all entities by ");
            List<Entity> list = entityService.listEntities();
            List<EntityResponse> listResponse = list.stream().map(EntityResponse::new).collect(Collectors.toList());
            return listResponse;
        } else {
            log.info("rejected unauth user reading entities action");
            throw new UnauthorizedUserException("you don't have permit to loading all entities");
        }
    }

    @RolesAllowed({"ROLE_USER"})
    @POST
    public EntityResponse createEntity(@Context SecurityContext context, CreateEntityRequest entity) throws EntityException {
        if (entity == null) {
            throw new NullParametersException("entity");
        }
        User user = loadUserFromSecurityContext(context);
        if (user != null) {
            log.info("creating entity [" + entity.getName() + "] by [" + user.getId() + "]");
            Entity entity1 = entityService.createEntityFromRequest(entity);
            EntityResponse entityResponse = new EntityResponse(entity1);
            return entityResponse;
        } else {
            log.info("rejected unauth user reading entities action");
            throw new UnauthorizedUserException("you don't have permit to loading all entities");
        }
    }

    @RolesAllowed({"ROLE_USER"})
    @PUT
    public EntityResponse updateEntity(@Context SecurityContext context, UpdateEntityRequest request) {
        if (request == null)
            throw new NullParametersException("entity");
        User user = loadUserFromSecurityContext(context);
        if (user == null)
            throw new UnauthorizedUserException("you don't have permit to update this entities");
        log.info("updating entity [name:" + request.getName() + " id:" + request.getId() + "] by [" + user.getId() + "]");
        Entity entity1 = entityService.updateEntityFromRequest(request);
        EntityResponse entityResponse = new EntityResponse(entity1);
        return entityResponse;
    }

    @RolesAllowed({"ROLE_USER"})
    @Path("{id}")
    @GET
    public EntityResponse getEntity(final @PathParam("id") String entityId, final @Context SecurityContext securityContext) {
        User user = loadUserFromSecurityContext(securityContext);
        if (user == null)
            throw new UnauthorizedUserException("you don't have permit to update this entities");
        Entity entity = entityService.getEntity(entityId);
        return new EntityResponse(entity);
    }

    @RolesAllowed({"ROLE_USER"})
    @Path("{id}")
    @DELETE
    public Response delete(final @PathParam("id") String id){
        entityService.deleteEntity(id);
        return Response.ok().build();
    }
}
