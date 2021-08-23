package org.javaweather.controller;

import org.javaweather.WeatherManager;
import org.javaweather.view.ViewFactory;

public abstract class BaseController {

    protected WeatherManager weatherManager;
    protected ViewFactory viewFactory;
    private String fxmlName;

    public BaseController(WeatherManager weatherManager, ViewFactory viewFactory, String fxmlName){
        this.weatherManager = weatherManager;
        this.viewFactory = viewFactory;
        this.fxmlName = fxmlName;
    }

    public String getFXMLname(){
        return fxmlName;
    };

}
