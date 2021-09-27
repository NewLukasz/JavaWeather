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

    public void setCity(String city) {
        this.city = city;
    }

    public FetchWeatherService() {
        api = new Api();
    }

    public void makeApiQueryAndSetApiResponse() {
        api.setCity(city);
        api.makeApiQuery();
        setApiResponse(api.getApiResponse());
    }

    public void setApiResponse(JSONObject apiResponse) {
        this.apiResponse = apiResponse;
    }

    public MessageCode getMessageFromJsonApiResponse() {
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

    public void fetchDataFromServerProcedure() {
        makeApiQueryAndSetApiResponse();
        if (isCityFoundBaseApiResponse()) {
            checkMessageFromApiAndAssignJsonObjectWithDataIfCityFound();
        }
    }
}
