package proitsw.com;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class CityDaoImplementation implements CityDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<City> findAll(int first, int max) {
        TypedQuery<City> query = entityManager.createQuery("SELECT c FROM City c", City.class);
        query.setFirstResult(first);
        query.setMaxResults(max);
        return query.getResultList();
    }

    @Override
    public long count() {
        return entityManager.createQuery("SELECT COUNT(c) FROM City c", Long.class).getSingleResult();
    }

    @Override
    public City save(City city) {
        entityManager.persist(city);
        return city;
    }

    @Override
    public City update(City city) {
        return entityManager.merge(city);
    }

    @Override
    public void delete(City city) {
        if (entityManager.contains(city))
            entityManager.remove(city);
        else
            entityManager.remove(entityManager.merge(city));
    }

    @Override
    public City find(Long id) {
        return entityManager.find(City.class, id);
    }
}
