package org.javaweather.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.javaweather.WeatherManager;
import org.javaweather.model.WeatherInformation;
import org.javaweather.view.ViewFactory;

public class MainWindowController extends BaseController{
    private WeatherManager weatherManager;
    private WeatherInformation homeWeatherInformation;

    @FXML
    private Label homeLocationWeather;

    @FXML
    private Label homeCity;

    @FXML
    private Label homeCountry;

    @FXML
    private ImageView homeMainDayIcon;

    @FXML
    private Label homeMainDayTemperature;

    public MainWindowController(WeatherManager weatherManager, ViewFactory viewFactory,String fxmlName){
        super(weatherManager,viewFactory,fxmlName);
        this.homeWeatherInformation = weatherManager.getHomeWeather();
    }

    @FXML
    void TestButtonAction() {
        homeCity.setText("Przyklad - Lublin");
        homeCountry.setText("Przyklad - Polska");
        homeMainDayTemperature.setText("Przyklad - 20st");
        System.out.println("Test button action");
        homeWeatherInformation.setWeatherDataBasedOnFetchService();
        System.out.println(homeWeatherInformation.getWeatherIconCode(homeWeatherInformation.getFirstDayForecast()));
        System.out.println(homeWeatherInformation.getWeatherLongDescription(homeWeatherInformation.getFirstDayForecast()));
        System.out.println(homeWeatherInformation.getWeatherMainDescription(homeWeatherInformation.getFirstDayForecast()));
        System.out.println(homeWeatherInformation.getTemperature(homeWeatherInformation.getFirstDayForecast()));
        System.out.println(homeWeatherInformation.getMinTemperature(homeWeatherInformation.getFirstDayForecast()));
        System.out.println(homeWeatherInformation.getMaxTemperature(homeWeatherInformation.getFirstDayForecast()));
        System.out.println(homeWeatherInformation.getFeelsLikeTemperature(homeWeatherInformation.getFirstDayForecast()));
        System.out.println(homeWeatherInformation.getHumidity(homeWeatherInformation.getFirstDayForecast()));
        System.out.println(homeWeatherInformation.getPressure(homeWeatherInformation.getFirstDayForecast()));
        System.out.println(homeWeatherInformation.getCloudiness(homeWeatherInformation.getFirstDayForecast()));
        System.out.println(homeWeatherInformation.getWindSpeed(homeWeatherInformation.getFirstDayForecast()));
    }
}
