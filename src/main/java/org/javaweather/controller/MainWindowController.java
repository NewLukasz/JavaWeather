package org.javaweather.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.javaweather.model.WeatherInformation;

public class MainWindowController {

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

    @FXML
    void TestButtonAction() {
        homeCity.setText("Przyklad - Lublin");
        homeCountry.setText("Przyklad - Polska");
        homeMainDayTemperature.setText("Przyklad - 20st");
        System.out.println("Test button action");
    }
}
