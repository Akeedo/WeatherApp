package com.mycompany.weather.jwt.rest;
public class SampleController {
    private static final Logger logger = LoggerFactory.getLogger(SampleController.class);
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
}
