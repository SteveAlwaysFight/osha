package com.oshippa.auth.user.resource;

import com.oshippa.auth.user.VerificationTokenService;
import com.oshippa.auth.user.api.LostPasswordRequest;
import com.oshippa.auth.user.api.PasswordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @version 1.0
 * @author: Iain Porter
 * @since 13/05/2013
 */
@Path("/admin/password")
@Component
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class PasswordResource {

    @Autowired
    protected VerificationTokenService verificationTokenService;

    @PermitAll
    @Path("tokens")
    @POST
    public Response sendEmailToken(LostPasswordRequest request) {
        verificationTokenService.sendLostPasswordToken(request);
        return Response.ok().build();
    }

    @PermitAll
    @Path("tokens/{token}")
    @POST
    public Response resetPassword(@PathParam("token") String base64EncodedToken, PasswordRequest request) {
        verificationTokenService.resetPassword(base64EncodedToken, request);
        return Response.ok().build();
    }
}
