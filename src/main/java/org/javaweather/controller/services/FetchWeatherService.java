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
    private String temporaryDateInString;

    public FetchWeatherService(String temporaryDateInString, String city){
        doHttpGetWithOpenWeather();
    }

    public static void doHttpGetWithOpenWeather(){

        String cityName ="Lublin";

        String url="https://api.openweathermap.org/data/2.5/forecast?q="+cityName+"&appid=842bc442024d008401d44dfcb60ce43a";
        //String url="https://api.openweathermap.org/data/2.5/forecast?id=756135&appid=842bc442024d008401d44dfcb60ce43a";


        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse resp = null;
        try {
            resp = client.execute(get);
            HttpEntity entity = resp.getEntity();
            //System.out.println(EntityUtils.toString(entity));
            String str = EntityUtils.toString(entity);
            JSONObject jsonObject = new JSONObject(str);
            System.out.println(jsonObject);
            resp.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
