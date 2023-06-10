package com.mycompany.weather.jwt.rest;
import javax.json.JsonObject;
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
