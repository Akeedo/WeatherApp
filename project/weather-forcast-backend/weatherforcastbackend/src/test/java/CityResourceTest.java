import com.mycompany.rest.CityResource;
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
