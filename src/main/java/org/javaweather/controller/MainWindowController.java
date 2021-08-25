package org.javaweather.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.javaweather.WeatherManager;
import org.javaweather.model.WeatherInformation;
import org.javaweather.view.IconResolver;
import org.javaweather.view.ViewFactory;
import org.json.JSONObject;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController extends BaseController implements Initializable {
    private WeatherInformation homeWeatherInformation;
    private IconResolver iconResolver = new IconResolver();

    final Integer INDEX_OF_TODAY_FIRST_DAY=0;
    final Integer INDEX_OF_SECOND_DAY=1;
    final Integer INDEX_OF_THIRD_DAY=2;
    final Integer INDEX_OF_FOURTH_DAY=3;
    final Integer INDEX_OF_FIFTH_DAY=4;

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
    /*
    @FXML
    private Label homeSecondDayDate;
    @FXML
    private ImageView homeSecondDayIcon;
    @FXML
    private Label homeSecondDayShortDescription;
    @FXML
    private Label homeSecondDayTempMin;
    @FXML
    private Label homeSecondDayTempMax;
    @FXML
    private Label homeSecondDayWind;
     */
    @FXML
    private Label homeThirdDayDate;
    @FXML
    private ImageView homeThirdDayIcon;
    @FXML
    private Label homeThirdDayShortDescription;
    @FXML
    private Label homeThirdDayTempMin;
    @FXML
    private Label homeThirdDayTempMax;
    @FXML
    private Label homeThirdDayWind;
    @FXML
    private Label homeFourthDayDate;
    @FXML
    private ImageView homeFourthDayIcon;
    @FXML
    private Label homeFourthDayShortDescription;
    @FXML
    private Label homeFourthDayTempMin;
    @FXML
    private Label homeFourthDayTempMax;
    @FXML
    private Label homeFourthDayWind;
    @FXML
    private Label homeFifthDayDate;
    @FXML
    private ImageView homeFifthDayIcon;
    @FXML
    private Label homeFifthDayShortDescription;
    @FXML
    private Label homeFifthDayTempMin;
    @FXML
    private Label homeFifthDayTempMax;
    @FXML
    private Label homeFifthDayWind;

    @FXML
    private Pane homeFirstDayWeather;

    @FXML
    private AnchorPane mainPane;

    public MainWindowController(WeatherManager weatherManager, ViewFactory viewFactory,String fxmlName){
        super(weatherManager,viewFactory,fxmlName);
        this.homeWeatherInformation = weatherManager.getHomeWeather();
    }

    @FXML
    private TextField cityPicker;

    @FXML
    void TestButtonAction() {
        homeWeatherInformation.setCityAndReloadData(cityPicker.getText());
        fulfilHomeWeatherDataForToday();
        fulfilHomeWeatherForFourDayForecast();
        homeCity.setText(homeWeatherInformation.getCity());
        System.out.println(mainPane.getChildren());

        for(Node node: mainPane.getChildren()){
            if(node.getClass().equals(homeFirstDayWeather.getClass())){
                System.out.println(node.getId());
            }
        }

        ObservableList<Node> listWithFXMLComponents = homeFirstDayWeather.getChildren();
        fulfilOneWeatherDayTestFunction(homeWeatherInformation,listWithFXMLComponents);

    }

    private void fulfilOneWeatherDayTestFunction(WeatherInformation weatherInformation, ObservableList<Node> listWithComponents){
        JSONObject oneDayDataFromForecastList = homeWeatherInformation.getOneDayFromForecast(INDEX_OF_SECOND_DAY);
        for(Node component:listWithComponents){
            String idOfFXMLComponent=component.getId();
            if(component.getClass().equals(this.homeFifthDayDate.getClass())){
                if(idOfFXMLComponent.contains("Date")){
                    ((Label) component).setText(weatherInformation.getDateOfWeather(oneDayDataFromForecastList));
                }else if(idOfFXMLComponent.contains("Wind")){
                    ((Label) component).setText(weatherInformation.getWindSpeed(oneDayDataFromForecastList));
                }else if(idOfFXMLComponent.contains("Temp")&&component.getId().contains("Max")){
                    ((Label) component).setText(weatherInformation.getMaxTemperature(oneDayDataFromForecastList));
                }else if(idOfFXMLComponent.contains("Temp")&&component.getId().contains("Min")){
                    ((Label) component).setText(weatherInformation.getMinTemperature(oneDayDataFromForecastList));
                }else if(idOfFXMLComponent.contains("Description")){
                    ((Label) component).setText(weatherInformation.getWeatherMainDescription(oneDayDataFromForecastList));
                }
            }else if(component.getClass().equals(this.homeFourthDayIcon.getClass())){
                System.out.println("OK");
                Image image = iconResolver.getIconForWeather(weatherInformation.getWeatherIconCode(oneDayDataFromForecastList));
                ((ImageView) component).setImage(image);
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        homeWeatherInformation.setWeatherDataBasedOnFetchService();
        homeCity.setText(homeWeatherInformation.getCity());
        fulfilHomeWeatherDataForToday();
        fulfilHomeWeatherForFourDayForecast();
    }

    private void fulfilHomeWeatherDataForToday(){
        fulfilMainDayTodayWeather(homeWeatherInformation,
                homeWeatherInformation.getOneDayFromForecast(INDEX_OF_TODAY_FIRST_DAY),
                todaysDate,
                shortWeatherDescription,
                longWeatherDescription,
                homeMainDayIcon,
                homeMainDayTemperature,
                homeMainTemperatureFeelsLike,
                mainDayWind,
                mainDayPressure,
                mainDayCloudiness,
                mainDayHumidity);
    }

    private void fulfilHomeWeatherForFourDayForecast(){
        /*
        fulfilOneDayWeatherForecast(homeWeatherInformation,
                homeWeatherInformation.getOneDayFromForecast(INDEX_OF_SECOND_DAY),
                homeSecondDayDate,
                homeSecondDayShortDescription,
                homeSecondDayIcon,
                homeSecondDayTempMin,
                homeSecondDayTempMax,
                homeSecondDayWind
                );*/

        fulfilOneDayWeatherForecast(homeWeatherInformation,
                homeWeatherInformation.getOneDayFromForecast(INDEX_OF_THIRD_DAY),
                homeThirdDayDate,
                homeThirdDayShortDescription,
                homeThirdDayIcon,
                homeThirdDayTempMin,
                homeThirdDayTempMax,
                homeThirdDayWind);

        fulfilOneDayWeatherForecast(homeWeatherInformation,
                homeWeatherInformation.getOneDayFromForecast(INDEX_OF_FOURTH_DAY),
                homeFourthDayDate,
                homeFourthDayShortDescription,
                homeFourthDayIcon,
                homeFourthDayTempMin,
                homeFourthDayTempMax,
                homeFourthDayWind);

        fulfilOneDayWeatherForecast(homeWeatherInformation,
                homeWeatherInformation.getOneDayFromForecast(INDEX_OF_FIFTH_DAY),
                homeFifthDayDate,
                homeFifthDayShortDescription,
                homeFifthDayIcon,
                homeFifthDayTempMin,
                homeFifthDayTempMax,
                homeFifthDayWind);
    }

    private void fulfilOneDayWeatherForecast(WeatherInformation weatherInformation,
                                             JSONObject oneDayDataFromForecastList,
                                             Label forecastDate,
                                             Label forecastShortDescription,
                                             ImageView weatherIcon,
                                             Label tempMin,
                                             Label tempMax,
                                             Label wind ){
        forecastDate.setText(weatherInformation.getDateOfWeather(oneDayDataFromForecastList));
        forecastShortDescription.setText(weatherInformation.getWeatherMainDescription(oneDayDataFromForecastList));
        Image image = iconResolver.getIconForWeather(weatherInformation.getWeatherIconCode(oneDayDataFromForecastList));
        weatherIcon.setImage(image);
        tempMin.setText(weatherInformation.getMinTemperature(oneDayDataFromForecastList));
        tempMax.setText(weatherInformation.getMaxTemperature(oneDayDataFromForecastList));
        wind.setText(weatherInformation.getWindSpeed(oneDayDataFromForecastList));
    }

    private void fulfilMainDayTodayWeather(WeatherInformation weatherInformation,
                                           JSONObject oneDayDataFromForecastList,
                                           Label todaysDate,
                                           Label forecastShortDescription,
                                           Label forecastLongDescription,
                                           ImageView weatherIcon,
                                           Label mainTemperature,
                                           Label temperatureFeelsLike,
                                           Label wind,
                                           Label pressure,
                                           Label cloudiness,
                                           Label humidity
                                           ){
        todaysDate.setText(weatherInformation.getDateOfWeather(oneDayDataFromForecastList));
        forecastShortDescription.setText(weatherInformation.getWeatherMainDescription(oneDayDataFromForecastList));
        forecastLongDescription.setText(homeWeatherInformation.getWeatherLongDescription(oneDayDataFromForecastList));
        Image image = iconResolver.getIconForWeather(weatherInformation.getWeatherIconCode(oneDayDataFromForecastList));
        weatherIcon.setImage(image);
        mainTemperature.setText(homeWeatherInformation.getTemperature(oneDayDataFromForecastList));
        temperatureFeelsLike.setText(homeWeatherInformation.getFeelsLikeTemperature(oneDayDataFromForecastList));
        wind.setText(homeWeatherInformation.getWindSpeed(oneDayDataFromForecastList));
        pressure.setText(homeWeatherInformation.getPressure(oneDayDataFromForecastList));
        cloudiness.setText(homeWeatherInformation.getCloudiness(oneDayDataFromForecastList));
        humidity.setText(homeWeatherInformation.getHumidity(oneDayDataFromForecastList));
    }
}
