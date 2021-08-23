package org.javaweather.view;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class IconResolver {
    public Image getIconForWeather(String weatherIconCode){
        Image image;
        try{
            image = new Image(getClass().getResourceAsStream("icons/"+weatherIconCode+".png"));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return image;
    }
}
