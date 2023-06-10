package com.mycompany.weather.jwt.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

public class SampleController {
    private static final Logger logger = LoggerFactory.getLogger(SampleController.class);

    @Inject
    private SecurityContext securityContext;

    @GET
    @Path("read")
    @PermitAll
    public Response read(){
        logger.info("read");
        JsonObject result = Json.createObjectBuilder()
                .add("user", securityContext.getCallerPrincipal() != null
                ? securityContext.getCallerPrincipal().getName(): "Anonymous")
                .add("message", "Read resource")
                .build();
                return Response.ok(result).build();
    }

    @POST
    @Path("write")
    public Response write(){
        logger.info("write");
        JsonObject result = Json.createObjectBuilder()
                .add("user", securityContext.getCallerPrincipal().getName())
                .add("message", "Write resource")
                .build();
        return Response.ok(result).build();
    }

    @DELETE
    @Path("delete")
    public Response delete(){
        logger.info("delete");
        JsonObject result = Json.createObjectBuilder()
                .add("user", securityContext.getCallerPrincipal().getName())
                .add("message", "Delete resource")
                .build();
        return Response.ok(result).build();
    }
}
