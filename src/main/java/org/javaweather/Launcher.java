package org.javaweather;

import javafx.application.Application;
import javafx.stage.Stage;
import org.javaweather.view.ViewFactory;

public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        WeatherManager weatherManager = new WeatherManager();
        if(!weatherManager.checkPersistenceAndLoadFromAPIIfIsInUse()){
            weatherManager.setDefaultCitiesAndLoadFromAPI();
        }
        ViewFactory viewFactory = new ViewFactory(weatherManager);
        viewFactory.showMainWindow();
    }
}