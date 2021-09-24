package org.javaweather.model;

import org.javaweather.WeatherManager;
import org.javaweather.controller.services.FetchWeatherService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class WeatherInformationTest {

    /*
    if (city.contains(" ")) {
            city = changeSpaceToPlus(city);
        }
        this.city = city;
        fetchWeatherService = new FetchWeatherService(this.city);
        jsonObjectWithWeather = fetchWeatherService.getJsonWithWeatherData();
        if (jsonObjectWithWeather != null) {
            changeCityStatus = true;
            setWeatherDataBasedOnFetchService();
        } else {
            changeCityStatus = false;
        }
     */
    @Test
    void setCityAndLoadDataFromAPIShouldSetListOfDaysData(){
        String city="Lublin";

    }

    public JSONObject getPreparedJsonObjectWithWeatherData() {
        try {
            String inputStringFromResources = new String(getClass().getClassLoader().getResourceAsStream("ExampleResponseFromApi").readAllBytes());
            return new JSONObject(inputStringFromResources);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

}