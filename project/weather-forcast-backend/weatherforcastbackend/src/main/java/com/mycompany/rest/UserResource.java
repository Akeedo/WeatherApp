package com.mycompany.rest;
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
