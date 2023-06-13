package com.mycompany.rest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/user")
public class UserResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(){
        User user = new User();
        user.setUsername("Akeed Anjum");
        user.setPassword("Akeedo55 Eee");
        return user;
    }
}
