package com.mycompany.rest;


import com.mycompany.dao.CityDAO;
import com.mycompany.dao.CityDaoImpl;
import com.mycompany.service.CityService;
import lombok.NoArgsConstructor;
import org.eclipse.jetty.security.UserStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@Controller
@Path("/city")
@NoArgsConstructor
public class CityResource {

    private final Logger log = LoggerFactory.getLogger(CityResource.class);

    @GET
    public Response getCities(@QueryParam("size") int size,
                              @QueryParam("offset") int offset,
                              @QueryParam("searchText") String searchText
    ){
        try{
            log.info("Request received for application list by: {}", CityResource.class);
            CityDAO cityDAO = new CityDaoImpl();
            CityService cityService = new CityService(cityDAO);
            /*CityService cityService = new CityService();*/
            Response response =  cityService.getCities();
            log.info("Response sent for City list : {}", response);
            return response;
        }
        catch (WebApplicationException ex){
            throw new WebApplicationException(ex.getMessage(), ex.getResponse().getStatus());
        }

    }
}
