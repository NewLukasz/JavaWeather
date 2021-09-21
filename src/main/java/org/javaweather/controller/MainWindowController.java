package org.javaweather.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.javaweather.WeatherManager;
import org.javaweather.model.WeatherInformation;
import org.javaweather.view.IconResolver;
import org.javaweather.view.ViewFactory;
import org.json.JSONObject;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController extends BaseController implements Initializable {
    private final WeatherInformation homeWeatherInformation;
    private final WeatherInformation vacationWeatherInformation;
    private final IconResolver iconResolver = new IconResolver();

    private final Integer INDEX_OF_FIRST_DAY = 0;
    private final Integer INDEX_OF_SECOND_DAY = 1;
    private final Integer INDEX_OF_THIRD_DAY = 2;
    private final Integer INDEX_OF_FOURTH_DAY = 3;
    private final Integer INDEX_OF_FIFTH_DAY = 4;

    @FXML
    private Label homeCity;
    @FXML
    private Label homeCityErrorLabel;
    @FXML
    private ImageView homeFirstDayIcon;
    @FXML
    private Pane homeFirstDayPaneWeather;
    @FXML
    private Pane homeSecondDayPaneWeather;
    @FXML
    private Pane homeThirdDayPaneWeather;
    @FXML
    private Pane homeFourthDayPaneWeather;
    @FXML
    private Pane homeFifthDayPaneWeather;
    @FXML
    private TextField homeCityPicker;


    @FXML
    private Label vacationCity;
    @FXML
    private Pane vacationFirstDayPaneWeather;
    @FXML
    private Pane vacationSecondDayPaneWeather;
    @FXML
    private Pane vacationThirdDayPaneWeather;
    @FXML
    private Pane vacationFourthDayPaneWeather;
    @FXML
    private Pane vacationFifthDayPaneWeather;
    @FXML
    private TextField vacationCityPicker;
    @FXML
    private Label vacationDestinationErrorLabel;


    public MainWindowController(WeatherManager weatherManager, ViewFactory viewFactory, String fxmlName) {
        super(weatherManager, viewFactory, fxmlName);
        this.homeWeatherInformation = weatherManager.getHomeWeather();
        this.vacationWeatherInformation = weatherManager.getVacationWeather();
    }

    @FXML
    void changeHomeLocationButtonAction() {
        if (homeCityPicker.getText().equals("")) {
            homeCityErrorLabel.setText("You didn't type new value in text field");
            return;
        }
        homeWeatherInformation.setCityAndLoadDataFromAPI(homeCityPicker.getText());
        if (!homeWeatherInformation.getChangeCityStatus()) {
            homeCityErrorLabel.setText("City is not found");
        } else {
            homeCityErrorLabel.setText("");
        }
        fulfilHomeWeather();
        homeCity.setText(homeWeatherInformation.getCity() + " (" + homeWeatherInformation.getCountry() + ")");
        weatherManager.saveCitiesPersistence(homeWeatherInformation.getCity(), vacationWeatherInformation.getCity());
        homeCityPicker.clear();
    }

    @FXML
    void changeVacationDestinationButtonAction() {
        if (vacationCityPicker.getText().equals("")) {
            vacationDestinationErrorLabel.setText("You didn't type new value in text field");
            return;
        }
        vacationWeatherInformation.setCityAndLoadDataFromAPI(vacationCityPicker.getText());
        if (!vacationWeatherInformation.getChangeCityStatus()) {
            vacationDestinationErrorLabel.setText("City is not found");
        } else {
            vacationDestinationErrorLabel.setText("");
        }
        fulfilVacationWeather();
        vacationCity.setText(vacationWeatherInformation.getCity() + " (" + vacationWeatherInformation.getCountry() + ")");
        weatherManager.saveCitiesPersistence(homeWeatherInformation.getCity(), vacationWeatherInformation.getCity());
        vacationCityPicker.clear();
    }

    private void fulfilOneWeatherDayFunction(Integer indexOfTheDay, WeatherInformation weatherInformation, ObservableList<Node> listWithComponents) {
        JSONObject oneDayDataFromForecastList = weatherInformation.getOneDayFromForecast(indexOfTheDay);
        for (Node component : listWithComponents) {
            String idOfFXMLComponent = component.getId();
            if (idOfFXMLComponent == null) {
                continue;
            }
            if (component.getClass().equals(this.homeCity.getClass())) {
                if (idOfFXMLComponent.contains("Date")) {
                    ((Label) component).setText(weatherInformation.getDateOfWeather(oneDayDataFromForecastList));
                } else if (idOfFXMLComponent.contains("Wind")) {
                    ((Label) component).setText(weatherInformation.getWindSpeed(oneDayDataFromForecastList));
                } else if (idOfFXMLComponent.contains("FeelsLike")) {
                    ((Label) component).setText(weatherInformation.getFeelsLikeTemperature(oneDayDataFromForecastList));
                } else if (idOfFXMLComponent.contains("Temp")) {
                    ((Label) component).setText(weatherInformation.getTemperature(oneDayDataFromForecastList));
                } else if (idOfFXMLComponent.contains("Description")) {
                    ((Label) component).setText(weatherInformation.getWeatherMainDescription(oneDayDataFromForecastList));
                } else if (idOfFXMLComponent.contains("Pressure")) {
                    ((Label) component).setText(weatherInformation.getPressure(oneDayDataFromForecastList));
                } else if (idOfFXMLComponent.contains("Cloudiness")) {
                    ((Label) component).setText(weatherInformation.getCloudiness(oneDayDataFromForecastList));
                } else if (idOfFXMLComponent.contains("Humidity")) {
                    ((Label) component).setText(weatherInformation.getCloudiness(oneDayDataFromForecastList));
                }

            } else if (component.getClass().equals(this.homeFirstDayIcon.getClass())) {
                Image image = iconResolver.getIconForWeather(weatherInformation.getWeatherIconCode(oneDayDataFromForecastList));
                ((ImageView) component).setImage(image);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        homeWeatherInformation.setWeatherDataBasedOnFetchService();
        homeCity.setText(homeWeatherInformation.getCity() + " (" + homeWeatherInformation.getCountry() + ")");
        fulfilHomeWeather();

        vacationCity.setText(vacationWeatherInformation.getCity() + " (" + vacationWeatherInformation.getCountry() + ")");
        vacationWeatherInformation.setWeatherDataBasedOnFetchService();
        fulfilVacationWeather();
    }


    private void fulfilHomeWeather() {
        fulfilOneWeatherDayFunction(INDEX_OF_FIRST_DAY, homeWeatherInformation, homeFirstDayPaneWeather.getChildren());
        fulfilOneWeatherDayFunction(INDEX_OF_SECOND_DAY, homeWeatherInformation, homeSecondDayPaneWeather.getChildren());
        fulfilOneWeatherDayFunction(INDEX_OF_THIRD_DAY, homeWeatherInformation, homeThirdDayPaneWeather.getChildren());
        fulfilOneWeatherDayFunction(INDEX_OF_FOURTH_DAY, homeWeatherInformation, homeFourthDayPaneWeather.getChildren());
        fulfilOneWeatherDayFunction(INDEX_OF_FIFTH_DAY, homeWeatherInformation, homeFifthDayPaneWeather.getChildren());
    }

    private void fulfilVacationWeather() {
        fulfilOneWeatherDayFunction(INDEX_OF_FIRST_DAY, vacationWeatherInformation, vacationFirstDayPaneWeather.getChildren());
        fulfilOneWeatherDayFunction(INDEX_OF_SECOND_DAY, vacationWeatherInformation, vacationSecondDayPaneWeather.getChildren());
        fulfilOneWeatherDayFunction(INDEX_OF_THIRD_DAY, vacationWeatherInformation, vacationThirdDayPaneWeather.getChildren());
        fulfilOneWeatherDayFunction(INDEX_OF_FOURTH_DAY, vacationWeatherInformation, vacationFourthDayPaneWeather.getChildren());
        fulfilOneWeatherDayFunction(INDEX_OF_FIFTH_DAY, vacationWeatherInformation, vacationFifthDayPaneWeather.getChildren());
    }

}
