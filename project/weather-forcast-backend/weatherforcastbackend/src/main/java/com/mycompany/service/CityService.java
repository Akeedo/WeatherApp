package com.mycompany.service;

import com.mycompany.dao.CityDAO;
import com.mycompany.dao.CityDaoImpl;
import com.mycompany.entity.City;
import com.mycompany.repository.CityRepository;
import com.mycompany.rest.CityResource;
import lombok.NoArgsConstructor;
//import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@NoArgsConstructor
public class CityService {

    private final Logger log = LoggerFactory.getLogger(CityService.class);

    private CityDAO cityDAO;

    public CityService(CityDAO cityDAO) {
        this.cityDAO = cityDAO;
    }

    public Response getCities(){
        log.info("Get All the Users from Cities");
        List<City> cities = cityDAO.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("data", cities);
        return Response.status(Response.Status.OK).entity(response).build();
    }

}
