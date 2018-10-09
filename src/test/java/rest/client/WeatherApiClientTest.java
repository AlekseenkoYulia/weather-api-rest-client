package rest.client;

import forecast.Forecast;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.*;

public class WeatherApiClientTest {
    private static WeatherApiClient client;
    private int size;
    private static Forecast testForecast;
    private static Forecast[] forecasts;

    @BeforeClass
    public static void init() {
        client = new WeatherApiClient();

        testForecast = new Forecast("Test1", Integer.toString(11000), 68, 20, 10);

        forecasts = new Forecast[2];
        forecasts[0] = new Forecast("Test2", Integer.toString(11001), 68, 20, 10);
        forecasts[1] = new Forecast("Test3", Integer.toString(11002), 68, 20, 10);
    }

    @Before
    public void initData(){
        client.jsonAdd(testForecast);
        size = Integer.parseInt(client.amountOfAllAvailableForecasts());
    }

    @After
    public void deleteTestData(){
        client.deleteByCity("Test1");
        client.deleteByCity("Test2");
        client.deleteByCity("Test3");
    }

    @Test
    public void shouldReturnAllAvailableForecasts(){
        assertEquals(size, client.allAvailableForecasts().split("\n").length);
    }

    @Test
    public void shouldReturnAmountOfAllAvailableForecasts(){
        assertEquals(1001, Integer.parseInt(client.amountOfAllAvailableForecasts()));
    }

    @Test
    public void shouldDeleteById(){
        Forecast forecast = client.jsonByCity("Test1");
        client.deleteById(Integer.toString(forecast.getId()));
        assertEquals(size-1, Integer.parseInt(client.amountOfAllAvailableForecasts()));
    }

    @Test
    public void shouldDeleteByCity(){
        client.deleteByCity("Test1");
        assertEquals(size-1, Integer.parseInt(client.amountOfAllAvailableForecasts()));
    }

    @Test
    public void shouldDeleteByZipCode(){
        client.deleteByZipCode("11000");
        assertEquals(size-1, Integer.parseInt(client.amountOfAllAvailableForecasts()));
    }

    @Test
    public void shouldReturnAllForecasts(){
        List<Forecast> forecasts = client.jsonAll();
        assertEquals(size, forecasts.size());
    }

    @Test
    public void shouldReturnRainyForecasts(){
        List<Forecast> forecasts = client.jsonRainy();
        assertNotNull(forecasts);
    }

    @Test
    public void shouldReturnNonrainyForecasts(){
        List<Forecast> forecasts = client.jsonNonRainy();
        assertNotNull(forecasts);
    }

    @Test
    public void shouldReturnHottestForecast(){
        Forecast forecast = client.jsonHottest();
        assertNotNull(forecast);
    }

    @Test
    public void shouldReturnJsonByZipCode(){
        Forecast forecast = client.jsonByZipCode("11000");
        assertNotNull(forecast);
        assertEquals("Test1", forecast.getCity());
    }
    @Test
    public void shouldReturnJsonByCity(){
        Forecast forecast = client.jsonByCity("Test1");
        assertNotNull(forecast);
    }

    @Test
    public void shouldAddOneForecast(){
        client.jsonAdd(forecasts[0]);
        Forecast forecast = client.jsonByCity("Test2");
        assertNotNull(forecast);
    }

    @Test
    public void shouldAddForecasts(){
        client.jsonAddAll(forecasts);
        assertNotNull(client.jsonByCity("Test2"));
        assertNotNull(client.jsonByCity("Test3"));
        assertEquals(size+2, Integer.parseInt(client.amountOfAllAvailableForecasts()));
    }
}