package org.javaweather.model;

import org.javaweather.controller.services.FetchWeatherService;
import org.json.JSONArray;
import org.json.JSONObject;


public class WeatherInformation {
    private FetchWeatherService fetchWeatherService = null;
    private JSONObject jsonObjectWithWeather = null;
    private String city;
    private JSONObject firstDayForecast;
    private JSONObject secondDayForecast;
    private JSONObject thirdDayForecast;
    private JSONObject fourthDayForecast;
    private JSONObject fifthDayForecast;

    public JSONObject getFirstDayForecast() {
        return firstDayForecast;
    }

    final int INDEX_OF_FIRST_DAY_DATA_9AM = 0;
    final int INDEX_OF_SECOND_DAY_DATA_9AM = 8;
    final int INDEX_OF_THIRD_DAY_DATA_9AM = 16;
    final int INDEX_OF_FOURTH_DAY_DATA_9AM = 24;
    final int INDEX_OF_FIFTH_DAY_DATA_9AM = 32;

    public WeatherInformation(String city){
        this.city = city;
        this.fetchWeatherService = new FetchWeatherService(city);
        this.jsonObjectWithWeather = fetchWeatherService.getJsonWithWeatherData();
    }

    public void setWeatherDataBasedOnFetchService(){
        JSONArray inputArray = (JSONArray) this.jsonObjectWithWeather.opt("list");
        this.firstDayForecast = inputArray.getJSONObject(INDEX_OF_FIRST_DAY_DATA_9AM);
        this.secondDayForecast = inputArray.getJSONObject(INDEX_OF_SECOND_DAY_DATA_9AM);
        this.thirdDayForecast = inputArray.getJSONObject(INDEX_OF_THIRD_DAY_DATA_9AM);
        this.fourthDayForecast =inputArray.getJSONObject(INDEX_OF_FOURTH_DAY_DATA_9AM);
        this.fifthDayForecast = inputArray.getJSONObject(INDEX_OF_FIFTH_DAY_DATA_9AM);
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

    public Double getTemperature(JSONObject jsonObjectWithSingleDayData){
        return jsonObjectWithSingleDayData.getJSONObject("main").getDouble("temp");
    }

    public Double getMinTemperature(JSONObject jsonObjectWithSingleDayData){
        return jsonObjectWithSingleDayData.getJSONObject("main").getDouble("temp_min");
    }

    public Double getMaxTemperature(JSONObject jsonObjectWithSingleDayData){
        return jsonObjectWithSingleDayData.getJSONObject("main").getDouble("temp_max");
    }

    public Double getFeelsLikeTemperature(JSONObject jsonObjectWithSingleDayData){
        return jsonObjectWithSingleDayData.getJSONObject("main").getDouble("feels_like");
    }

    public Integer getHumidity(JSONObject jsonObjectWithSingleDayData){
        return jsonObjectWithSingleDayData.getJSONObject("main").getInt("humidity");
    }

    public Integer getPressure(JSONObject jsonObjectWithSingleDayData){
        return jsonObjectWithSingleDayData.getJSONObject("main").getInt("pressure");
    }

    public Integer getCloudiness(JSONObject jsonObjectWithSingleDayData){
        return jsonObjectWithSingleDayData.getJSONObject("clouds").getInt("all");
    }

    public Double getWindSpeed(JSONObject jsonObjectWithSingleDayData){
        return jsonObjectWithSingleDayData.getJSONObject("wind").getDouble("speed");
    }

    private JSONObject convertOneElementJsonArrayToJsonObject(JSONArray oneElementJsonArray){
        return oneElementJsonArray.getJSONObject(0);
    }
}
