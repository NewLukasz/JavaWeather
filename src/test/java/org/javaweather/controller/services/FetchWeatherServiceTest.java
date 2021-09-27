package org.javaweather.controller.services;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class FetchWeatherServiceTest {

    @Test
    void shouldFindCityInFoundCityApiResponse() {
        //given
        FetchWeatherService fetchWeatherService = new FetchWeatherService();
        Api api = mock(Api.class);
        given(api.getApiResponse()).willReturn(getJsonObjectFromFile("FoundCityExampleResponseFromApi"));
        //when
        fetchWeatherService.setApiResponse(api.getApiResponse());
        MessageCode messageCodeFromApiResponse = fetchWeatherService.getMessageFromJsonApiResponse();
        // then
        assertThat(messageCodeFromApiResponse,equalTo(MessageCode.CITY_FOUND));
    }

    @Test
    void shouldNotFindCityInNotFoundCityApiResponse(){
        //given
        FetchWeatherService fetchWeatherService = new FetchWeatherService();
        Api api = mock(Api.class);
        given(api.getApiResponse()).willReturn(getJsonObjectFromFile("NotFoundCityExampleResponseFromApi"));
        //when
        fetchWeatherService.setApiResponse(api.getApiResponse());
        MessageCode messageCodeFromApiResponse = fetchWeatherService.getMessageFromJsonApiResponse();
        // then
        assertThat(messageCodeFromApiResponse,equalTo(MessageCode.CITY_NOT_FOUND));
    }

    private JSONObject getJsonObjectFromFile(String fileName){
        try {
            String inputStringFromResources = new String(getClass().getClassLoader().getResourceAsStream(fileName).readAllBytes());
            return new JSONObject(inputStringFromResources);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

}