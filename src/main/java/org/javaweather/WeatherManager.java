package org.javaweather;

import org.javaweather.controller.services.FetchWeatherService;
import org.javaweather.model.WeatherInformation;

public class WeatherManager {
    private WeatherInformation homeWeather = new WeatherInformation("Wenecja");

    public WeatherInformation getGomeWeather(){
        return homeWeather;
    }
}
