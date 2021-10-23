package org.javaweather.controller;

import org.javaweather.WeatherManager;
import org.javaweather.model.WeatherInformation;
import org.javaweather.view.ViewFactory;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.is;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MainWindowControllerTest {

    @Test
    void mainWindowControllerShouldAssignHomeAndVacationWeatherInformation() {
        //given
        WeatherManager weatherManager = mock(WeatherManager.class);
        WeatherInformation homeWeatherInformation = new WeatherInformation();
        WeatherInformation vacationWeatherInformation = new WeatherInformation();
        //when
        when(weatherManager.getHomeWeather()).thenReturn(homeWeatherInformation);
        when(weatherManager.getVacationWeather()).thenReturn(vacationWeatherInformation);
        MainWindowController mainWindowController = createController(weatherManager);
        //then
        assertThat(mainWindowController.getHomeWeatherInformation(), is(homeWeatherInformation));
        assertThat(mainWindowController.getVacationWeatherInformation(), is(vacationWeatherInformation));
    }

    private MainWindowController createController(WeatherManager weatherManager){
        ViewFactory viewFactory = new ViewFactory(weatherManager);
        String fxmlName = "MainWindow.fxml";
        MainWindowController mainWindowController = new MainWindowController(weatherManager, viewFactory, fxmlName);
        return  mainWindowController;
    }

}