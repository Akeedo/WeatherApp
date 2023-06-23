package com.mycompany.service;

import com.mycompany.entity.City;
import com.mycompany.repository.CityRepository;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CityService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private CityRepository cityRepository;

    public Response getCities(){
        List<City> cities = cityRepository.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("data", cities);
        return Response.status(Response.Status.OK).entity(response).build();
    }

}
