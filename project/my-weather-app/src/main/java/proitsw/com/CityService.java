package proitsw.com;

import jakarta.ejb.Singleton;

import javax.ejb.EJB;
import java.util.List;

@Singleton
public class CityService {

    @EJB
    private CityDao cityDao;

    private final WeatherService weatherService;

    public CityService(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    public List<City> findAll(int first, int max) {
        return cityDao.findAll(first, max);
    }

    public long count() {
        return cityDao.count();
    }

    public String fetchWeatherByCityName(String cityName) {
        return weatherService.getWeatherDataByCity(cityName);
    }

    // Other CRUD operations
    public City save(City city) {
        return cityDao.save(city);
    }

    public City update(City city) {
        return cityDao.update(city);
    }

    public void delete(City city) {
        cityDao.delete(city);
    }

    public City find(Long id) {
        return cityDao.find(id);
    }
}
