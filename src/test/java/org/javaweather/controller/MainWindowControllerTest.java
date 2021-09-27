package org.javaweather.controller;

import org.javaweather.WeatherManager;
import org.javaweather.model.WeatherInformation;
import org.javaweather.view.ViewFactory;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MainWindowControllerTest {

    @Test
    void mainWindowControllerShouldAssignHomeAndVacationWeatherInformation(){
        WeatherManager weatherManager = mock(WeatherManager.class);
        ViewFactory viewFactory = new ViewFactory(weatherManager);
        String fxmlName="MainWindow.fxml";

        WeatherInformation homeWeatherInformation = new WeatherInformation();
        WeatherInformation vacationWeatherInformation = new WeatherInformation();

        when(weatherManager.getHomeWeather()).thenReturn(homeWeatherInformation);
        when(weatherManager.getVacationWeather()).thenReturn(vacationWeatherInformation);

        MainWindowController mainWindowController = new MainWindowController(weatherManager,viewFactory, fxmlName);

        assertThat(mainWindowController.getHomeWeatherInformation().getClass(),equalTo(WeatherInformation.class));
        assertThat(mainWindowController.getVacationWeatherInformation().getClass(),equalTo(WeatherInformation.class));
    }

}