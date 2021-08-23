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

    private String city;
    private JSONObject jsonWithWeatherData;
    private Boolean getWeatherDataStatus;

    public JSONObject getJsonWithWeatherData() {
        return jsonWithWeatherData;
    }

    public FetchWeatherService(String city){
        this.getWeatherDataStatus = false;
        checkIfCityIsFoundAndAssignIfYes(city);
    }

    private boolean checkIfCityIsFoundAndAssignIfYes(String city){
        JSONObject jsonWithResponseData = getApiResponse(city);
        String messageFromApi = getMessageFromJsonApiResponse(jsonWithResponseData);
        if(messageFromApi.equals("City found")){
            this.jsonWithWeatherData=jsonWithResponseData;
            return this.getWeatherDataStatus=true;
        }else{
            return this.getWeatherDataStatus=false;
        }
    }

    private String getMessageFromJsonApiResponse(JSONObject jsonObjectWithMessage){
        Integer codeOfMessage = jsonObjectWithMessage.getInt("cod");
        return MessageCodes.getMessageBasedCode(codeOfMessage);
    }

    private JSONObject getApiResponse(String city){
        String urlToApi= "https://api.openweathermap.org/data/2.5/forecast?q="+city+"&units=metric&appid="+ApiData.getApiKey();
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(urlToApi);
        CloseableHttpResponse response = null;
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
