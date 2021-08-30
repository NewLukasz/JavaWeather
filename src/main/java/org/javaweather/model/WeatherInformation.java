package org.javaweather.model;

import org.javaweather.controller.services.FetchWeatherService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class WeatherInformation {
    private FetchWeatherService fetchWeatherService = null;
    private JSONObject jsonObjectWithWeather = null;
    private String city = null;
    private ArrayList<JSONObject> listOfDaysData;
    private Boolean changeCityStatus=true;

    final int INDEX_OF_FIRST_DAY_DATA = 0;
    final int INDEX_OF_SECOND_DAY_DATA = 8;
    final int INDEX_OF_THIRD_DAY_DATA = 16;
    final int INDEX_OF_FOURTH_DAY_DATA = 24;
    final int INDEX_OF_FIFTH_DAY_DATA = 32;

    final int INDEX_OF_LAST_NECESSARY_DATE_SIGN=10;

    public WeatherInformation(String city){
        this.city = city;
        this.fetchWeatherService = new FetchWeatherService(this.city);
        this.jsonObjectWithWeather = fetchWeatherService.getJsonWithWeatherData();
    }

    public JSONObject getOneDayFromForecast(Integer indexOfTheDay){
        return this.listOfDaysData.get(indexOfTheDay);
    }

    public String getCity() {
        if(city.contains("+")){
            return changePlusesToSpaces(city);
        }else{
            return city;
        }
    }

    public void setCityAndReloadData(String city){
        if(city.contains(" ")){
            city = changeSpaceToPlus(city);
        }
        this.city=city;
        this.fetchWeatherService = new FetchWeatherService(this.city);
        this.jsonObjectWithWeather = fetchWeatherService.getJsonWithWeatherData();
        if(this.jsonObjectWithWeather!=null){
            this.changeCityStatus=true;
        }
        if(this.jsonObjectWithWeather==null){
            this.changeCityStatus=false;
            return;
        }
        setWeatherDataBasedOnFetchService();
    }

    public Boolean getChangeCityStatus(){
        return this.changeCityStatus;
    }

    private String changeSpaceToPlus(String stringWithSpaces){
        String stringWithPluses = stringWithSpaces.replace(" ","+");
        return stringWithPluses;
    }

    private String changePlusesToSpaces(String stringWithPluses){
        String stringWithSpaces = stringWithPluses.replace("+"," ");
        return stringWithSpaces;
    }

    public void setWeatherDataBasedOnFetchService(){
        JSONArray inputArray = (JSONArray) this.jsonObjectWithWeather.opt("list");
        JSONObject firstDayForecast = inputArray.getJSONObject(INDEX_OF_FIRST_DAY_DATA);
        JSONObject secondDayForecast = inputArray.getJSONObject(INDEX_OF_SECOND_DAY_DATA);
        JSONObject thirdDayForecast = inputArray.getJSONObject(INDEX_OF_THIRD_DAY_DATA);
        JSONObject fourthDayForecast =inputArray.getJSONObject(INDEX_OF_FOURTH_DAY_DATA);
        JSONObject fifthDayForecast = inputArray.getJSONObject(INDEX_OF_FIFTH_DAY_DATA);
        listOfDaysData = new ArrayList<JSONObject>();
        this.listOfDaysData.add(firstDayForecast);
        this.listOfDaysData.add(secondDayForecast);
        this.listOfDaysData.add(thirdDayForecast);
        this.listOfDaysData.add(fourthDayForecast);
        this.listOfDaysData.add(fifthDayForecast);
    }

    public String getCountry(){
        return jsonObjectWithWeather.getJSONObject("city").getString("country");
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

    public String getFeelsLikeTemperature(JSONObject jsonObjectWithSingleDayData){
        return convertTemperatureToStringAndAddCelsiusUnit(jsonObjectWithSingleDayData.getJSONObject("main").getDouble("feels_like"));
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
