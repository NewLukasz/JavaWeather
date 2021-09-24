package org.javaweather.controller.services;

import org.json.JSONObject;


public class FetchWeatherService {

    private JSONObject jsonWithWeatherData;
    private JSONObject apiResponse;
    private String city;
    private Api api;

    public JSONObject getJsonWithWeatherData() {
        return jsonWithWeatherData;
    }

    public JSONObject getApiResponse() {
        return apiResponse;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public FetchWeatherService() {

    }

    public void makeApiQueryAndSetApiResponse() {
        api = new Api();
        api.setCity(city);
        apiResponse = api.setApiResponseAfterQuery();
    }

    private MessageCode getMessageFromJsonApiResponse() {
        int codeOfMessage = apiResponse.getInt("cod");
        return MessageCode.fromCode(codeOfMessage);
    }

    public boolean isCityFoundBaseApiResponse() {
        if (getMessageFromJsonApiResponse() == MessageCode.CITY_FOUND) {
            return true;
        } else {
            return false;
        }
    }

    public void checkMessageFromApiAndAssignJsonObjectWithDataIfCityFound() {
        if (isCityFoundBaseApiResponse()) {
            jsonWithWeatherData = apiResponse;
        }
    }

    public void fetchDataFromServerProcedure(){
        makeApiQueryAndSetApiResponse();
        if(isCityFoundBaseApiResponse()){
            checkMessageFromApiAndAssignJsonObjectWithDataIfCityFound();
        }
    }
}
