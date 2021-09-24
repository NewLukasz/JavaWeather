package org.javaweather.controller.services;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

class FetchWeatherServiceTest {
    @Test
    void makeApiQueryAndSetApiResponseShouldSetApiResponse(){
        //given
        FetchWeatherService fetchWeatherService = new FetchWeatherService();
        fetchWeatherService.setCity("Ankara");

        JSONObject jsonObject = getPreparedJsonObjectWithWeatherData();

        Api api = mock(Api.class);

        //System.out.println(jsonObject);
        given(api.makeApiQueryAndSetApiResponse()).willReturn(jsonObject);

        fetchWeatherService.fetchDataFromServerProcedure();
        System.out.println(fetchWeatherService.getApiResponse());
    }

    public JSONObject getPreparedJsonObjectWithWeatherData() {
        try {
            String inputStringFromResources = new String(getClass().getClassLoader().getResourceAsStream("ExampleResponseFromApi").readAllBytes());
            return new JSONObject(inputStringFromResources);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }
}