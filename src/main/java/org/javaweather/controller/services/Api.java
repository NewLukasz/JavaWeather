package org.javaweather.controller.services;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;

import static org.javaweather.model.AdditionalFunctions.changeSpaceToPlus;

public class Api {
    private String city;
    private JSONObject apiResponse;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public JSONObject getApiResponse() {
        return apiResponse;
    }

    public void setApiResponse(JSONObject apiResponse) {
        this.apiResponse = apiResponse;
    }

    public void setApiResponseBasedOnApiQueryResult(JSONObject jsonWithResponseData ){
        apiResponse = jsonWithResponseData;
    }

    private String getApiKey() {
        return "842bc442024d008401d44dfcb60ce43a";
    }



    public JSONObject makeApiQueryAndSetApiResponse() {
        String cityWithPlus = changeSpaceToPlus(city);
        String urlToApi = "https://api.openweathermap.org/data/2.5/forecast?q=" + cityWithPlus + "&units=metric&appid=" + getApiKey();
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(urlToApi);
        CloseableHttpResponse response;
        try {
            response = client.execute(get);
            HttpEntity entity = response.getEntity();
            JSONObject jsonWithResponseData = new JSONObject(EntityUtils.toString(entity));
            response.close();
            setApiResponseBasedOnApiQueryResult(jsonWithResponseData);
            return jsonWithResponseData;
        } catch (IOException e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

    public JSONObject setApiResponseAfterQuery(){
        return apiResponse= makeApiQueryAndSetApiResponse();
    }

}
