package com.mycompany.dao;

import com.mycompany.entity.City;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public interface CityDAO {
    City findById(Long id);
    List<City> findAll();
    void save(City city);
    void delete(City city);
}
