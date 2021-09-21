package org.javaweather.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.javaweather.WeatherManager;
import org.javaweather.controller.BaseController;
import org.javaweather.controller.MainWindowController;

import java.io.IOException;

public class ViewFactory {
    private final WeatherManager weatherManager;

    public ViewFactory(WeatherManager weatherManager) {
        this.weatherManager = weatherManager;
    }

    public void showMainWindow() {
        BaseController controller = new MainWindowController(weatherManager, this, "MainWindow.fxml");
        initializeStage(controller);
    }

    private void initializeStage(BaseController baseController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(baseController.getFXMLname()));
        fxmlLoader.setController(baseController);
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
