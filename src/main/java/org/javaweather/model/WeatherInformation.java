package org.javaweather.model;

import org.javaweather.controller.services.FetchWeatherService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static org.javaweather.model.AdditionalFunctions.*;

public class WeatherInformation {
    private FetchWeatherService fetchWeatherService;
    private JSONObject jsonObjectWithWeather;
    private String city;
    private List<JSONObject> listOfDaysData;
    private Boolean changeCityStatus = true;

    final private static int INDEX_OF_FIRST_DAY_DATA = 0;
    final private static int INDEX_OF_SECOND_DAY_DATA = 8;
    final private static int INDEX_OF_THIRD_DAY_DATA = 16;
    final private static int INDEX_OF_FOURTH_DAY_DATA = 24;
    final private static int INDEX_OF_FIFTH_DAY_DATA = 32;

    final private static int INDEX_OF_LAST_NECESSARY_DATE_SIGN = 10;

    public WeatherInformation(String city) {
        this.city = city;
        fetchWeatherService = new FetchWeatherService(city);
        jsonObjectWithWeather = fetchWeatherService.getJsonWithWeatherData();
    }

    public JSONObject getOneDayFromForecast(Integer indexOfTheDay) {
        return this.listOfDaysData.get(indexOfTheDay);
    }

    public String getCity() {
        if (city.contains("+")) {
            return changePlusesToSpaces(city);
        } else {
            return city;
        }
    }

    public void setCityAndReloadData(String city) {
        if (city.contains(" ")) {
            city = changeSpaceToPlus(city);
        }
        this.city = city;
        fetchWeatherService = new FetchWeatherService(this.city);
        jsonObjectWithWeather = fetchWeatherService.getJsonWithWeatherData();
        if (jsonObjectWithWeather != null) {
            changeCityStatus = true;
        }
        if (this.jsonObjectWithWeather == null) {
            changeCityStatus = false;
            return;
        }
        setWeatherDataBasedOnFetchService();
    }

    public Boolean getChangeCityStatus() {
        return changeCityStatus;
    }

    public void setWeatherDataBasedOnFetchService() {
        JSONArray inputArray = (JSONArray) jsonObjectWithWeather.opt("list");
        JSONObject firstDayForecast = inputArray.getJSONObject(INDEX_OF_FIRST_DAY_DATA);
        JSONObject secondDayForecast = inputArray.getJSONObject(INDEX_OF_SECOND_DAY_DATA);
        JSONObject thirdDayForecast = inputArray.getJSONObject(INDEX_OF_THIRD_DAY_DATA);
        JSONObject fourthDayForecast = inputArray.getJSONObject(INDEX_OF_FOURTH_DAY_DATA);
        JSONObject fifthDayForecast = inputArray.getJSONObject(INDEX_OF_FIFTH_DAY_DATA);
        listOfDaysData = new ArrayList<JSONObject>();
        listOfDaysData.add(firstDayForecast);
        listOfDaysData.add(secondDayForecast);
        listOfDaysData.add(thirdDayForecast);
        listOfDaysData.add(fourthDayForecast);
        listOfDaysData.add(fifthDayForecast);
    }

    public String getCountry() {
        return jsonObjectWithWeather.getJSONObject("city").getString("country");
    }

    public String getDateOfWeather(JSONObject jsonObjectWithSingleDayData) {
        return jsonObjectWithSingleDayData.getString("dt_txt").substring(0, INDEX_OF_LAST_NECESSARY_DATE_SIGN);
    }

    public String getWeatherIconCode(JSONObject jsonObjectWithSingleDayData) {
        return convertOneElementJsonArrayToJsonObject(jsonObjectWithSingleDayData.getJSONArray("weather")).getString("icon");
    }

    public String getWeatherMainDescription(JSONObject jsonObjectWithSingleDayData) {
        return convertOneElementJsonArrayToJsonObject(jsonObjectWithSingleDayData.getJSONArray("weather")).getString("main");
    }

    public String getTemperature(JSONObject jsonObjectWithSingleDayData) {
        return convertTemperatureToStringAndAddCelsiusUnit(jsonObjectWithSingleDayData.getJSONObject("main").getDouble("temp"));
    }

    public String getFeelsLikeTemperature(JSONObject jsonObjectWithSingleDayData) {
        return convertTemperatureToStringAndAddCelsiusUnit(jsonObjectWithSingleDayData.getJSONObject("main").getDouble("feels_like"));
    }

    public String getPressure(JSONObject jsonObjectWithSingleDayData) {
        return jsonObjectWithSingleDayData.getJSONObject("main").getInt("pressure") + "hPA";
    }

    public String getCloudiness(JSONObject jsonObjectWithSingleDayData) {
        return jsonObjectWithSingleDayData.getJSONObject("clouds").getInt("all") + "%";
    }

    public String getWindSpeed(JSONObject jsonObjectWithSingleDayData) {
        return (int) jsonObjectWithSingleDayData.getJSONObject("wind").getDouble("speed") + "m/s";
    }

    public String convertTemperatureToStringAndAddCelsiusUnit(double temperature) {
        return (int) temperature + "\u00B0C";
    }
}
