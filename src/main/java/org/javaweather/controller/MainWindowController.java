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
    private WeatherInformation homeWeatherInformation;
    private IconResolver iconResolver = new IconResolver();

    final Integer INDEX_OF_FIRST_DAY=0;
    final Integer INDEX_OF_SECOND_DAY=1;
    final Integer INDEX_OF_THIRD_DAY=2;
    final Integer INDEX_OF_FOURTH_DAY=3;
    final Integer INDEX_OF_FIFTH_DAY=4;

    @FXML
    private Label homeCity;
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


    public MainWindowController(WeatherManager weatherManager, ViewFactory viewFactory,String fxmlName){
        super(weatherManager,viewFactory,fxmlName);
        this.homeWeatherInformation = weatherManager.getHomeWeather();
    }

    @FXML
    private TextField homeCityPicker;

    @FXML
    void ChangeHomeLocationButtonAction() {
        homeWeatherInformation.setCityAndReloadData(homeCityPicker.getText());
        fulfilHomeWeather();
        homeCity.setText(homeWeatherInformation.getCity());
    }

    private void fulfilOneWeatherDayTestFunction(Integer indexOfTheDay,WeatherInformation weatherInformation, ObservableList<Node> listWithComponents){
        JSONObject oneDayDataFromForecastList = homeWeatherInformation.getOneDayFromForecast(indexOfTheDay);
        for(Node component:listWithComponents){
            String idOfFXMLComponent=component.getId();
            if(idOfFXMLComponent==null){
                continue;
            }
            if(component.getClass().equals(this.homeCity.getClass())){
                if(idOfFXMLComponent.contains("Date")){
                    ((Label) component).setText(weatherInformation.getDateOfWeather(oneDayDataFromForecastList));
                }else if(idOfFXMLComponent.contains("Wind")){
                    ((Label) component).setText(weatherInformation.getWindSpeed(oneDayDataFromForecastList));
                }else if(idOfFXMLComponent.contains("FeelsLike")){
                    ((Label) component).setText(weatherInformation.getFeelsLikeTemperature(oneDayDataFromForecastList));
                }else if(idOfFXMLComponent.contains("Temp")){
                    ((Label) component).setText(weatherInformation.getTemperature(oneDayDataFromForecastList));
                }else if(idOfFXMLComponent.contains("Description")){
                    ((Label) component).setText(weatherInformation.getWeatherMainDescription(oneDayDataFromForecastList));
                }else if(idOfFXMLComponent.contains("Pressure")){
                    ((Label) component).setText(weatherInformation.getPressure(oneDayDataFromForecastList));
                }else if(idOfFXMLComponent.contains("Cloudiness")){
                    ((Label) component).setText(weatherInformation.getCloudiness(oneDayDataFromForecastList));
                }else if(idOfFXMLComponent.contains("Humidity")){
                    ((Label) component).setText(weatherInformation.getCloudiness(oneDayDataFromForecastList));
                }

            }else if(component.getClass().equals(this.homeFirstDayIcon.getClass())){
                Image image = iconResolver.getIconForWeather(weatherInformation.getWeatherIconCode(oneDayDataFromForecastList));
                ((ImageView) component).setImage(image);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        homeWeatherInformation.setWeatherDataBasedOnFetchService();
        homeCity.setText(homeWeatherInformation.getCity());
        fulfilHomeWeather();
    }


    private void fulfilHomeWeather(){
        fulfilOneWeatherDayTestFunction(INDEX_OF_FIRST_DAY,homeWeatherInformation,homeFirstDayPaneWeather.getChildren());
        fulfilOneWeatherDayTestFunction(INDEX_OF_SECOND_DAY,homeWeatherInformation,homeSecondDayPaneWeather.getChildren());
        fulfilOneWeatherDayTestFunction(INDEX_OF_THIRD_DAY,homeWeatherInformation,homeThirdDayPaneWeather.getChildren());
        fulfilOneWeatherDayTestFunction(INDEX_OF_FOURTH_DAY,homeWeatherInformation,homeFourthDayPaneWeather.getChildren());
        fulfilOneWeatherDayTestFunction(INDEX_OF_FIFTH_DAY,homeWeatherInformation,homeFifthDayPaneWeather.getChildren());
    }

}
