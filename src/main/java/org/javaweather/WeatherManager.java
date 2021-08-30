package org.javaweather;

import org.javaweather.controller.Persistence;
import org.javaweather.model.WeatherInformation;

public class WeatherManager {
    Persistence persistence = new Persistence();
    private String homeCity = "Warszawa";
    private String vacationDestination = "Londyn";

    private WeatherInformation homeWeather = null;
    private WeatherInformation vacationWeather = null;

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
            this.homeCity = persistence.getHomeCity();
            this.vacationDestination = persistence.getVacationDestination();
        }
        homeWeather = new WeatherInformation(this.homeCity);
        vacationWeather = new WeatherInformation(this.vacationDestination);
    }
}
