package org.javaweather.controller.services;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;


import java.io.IOException;

public class FetchWeatherService {

    private JSONObject jsonWithWeatherData;

    public FetchWeatherService(String city) {
        checkIfCityIsFoundAndAssignIfYes(city);
    }

    public JSONObject getJsonWithWeatherData() {
        return jsonWithWeatherData;
    }

    private boolean checkIfCityIsFoundAndAssignIfYes(String city) {
        JSONObject jsonWithResponseData = getApiResponse(city);
        MessageCode messageFromApi = getMessageFromJsonApiResponse(jsonWithResponseData);
        if (messageFromApi == MessageCode.CITY_FOUND) {
            jsonWithWeatherData = jsonWithResponseData;
            return true;
        } else {
            return false;
        }
    }

    private MessageCode getMessageFromJsonApiResponse(JSONObject jsonObjectWithMessage) {
        int codeOfMessage = jsonObjectWithMessage.getInt("cod");
        return MessageCode.fromCode(codeOfMessage);
    }

    private JSONObject getApiResponse(String city) {
        String urlToApi = "https://api.openweathermap.org/data/2.5/forecast?q=" + city + "&units=metric&appid=" + ApiData.getApiKey();
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(urlToApi);
        CloseableHttpResponse response;
        try {
            response = client.execute(get);
            HttpEntity entity = response.getEntity();
            JSONObject jsonWithResponseData = new JSONObject(EntityUtils.toString(entity));
            response.close();
            return jsonWithResponseData;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
