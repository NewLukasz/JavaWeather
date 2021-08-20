package org.javaweather;

import org.javaweather.controller.services.FetchWeatherService;

public class WeatherManager {
    public WeatherManager(){
        FetchWeatherService fetchWeatherService = new FetchWeatherService("Date temporary in string", "Lublin" );
    }
}
