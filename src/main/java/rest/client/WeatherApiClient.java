package rest.client;

import forecast.Forecast;

import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class WeatherApiClient {
    private static Client client = ClientBuilder.newClient();

    private static WebTarget baseTarget = client.target("http://localhost:8888/rest/");

    private static void allAvailableForecasts() {
        WebTarget target = baseTarget.path("all/");
        System.out.println(target.request().get(String.class));
    }

    private static void amountOfAllAvailableForecasts(){
        WebTarget target = baseTarget.path("count/");
        System.out.println(target.request().get(String.class));
    }

    private static void deleteById (String id){
        WebTarget target = baseTarget.path("post/deleteById/"+id);
        Response r = target.request().post(Entity.text(""));
        System.out.println(r.getStatusInfo());

    }

    private static void deleteByCity(String city){
        WebTarget target = baseTarget.path("post/deleteByCity/"+city);
        Response r = target.request().post(Entity.text(""));
        System.out.println(r.getStatusInfo());
    }

    private static void deleteByZipCode(String zipCode){
        WebTarget target = baseTarget.path("post/deleteByZipCode/"+zipCode);
        Response r = target.request().post(Entity.text(""));
        System.out.println(r.getStatusInfo());
    }

    private static void jsonAll(){
        WebTarget target = baseTarget.path("json/all");
        List<Forecast> forecasts = target
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Forecast>>(){});
        System.out.println(forecasts);
    }

    private static void jsonRainy(){
        WebTarget target = baseTarget.path("json/rainy");
        List<Forecast> forecasts = target
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Forecast>>(){});
        System.out.println(forecasts);
    }

    private static void jsonNonRainy(){
        WebTarget target = baseTarget.path("json/nonrainy");
        List<Forecast> forecasts = target
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Forecast>>(){});
        System.out.println(forecasts);
    }

    private static void jsonHottest(){
        WebTarget target = baseTarget.path("json/hottest");
        Forecast forecast = target
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get(Forecast.class);
        System.out.println(forecast);
    }


    private static void jsonByZipCode(String zipCode){
        WebTarget target = baseTarget.path("json/byZipCode/"+zipCode);
        Forecast forecast = target
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get(Forecast.class);
        System.out.println(forecast);
    }

    private static void jsonByCity(String city){
        WebTarget target = baseTarget.path("json/byCity/"+city);
        Forecast forecast = target
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get(Forecast.class);
        System.out.println(forecast);
    }

    private static void jsonAdd(Forecast forecast)
    {
        WebTarget target = baseTarget.path("json/post/add");
        Response response = target
            .request()
            .post(Entity.json(forecast));

        System.out.println(response.getStatus());
    }

    private static void jsonAddAll(Forecast[] forecasts)
    {
        WebTarget target = baseTarget.path("json/post/addAll");
        Response response = target
                .request()
                .post(Entity.json(forecasts));

        System.out.println(response.getStatus());
    }
    public static void main(String[] args) {
        //allAvailableForecasts();
        //amountOfAllAvailableForecasts();

        //deleteById("1");
        //allAvailableForecasts();

        //deleteByCity("Akron");
        //allAvailableForecasts();

        //deleteByZipCode("10003");
        //allAvailableForecasts();

        //jsonAll();
        //jsonRainy();
        //jsonNonRainy();
        //jsonHottest();

        //jsonByZipCode("10006");
        //jsonByCity("d");

        //Forecast forecast = new Forecast("TestCity", "11002", 68, 20, 10);
        //jsonAdd(forecast);
        //jsonByZipCode("11002");

        //Forecast[] forecasts = new Forecast[2];
        //forecasts[0] = new Forecast("addSAllTest1", "11010", 68, 20, 10);
        //forecasts[1] = new Forecast("addSAllTest2", "11012", 68, 20, 10);
        //jsonAddAll(forecasts);
        //allAvailableForecasts();
    }
}
