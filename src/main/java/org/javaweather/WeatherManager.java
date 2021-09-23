package org.javaweather;

import org.javaweather.controller.Persistence;
import org.javaweather.model.WeatherInformation;

public class WeatherManager {
    private final Persistence persistence = new Persistence();
    private String homeCity;
    private String vacationDestination;

    private WeatherInformation homeWeather = new WeatherInformation();
    private WeatherInformation vacationWeather = new WeatherInformation();

    public WeatherManager() {
    }

    public void setDefaultCitiesAndLoadFromAPI() {
        homeCity = "Warszawa";
        vacationDestination = "Londyn";
        homeWeather.setCityAndLoadDataFromAPI(homeCity);
        vacationWeather.setCityAndLoadDataFromAPI(vacationDestination);
    }

    public WeatherInformation getHomeWeather() {
        return homeWeather;
    }

    public WeatherInformation getVacationWeather() {
        return vacationWeather;
    }

    public void saveCitiesPersistence(String homeCity, String vacationDestination) {
        persistence.saveToPersistence(homeCity, vacationDestination);
    }

    public boolean checkPersistenceAndLoadFromAPIIfIsInUse() {
        if (persistence.checkPersistenceAndLoadIfIsInUse()) {
            homeWeather.setCityAndLoadDataFromAPI(persistence.getHomeCity());
            vacationWeather.setCityAndLoadDataFromAPI(persistence.getVacationDestination());
            return true;
        }
        return false;
    }
}
