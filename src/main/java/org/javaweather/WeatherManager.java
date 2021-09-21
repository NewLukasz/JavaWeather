package org.javaweather;

import org.javaweather.controller.Persistence;
import org.javaweather.model.WeatherInformation;

public class WeatherManager {
    private final Persistence persistence = new Persistence();
    private String homeCity = "Warszawa";
    private String vacationDestination = "Londyn";

    private WeatherInformation homeWeather;
    private WeatherInformation vacationWeather;

    public WeatherInformation getHomeWeather() {
        return homeWeather;
    }

    public  WeatherInformation getVacationWeather(){
        return vacationWeather;
    }

    public void saveCitiesPersistence(String homeCity, String vacationDestination){
        persistence.saveToPersistence(homeCity,vacationDestination);
    }

    public WeatherManager(){
        if(persistence.checkIfPersistenceIsAlreadyUsed()){
            homeCity = persistence.getHomeCity();
            vacationDestination = persistence.getVacationDestination();
        }
        homeWeather = new WeatherInformation(homeCity);
        vacationWeather = new WeatherInformation(vacationDestination);
    }

}
