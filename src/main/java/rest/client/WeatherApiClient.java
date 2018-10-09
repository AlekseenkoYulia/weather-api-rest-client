package rest.client;

import forecast.Forecast;

import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class WeatherApiClient {
    private  Client client;

    private  WebTarget baseTarget;

    public WeatherApiClient(){
        client = ClientBuilder.newClient();
        baseTarget = client.target("http://localhost:8888/rest/");
    }
    public String allAvailableForecasts() {
        return baseTarget
                .path("all/")
                .request()
                .get(String.class);
    }

    public String amountOfAllAvailableForecasts(){
        return baseTarget
                .path("count/")
                .request()
                .get(String.class);
    }

    public void deleteById (String id){
        baseTarget
                .path("post/deleteById/"+id)
                .request()
                .post(null);
    }

    public void deleteByCity(String city){
       baseTarget
               .path("post/deleteByCity/"+city)
               .request()
               .post(null);
    }

    public void deleteByZipCode(String zipCode){
        baseTarget
                .path("post/deleteByZipCode/"+zipCode)
                .request()
                .post(null);
    }

    public List<Forecast> jsonAll(){
        List<Forecast> forecasts = baseTarget
                .path("json/all")
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Forecast>>(){});
        return forecasts;
    }

    public List<Forecast> jsonRainy(){
        List<Forecast> forecasts = baseTarget
                .path("json/rainy")
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Forecast>>(){});
        return forecasts;
    }

    public List<Forecast> jsonNonRainy(){
        List<Forecast> forecasts = baseTarget
                .path("json/nonrainy")
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Forecast>>(){});
        return forecasts;
    }

    public Forecast jsonHottest(){
        Forecast forecast = baseTarget
                .path("json/hottest")
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get(Forecast.class);
        return forecast;
    }


    public Forecast jsonByZipCode(String zipCode){
        Forecast forecast = baseTarget
                .path("json/byZipCode/"+zipCode)
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get(Forecast.class);
        return forecast;
    }

    public Forecast jsonByCity(String city){
        Forecast forecast = baseTarget
                .path("json/byCity/"+city)
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get(Forecast.class);
        return forecast;
    }

    public void jsonAdd(Forecast forecast)
    {
        baseTarget
                .path("json/post/add")
                .request()
                .post(Entity.json(forecast));
    }

    public void jsonAddAll(Forecast[] forecasts)
    {
        baseTarget
                .path("json/post/addAll")
                .request()
                .post(Entity.json(forecasts));

    }
    public void main(String[] args) {
//        WeatherApiClient client = new WeatherApiClient();
//        System.out.println(client.jsonRainy());
//        System.out.println(client.jsonNonRainy());
//        System.out.println(client.jsonHottest());
    }
}
