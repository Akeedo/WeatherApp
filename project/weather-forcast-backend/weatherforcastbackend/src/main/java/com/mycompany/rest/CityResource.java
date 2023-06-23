package com.mycompany.rest;


import com.mycompany.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;


@Path("/city")
public class CityResource {

    private final Logger log = LoggerFactory.getLogger(CityResource.class);

    @Autowired
    private CityService cityService;

    @GET
    public Response getCities(@QueryParam("size") int size,
                              @QueryParam("offset") int offset,
                              @QueryParam("searchText") String searchText
    ){
        try{
            log.info("Request received for application list by: {}", CityResource.class);
            Response response =  cityService.getCities();
            log.info("Response sent for City list : {}", response);
            return response;
        }
        catch (WebApplicationException ex){
            throw new WebApplicationException(ex.getMessage(), ex.getResponse().getStatus());
        }

    }
}
