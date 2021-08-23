package org.javaweather;

import org.javaweather.model.WeatherInformation;

public class WeatherManager {

    private WeatherInformation homeWeather = new WeatherInformation();

    public WeatherInformation getHomeWeather() {
        return homeWeather;
    }
}
