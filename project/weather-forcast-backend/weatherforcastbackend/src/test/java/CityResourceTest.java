import com.mycompany.rest.CityResource;
import com.mycompany.entity.City;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CityResourceTest {
    private CityResource cityResource;

    @Before
    public void setup(){
        cityResource = new CityResource();
    }

    @Test
    public void testGetCity(){
        City city = cityResource.getCity();
        assertEquals("New York", city.getName());
        assertEquals("USA",city.getCountry());
        assertEquals(860000, city.getPopulation());
    }
}
