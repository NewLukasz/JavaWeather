package org.javaweather.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.javaweather.WeatherManager;
import org.javaweather.model.WeatherInformation;
import org.javaweather.view.ViewFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController extends BaseController implements Initializable {
    private WeatherManager weatherManager;
    private WeatherInformation homeWeatherInformation;

    @FXML
    private Label homeCity;
    @FXML
    private Label homeCountry;
    @FXML
    private ImageView homeMainDayIcon;
    @FXML
    private Label homeMainDayTemperature;
    @FXML
    private Label shortWeatherDescription;
    @FXML
    private Label homeMainTemperatureFeelsLike;
    @FXML
    private Label longWeatherDescription;
    @FXML
    private Label mainDayPressure;
    @FXML
    private Label mainDayWind;
    @FXML
    private Label mainDayCloudiness;
    @FXML
    private Label mainDayHumidity;
    @FXML
    private Label todaysDate;

    public MainWindowController(WeatherManager weatherManager, ViewFactory viewFactory,String fxmlName){
        super(weatherManager,viewFactory,fxmlName);
        this.homeWeatherInformation = weatherManager.getHomeWeather();
    }

    @FXML
    private TextField cityPicker;

    @FXML
    void TestButtonAction() {
        homeWeatherInformation.setCityAndReloadData(cityPicker.getText());
        fulfilDataForToday();
        homeCity.setText(homeWeatherInformation.getCity());
        System.out.println(homeWeatherInformation.getDateOfWeather(homeWeatherInformation.getFirstDayForecast()));
        /*
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
*/

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        homeWeatherInformation.setWeatherDataBasedOnFetchService();
        homeCity.setText(homeWeatherInformation.getCity());
        fulfilDataForToday();

    }

    private void fulfilDataForToday(){
        todaysDate.setText(homeWeatherInformation.getDateOfWeather(homeWeatherInformation.getFirstDayForecast()));
        homeMainDayTemperature.setText(homeWeatherInformation.getTemperature(homeWeatherInformation.getFirstDayForecast()));
        homeMainTemperatureFeelsLike.setText(homeWeatherInformation.getTemperature(homeWeatherInformation.getFirstDayForecast()));
        mainDayCloudiness.setText(homeWeatherInformation.getCloudiness(homeWeatherInformation.getFirstDayForecast()));
        mainDayHumidity.setText(homeWeatherInformation.getHumidity(homeWeatherInformation.getFirstDayForecast()));
        mainDayPressure.setText(homeWeatherInformation.getPressure(homeWeatherInformation.getFirstDayForecast()));
        mainDayWind.setText(homeWeatherInformation.getWindSpeed(homeWeatherInformation.getFirstDayForecast()));
        shortWeatherDescription.setText(homeWeatherInformation.getWeatherMainDescription(homeWeatherInformation.getFirstDayForecast()));
        longWeatherDescription.setText(homeWeatherInformation.getWeatherLongDescription(homeWeatherInformation.getFirstDayForecast()));
    }
}
