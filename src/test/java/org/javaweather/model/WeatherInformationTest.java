package org.javaweather.model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.javaweather.AdditionalFunctionsForTesting.getJsonObjectFromFile;


class WeatherInformationTest {

    private static WeatherInformation weatherInformation;

    @BeforeEach
    void setup() {
        //given
        weatherInformation = new WeatherInformation();
        JSONObject jsonObjectWithWeatherDataFromFile = getJsonObjectFromFile("FoundCityExampleResponseFromApi.json");
        weatherInformation.setJsonObjectWithWeatherBasedOnFetchWeatherService(jsonObjectWithWeatherDataFromFile);
        weatherInformation.setWeatherDataBasedOnFetchService();
    }

    @Test
    void shouldSetListOfFiveDaysDataBasedOnJsonObjectFromFetchWeatherService() {
        //when
        weatherInformation.setWeatherDataBasedOnFetchService();
        //then
        assertThat(weatherInformation.getListOfDaysData(), hasSize(5));
    }

    @Test
    void shouldReturnOneDayOfForecastAsJsonObject() {
        //when
        weatherInformation.setWeatherDataBasedOnFetchService();
        //then
        assertThat(weatherInformation.getOneDayFromForecast(2).getClass(), is(JSONObject.class));
    }

    @Test
    void shouldReturnCountry() {
        //given is in beforeAll section
        //then
        assertThat(weatherInformation.getCountry(), equalTo("PL"));
    }

    @Test
    void shouldReturnDateOfWeather() {
        //when
        JSONObject jsonObjectWithSingleDayData = weatherInformation.getOneDayFromForecast(2);
        //then
        assertThat(weatherInformation.getDateOfWeather(jsonObjectWithSingleDayData), equalTo("2021-09-26"));
    }

    @Test
    void shouldReturnWeatherIconCode() {
        //when
        JSONObject jsonObjectWithSingleDayData = weatherInformation.getOneDayFromForecast(2);
        //then
        assertThat(weatherInformation.getWeatherIconCode(jsonObjectWithSingleDayData), equalTo("04d"));
    }

    @Test
    void shouldReturnWeatherDescription() {
        //when
        JSONObject jsonObjectWithSingleDayData = weatherInformation.getOneDayFromForecast(2);
        //then
        assertThat(weatherInformation.getWeatherMainDescription(jsonObjectWithSingleDayData), equalTo("Clouds"));
    }

    @Test
    void shouldReturnTemperature() {
        //when
        JSONObject jsonObjectWithSingleDayData = weatherInformation.getOneDayFromForecast(2);
        //then
        assertThat(weatherInformation.getTemperature(jsonObjectWithSingleDayData), equalTo("17°C"));
    }

    @Test
    void shouldReturnFeelsLikeTemperature() {
        //when
        JSONObject jsonObjectWithSingleDayData = weatherInformation.getOneDayFromForecast(2);
        //then
        assertThat(weatherInformation.getFeelsLikeTemperature(jsonObjectWithSingleDayData), equalTo("17°C"));
    }

    @Test
    void shouldReturnPressure() {
        //when
        JSONObject jsonObjectWithSingleDayData = weatherInformation.getOneDayFromForecast(2);
        //then
        assertThat(weatherInformation.getPressure(jsonObjectWithSingleDayData), equalTo("1022hPA"));
    }

    @Test
    void shouldReturnCloudiness() {
        //when
        JSONObject jsonObjectWithSingleDayData = weatherInformation.getOneDayFromForecast(2);
        //then
        assertThat(weatherInformation.getCloudiness(jsonObjectWithSingleDayData), equalTo("82%"));
    }

    @Test
    void shouldReturnWindSpeed() {
        //when
        JSONObject jsonObjectWithSingleDayData = weatherInformation.getOneDayFromForecast(2);
        //then
        assertThat(weatherInformation.getWindSpeed(jsonObjectWithSingleDayData), equalTo("2m/s"));
    }

    @Test
    void shouldConvertTemperatureToStringAndAddCelsiusUnit() {
        //given
        double temperature = 30.5;
        //when
        String temperatureString = weatherInformation.convertTemperatureToStringAndAddCelsiusUnit(temperature);
        //then
        assertThat(temperatureString.getClass(), is(String.class));
        assertThat(temperatureString, containsString("\u00B0C"));
    }

}