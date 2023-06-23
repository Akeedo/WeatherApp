package com.mycompany.rest;

import com.mycompany.entity.City;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/city")
public class CityResource {
    private final Logger log = LoggerFactory.getLogger(CityResource.class);
    @Autowired
    private CityService cityService;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public City getCity(){
        City city = new City();
        city.setName("New York");
        city.setCountry("USA");
        city.setPopulation(860000);
        return city;
    }
}
