package org.javaweather.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.javaweather.WeatherManager;
import org.javaweather.model.WeatherInformation;
import org.javaweather.view.ViewFactory;

public class MainWindowController extends BaseController{


    private WeatherInformation weatherInformation = new WeatherInformation("Wenecja");

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
    }

    @FXML
    void TestButtonAction() {
        homeCity.setText("Przyklad - Lublin");
        homeCountry.setText("Przyklad - Polska");
        homeMainDayTemperature.setText("Przyklad - 20st");
        System.out.println("Test button action");
        weatherInformation.setWeatherDataBasedOnFetchService();
        System.out.println(weatherInformation.getWeatherIconCode(weatherInformation.getFirstDayForecast()));
        System.out.println(weatherInformation.getWeatherLongDescription(weatherInformation.getFirstDayForecast()));
        System.out.println(weatherInformation.getWeatherMainDescription(weatherInformation.getFirstDayForecast()));
        System.out.println(weatherInformation.getTemperature(weatherInformation.getFirstDayForecast()));
        System.out.println(weatherInformation.getMinTemperature(weatherInformation.getFirstDayForecast()));
        System.out.println(weatherInformation.getMaxTemperature(weatherInformation.getFirstDayForecast()));
        System.out.println(weatherInformation.getFeelsLikeTemperature(weatherInformation.getFirstDayForecast()));
        System.out.println(weatherInformation.getHumidity(weatherInformation.getFirstDayForecast()));
        System.out.println(weatherInformation.getPressure(weatherInformation.getFirstDayForecast()));
        System.out.println(weatherInformation.getCloudiness(weatherInformation.getFirstDayForecast()));
        System.out.println(weatherInformation.getWindSpeed(weatherInformation.getFirstDayForecast()));
    }
}
