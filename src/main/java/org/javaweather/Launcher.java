package org.javaweather;

import javafx.application.Application;
import javafx.stage.Stage;
import org.javaweather.view.ViewFactory;

import java.io.IOException;

public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        ViewFactory viewFactory = new ViewFactory(new WeatherManager());
        viewFactory.showMainWindow();
    }
}