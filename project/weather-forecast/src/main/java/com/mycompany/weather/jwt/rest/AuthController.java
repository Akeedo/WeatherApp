package com.mycompany.weather.jwt.rest;

import javax.json.Json;
import javax.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Inject
    private SecurityContext securityContext;

    @GET
    @Path("login")
    public Response login(){
        logger.info("login");
        if(securityContext.getCallerPrincipal() != null){
            JsonObject result = Json.createObjectBuilder()
                    .add("user", securityContext.getCallerPrincipal().getName())
                    .build();
            return Response.ok(result).build();
        }
        return Response.status(UNAUTHORIZED).build();
    }
}
