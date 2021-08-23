package org.javaweather.model;

import org.javaweather.controller.services.FetchWeatherService;
import org.json.JSONObject;

public class WeatherInformation {
    private FetchWeatherService fetchWeatherService = null;
    private JSONObject jsonObjectWithWeather = null;

    public void showJsonObjectWithWeather(){
        System.out.println(this.jsonObjectWithWeather);
    }

    public WeatherInformation(String city){
        this.fetchWeatherService = new FetchWeatherService(city);
        this.jsonObjectWithWeather = fetchWeatherService.getJsonWithWeatherData();
    }
}
