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
import com.oshippa.server.model.Module;
import com.oshippa.server.service.EntityService;
import com.oshippa.server.service.ModuleService;
import com.oshippa.server.transfd.entities.CreateEntityRequest;
import com.oshippa.server.transfd.entities.EntityResponse;
import com.oshippa.server.transfd.module.CreateModuleRequest;
import com.oshippa.server.transfd.module.ModuleResponse;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by steve on 1/26/16.
 */

@Path("/admin/modules")
@Component
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class ModuleResource extends BaseResource {

    Log log = LogFactory.getLog(ModuleResource.class);
    @Autowired
    ModuleService moduleService;

    @RolesAllowed({"ROLE_USER"})
    @GET
    public List<ModuleResponse> getModules(@Context SecurityContext context) throws EntityException {
        User user = loadUserFromSecurityContext(context);
        if (user != null) {
            log.info("reading all module by ");
            List<Module> list = moduleService.loadAll();
            List<ModuleResponse> listResponse = new ArrayList<>();
            for(Module module:list){
                ModuleResponse response = new ModuleResponse(module);
                listResponse.add(response);
            }
            return listResponse;
        } else {
            log.info("rejected unauth user reading entities action");
            throw new UnauthorizedUserException("you don't have permit to loading all entities");
        }
    }

    @RolesAllowed({"ROLE_USER"})
    @GET
    @Path("{id}")
    public ModuleResponse getModule(@Context SecurityContext context,@PathParam("id") String id){
        User user = loadUserFromSecurityContext(context);
        if (user == null)
            throw new UnauthorizedUserException("you don't have permit to update this entities");
        Module entity = moduleService.getModule(id);
        return new ModuleResponse(entity);
    }

    @RolesAllowed({"ROLE_USER"})
    @POST
    public ModuleResponse createModule(@Context SecurityContext context, CreateModuleRequest request) throws EntityException {
        if (request == null) {
            throw new NullParametersException("module");
        }
        User user = loadUserFromSecurityContext(context);
        if (user != null) {
            log.info("creating module [" + request.getName() + "] by [" + user.getId() + "]");
            Module module = moduleService.createModuleFromRequest(request);
            ModuleResponse entityResponse = new ModuleResponse(module);
            return entityResponse;
        } else {
            log.info("rejected unauth user creating module action");
            throw new UnauthorizedUserException("you don't have permit to create modules");
        }
    }

}
