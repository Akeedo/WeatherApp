package com.mycompany.rest;

@Path("/city")
public class CityResource {
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
