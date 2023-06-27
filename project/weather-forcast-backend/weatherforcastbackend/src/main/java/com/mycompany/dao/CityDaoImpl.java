package com.mycompany.dao;

import com.mycompany.entity.City;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;


public class CityDaoImpl implements CityDAO {
    @Override
    public City findById(Long id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("backendDB");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        City city = entityManager.find(City.class, id);
        entityManager.close();
        return city;
    }

    @Override
    public List<City> findAll() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("backendDB");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<City> cities = entityManager.createQuery("SELECT u FROM City u", City.class).getResultList();
        entityManager.close();
        return cities;
    }

    @Override
    public void save(City city) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("backendDB");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(city);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void delete(City city) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("backendDB");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        if (entityManager.contains(city)) {
            entityManager.remove(city);
        } else {
            entityManager.remove(entityManager.merge(city));
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
