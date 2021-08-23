package org.javaweather;

import org.javaweather.model.WeatherInformation;

public class WeatherManager {

    private String homeCity="Wenecja";

    private WeatherInformation homeWeather = new WeatherInformation(this.homeCity);

    public WeatherInformation getHomeWeather() {
        return homeWeather;
    }
}
