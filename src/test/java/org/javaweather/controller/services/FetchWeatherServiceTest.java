package org.javaweather.controller.services;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class FetchWeatherServiceTest {

    private static FetchWeatherService fetchWeatherService;
    private static Api api;

    @BeforeAll
    static void setup() {
        fetchWeatherService = new FetchWeatherService();
        api = mock(Api.class);
    }

    @Test
    void shouldFindCityInFoundCityApiResponse() {
        //given
        given(api.getApiResponse()).willReturn(getJsonObjectFromFile("FoundCityExampleResponseFromApi"));
        //when
        fetchWeatherService.setApiResponse(api.getApiResponse());
        MessageCode messageCodeFromApiResponse = fetchWeatherService.getMessageFromJsonApiResponse();
        // then
        assertThat(messageCodeFromApiResponse, equalTo(MessageCode.CITY_FOUND));
    }

    @Test
    void shouldNotFindCityInNotFoundCityApiResponse() {
        //given
        given(api.getApiResponse()).willReturn(getJsonObjectFromFile("NotFoundCityExampleResponseFromApi"));
        //when
        fetchWeatherService.setApiResponse(api.getApiResponse());
        MessageCode messageCodeFromApiResponse = fetchWeatherService.getMessageFromJsonApiResponse();
        // then
        assertThat(messageCodeFromApiResponse, equalTo(MessageCode.CITY_NOT_FOUND));
    }

    @Test
    void shouldReturnFalseApiResponseNotFindCity() {
        //given
        given(api.getApiResponse()).willReturn(getJsonObjectFromFile("NotFoundCityExampleResponseFromApi"));
        //when
        fetchWeatherService.setApiResponse(api.getApiResponse());
        //then
        assertThat(fetchWeatherService.isCityFoundBaseApiResponse(), equalTo(false));
    }

    @Test
    void shouldReturnTrueApiResponseFindCity() {
        //given
        given(api.getApiResponse()).willReturn(getJsonObjectFromFile("FoundCityExampleResponseFromApi"));
        //when
        fetchWeatherService.setApiResponse(api.getApiResponse());
        //then
        assertThat(fetchWeatherService.isCityFoundBaseApiResponse(), equalTo(true));
    }

    @Test
    void shouldSetJsonObjectCityIsFound(){
        //given
        given(api.getApiResponse()).willReturn(getJsonObjectFromFile("FoundCityExampleResponseFromApi"));
        //when
        fetchWeatherService.setApiResponse(api.getApiResponse());
        fetchWeatherService.checkMessageFromApiAndAssignJsonObjectWithDataIfCityFound();
        //then
        assertThat(fetchWeatherService.getJsonWithWeatherData(), is(notNullValue()));
    }

    @Test
    void shouldNotSetJsonObjectCityIsNotFound(){
        //given
        given(api.getApiResponse()).willReturn(getJsonObjectFromFile("NotFoundCityExampleResponseFromApi"));
        //when
        fetchWeatherService.setApiResponse(api.getApiResponse());
        fetchWeatherService.checkMessageFromApiAndAssignJsonObjectWithDataIfCityFound();
        //then
        assertThat(fetchWeatherService.getJsonWithWeatherData(), is(nullValue()));
    }

    private JSONObject getJsonObjectFromFile(String fileName) {
        try {
            String inputStringFromResources = new String(getClass().getClassLoader().getResourceAsStream(fileName).readAllBytes());
            return new JSONObject(inputStringFromResources);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

}