package proitsw.com;

import java.util.List;

public interface CityDao {
    List<City> findAll(int first, int max);
    long count();
    City save(City city);
    City update(City city);
    void delete(City city);
    City find(Long id);
}
