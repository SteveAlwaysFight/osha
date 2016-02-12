package com.oshippa.server.controller;

import com.oshippa.auth.resource.BaseResource;
import com.oshippa.auth.user.User;
import com.oshippa.common.exception.NullParametersException;
import com.oshippa.server.message.EntityException;
import com.oshippa.server.model.file.Report;
import com.oshippa.server.service.ReportService;
import com.oshippa.server.transfd.entities.EntityResponse;
import com.oshippa.server.transfd.entities.UpdateEntityRequest;
import com.oshippa.server.transfd.report.CreateReportRequest;
import com.oshippa.server.transfd.report.ReportResponse;
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

/**
 * Created by steve on 1/22/16.
 */
@Path("/admin/reports")
@Component
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class ReportResource extends BaseResource {
    Log log = LogFactory.getLog(ReportResource.class);
    @Autowired
    ReportService reportService;

    @RolesAllowed({"ROLE_USER"})
    @GET
    public List<ReportResponse> getReports(@Context SecurityContext context) throws EntityException {
        User user = loadUserFromSecurityContext(context);
        if (user != null) {
            log.info("reading all entities by " + user.getId());
            List<Report> list = reportService.listReports();
            List<ReportResponse> listResponse = list.stream().map(ReportResponse::new).collect(Collectors.toList());
            return listResponse;
        } else {
            log.info("rejected unauth user reading entities action");
            throw new UnauthorizedUserException("you don't have permit to loading all entities");
        }
    }

    @RolesAllowed({"ROLE_USER"})
    @POST
    public ReportResponse createReport(@Context SecurityContext context, CreateReportRequest request) throws EntityException {
        if (request == null) {
            throw new NullParametersException("report");
        }
        User user = loadUserFromSecurityContext(context);
        if (user != null) {
            log.info("creating report [" + request.getName() + "] by [" + user.getId() + "]");
            ReportResponse reportResponse = reportService.createReportFromRequest(request);
            return reportResponse;
        } else {
            log.info("rejected unauth user creating report action");
            throw new UnauthorizedUserException("you don't have permit to create reports");
        }
    }

    @RolesAllowed({"ROLE_USER"})
    @PUT
    public EntityResponse updateEntity(@Context SecurityContext context, UpdateEntityRequest request) {
//        if (request == null)
//            throw new NullParametersException("entity");
//        User user = loadUserFromSecurityContext(context);
//        if (user == null)
//            throw new UnauthorizedUserException("you don't have permit to update this entities");
//        log.info("updating entity [name:" + request.getName() + " id:" + request.getId() + "] by [" + user.getId() + "]");
//        Entity entity1 = entityService.updateEntityFromRequest(request);
//        EntityResponse entityResponse = new EntityResponse(entity1);
        return null;
    }

    @RolesAllowed({"ROLE_USER"})
    @Path("{id}")
    @GET
    public ReportResponse gerReport(final @PathParam("id") String reportId, final @Context SecurityContext securityContext) {
        User user = loadUserFromSecurityContext(securityContext);
        if (user == null)
            throw new UnauthorizedUserException("you don't have permit to update this entities");
        return reportService.getReport(reportId);
    }

    @RolesAllowed({"ROLE_USER"})
    @Path("{id}")
    @DELETE
    public Response delete(final @PathParam("id") String id){
        reportService.deleteReport(id);
        return Response.ok().build();
    }
}
