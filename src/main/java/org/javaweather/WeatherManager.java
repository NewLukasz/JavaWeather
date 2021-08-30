package org.javaweather;

import org.javaweather.model.WeatherInformation;

public class WeatherManager {

    private WeatherInformation homeWeather = new WeatherInformation("Warszawa");
    private WeatherInformation vacationWeather = new WeatherInformation("Rzym");

    public WeatherInformation getHomeWeather() {
        return homeWeather;
    }

    public  WeatherInformation getVacationWeather(){
        return vacationWeather;
    }
}
