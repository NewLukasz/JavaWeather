package org.javaweather.view;

import javafx.scene.image.Image;

public class IconResolver {
    public Image getIconForWeather(String weatherIconCode) {
        Image image;
        try {
            image = new Image(getClass().getResourceAsStream("icons/" + weatherIconCode + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return image;
    }
}
