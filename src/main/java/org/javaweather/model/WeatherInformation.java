package org.javaweather.model;

import org.javaweather.controller.services.FetchWeatherService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class WeatherInformation {
    private FetchWeatherService fetchWeatherService = null;
    private JSONObject jsonObjectWithWeather = null;
    private String city = "Warszawa";
    private JSONObject firstDayForecast;
    private JSONObject secondDayForecast;
    private JSONObject thirdDayForecast;
    private JSONObject fourthDayForecast;
    private JSONObject fifthDayForecast;
    private ArrayList<JSONObject> listOfDaysData;

    final int INDEX_OF_FIRST_DAY_DATA_9AM = 0;
    final int INDEX_OF_SECOND_DAY_DATA_9AM = 8;
    final int INDEX_OF_THIRD_DAY_DATA_9AM = 16;
    final int INDEX_OF_FOURTH_DAY_DATA_9AM = 24;
    final int INDEX_OF_FIFTH_DAY_DATA_9AM = 32;

    final int INDEX_OF_LAST_NECESSARY_DATE_SIGN=10;

    public WeatherInformation(){
        this.fetchWeatherService = new FetchWeatherService(this.city);
        this.jsonObjectWithWeather = fetchWeatherService.getJsonWithWeatherData();
    }

    public JSONObject getFirstDayForecast() {
        return firstDayForecast;
    }

    public JSONObject getOneDayFromForecast(Integer indexOfTheDay){
        return this.listOfDaysData.get(indexOfTheDay);
    }

    public String getCity() {
        return city;
    }

    public void setCityAndReloadData(String city){
        this.city=city;
        this.fetchWeatherService = new FetchWeatherService(this.city);
        this.jsonObjectWithWeather = fetchWeatherService.getJsonWithWeatherData();
        setWeatherDataBasedOnFetchService();
    }

    public void setWeatherDataBasedOnFetchService(){
        JSONArray inputArray = (JSONArray) this.jsonObjectWithWeather.opt("list");
        this.firstDayForecast = inputArray.getJSONObject(INDEX_OF_FIRST_DAY_DATA_9AM);
        this.secondDayForecast = inputArray.getJSONObject(INDEX_OF_SECOND_DAY_DATA_9AM);
        this.thirdDayForecast = inputArray.getJSONObject(INDEX_OF_THIRD_DAY_DATA_9AM);
        this.fourthDayForecast =inputArray.getJSONObject(INDEX_OF_FOURTH_DAY_DATA_9AM);
        this.fifthDayForecast = inputArray.getJSONObject(INDEX_OF_FIFTH_DAY_DATA_9AM);
        listOfDaysData = new ArrayList<JSONObject>();
        this.listOfDaysData.add(this.firstDayForecast);
        this.listOfDaysData.add(this.secondDayForecast);
        this.listOfDaysData.add(this.thirdDayForecast);
        this.listOfDaysData.add(this.fourthDayForecast);
        this.listOfDaysData.add(this.fifthDayForecast);
    }

    public String getDateOfWeather(JSONObject jsonObjectWithSingleDayData){
        return jsonObjectWithSingleDayData.getString("dt_txt").substring(0,INDEX_OF_LAST_NECESSARY_DATE_SIGN);
    }

    public String getWeatherIconCode(JSONObject jsonObjectWithSingleDayData){
       return convertOneElementJsonArrayToJsonObject(jsonObjectWithSingleDayData.getJSONArray("weather")).getString("icon");
    }

    public String getWeatherLongDescription(JSONObject jsonObjectWithSingleDayData){
        return convertOneElementJsonArrayToJsonObject(jsonObjectWithSingleDayData.getJSONArray("weather")).getString("description");
    }

    public String getWeatherMainDescription(JSONObject jsonObjectWithSingleDayData){
        return convertOneElementJsonArrayToJsonObject(jsonObjectWithSingleDayData.getJSONArray("weather")).getString("main");
    }

    public String getTemperature(JSONObject jsonObjectWithSingleDayData){
        return convertTemperatureToStringAndAddCelsiusUnit(jsonObjectWithSingleDayData.getJSONObject("main").getDouble("temp"));
    }

    public String getMinTemperature(JSONObject jsonObjectWithSingleDayData){
        return convertTemperatureToStringAndAddCelsiusUnit(jsonObjectWithSingleDayData.getJSONObject("main").getDouble("temp_min"));
    }

    public String getMaxTemperature(JSONObject jsonObjectWithSingleDayData){
        return convertTemperatureToStringAndAddCelsiusUnit(jsonObjectWithSingleDayData.getJSONObject("main").getDouble("temp_max"));
    }

    public Double getFeelsLikeTemperature(JSONObject jsonObjectWithSingleDayData){
        return jsonObjectWithSingleDayData.getJSONObject("main").getDouble("feels_like");
    }

    public String getHumidity(JSONObject jsonObjectWithSingleDayData){
        return String.valueOf(jsonObjectWithSingleDayData.getJSONObject("main").getInt("humidity")) + "%";
    }

    public String getPressure(JSONObject jsonObjectWithSingleDayData){
        return String.valueOf(jsonObjectWithSingleDayData.getJSONObject("main").getInt("pressure"))+"hPA";
    }

    public String getCloudiness(JSONObject jsonObjectWithSingleDayData){
        return String.valueOf(jsonObjectWithSingleDayData.getJSONObject("clouds").getInt("all"))+ "%";
    }

    public String getWindSpeed(JSONObject jsonObjectWithSingleDayData){
        return String.valueOf((int)jsonObjectWithSingleDayData.getJSONObject("wind").getDouble("speed"))+"m/s";
    }

    private JSONObject convertOneElementJsonArrayToJsonObject(JSONArray oneElementJsonArray){
        return oneElementJsonArray.getJSONObject(0);
    }

    public String convertTemperatureToStringAndAddCelsiusUnit(double temperature){
        return String.valueOf((int)temperature) + "\u00B0C";
    }
}
