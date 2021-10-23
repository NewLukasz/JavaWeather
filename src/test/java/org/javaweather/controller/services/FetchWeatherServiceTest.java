package org.javaweather.controller.services;

import org.javaweather.AdditionalFunctionsForTesting;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.javaweather.AdditionalFunctionsForTesting.getJsonObjectFromFile;

class FetchWeatherServiceTest {

    private FetchWeatherService fetchWeatherService;

    @BeforeEach
    void setup() {
        fetchWeatherService = new FetchWeatherService();
    }

    @Test
    void shouldFindCityInFoundCityApiResponse() {
        //given
        JSONObject jsonObjectWithResponseFromApi = getJsonObjectFromFile("FoundCityExampleResponseFromApi.json");
        //when
        fetchWeatherService.setApiResponse(jsonObjectWithResponseFromApi);
        MessageCode messageCodeFromApiResponse = fetchWeatherService.getMessageFromJsonApiResponse();
        // then
        assertThat(messageCodeFromApiResponse, equalTo(MessageCode.CITY_FOUND));
    }

    @Test
    void shouldNotFindCityInNotFoundCityApiResponse() {
        //given
        JSONObject jsonObjectWithResponseFromApi = getJsonObjectFromFile("NotFoundCityExampleResponseFromApi.json");
        //when
        fetchWeatherService.setApiResponse(jsonObjectWithResponseFromApi);
        MessageCode messageCodeFromApiResponse = fetchWeatherService.getMessageFromJsonApiResponse();
        // then
        assertThat(messageCodeFromApiResponse, equalTo(MessageCode.CITY_NOT_FOUND));
    }

    @Test
    void shouldReturnFalseApiResponseNotFindCity() {
        //given
        JSONObject jsonObjectWithResponseFromApi = getJsonObjectFromFile("NotFoundCityExampleResponseFromApi.json");;
        //when
        fetchWeatherService.setApiResponse(jsonObjectWithResponseFromApi);
        //then
        assertThat(fetchWeatherService.isCityFoundBaseApiResponse(), equalTo(false));
    }

    @Test
    void shouldReturnTrueApiResponseFindCity() {
        //given
        JSONObject jsonObjectWithResponseFromApi = getJsonObjectFromFile("FoundCityExampleResponseFromApi.json");
        //when
        fetchWeatherService.setApiResponse(jsonObjectWithResponseFromApi);
        //then
        assertThat(fetchWeatherService.isCityFoundBaseApiResponse(), equalTo(true));
    }

    @Test
    void shouldSetJsonObjectCityIsFound() {
        //given
        JSONObject jsonObjectWithResponseFromApi = getJsonObjectFromFile("FoundCityExampleResponseFromApi.json");
        //when
        fetchWeatherService.setApiResponse(jsonObjectWithResponseFromApi);
        fetchWeatherService.checkMessageFromApiAndAssignJsonObjectWithDataIfCityFound();
        String country = fetchWeatherService.getJsonWithWeatherData().getJSONObject("city").getString("country");
        //then
        assertThat(country, equalTo("PL"));

    }

    @Test
    void shouldNotSetJsonObjectCityIsNotFound() {
        //given
        JSONObject jsonObjectWithResponseFromApi = getJsonObjectFromFile("NotFoundCityExampleResponseFromApi.json");
        //when
        fetchWeatherService.setApiResponse(jsonObjectWithResponseFromApi);
        fetchWeatherService.checkMessageFromApiAndAssignJsonObjectWithDataIfCityFound();
        //then
        assertThat(fetchWeatherService.getJsonWithWeatherData(), is(nullValue()));
    }
}