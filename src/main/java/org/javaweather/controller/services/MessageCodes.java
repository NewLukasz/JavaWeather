package org.javaweather.controller.services;

public class MessageCodes {
    private static String cityNotFound = "City not found";

    public static String getCityNotFound() {
        return cityNotFound;
    }

    public static String getCityFound() {
        return cityFound;
    }

    private static String cityFound = "City found";

    public static String getMessageBasedCode(int code){
        switch (code){
            case 200: return cityFound;
            case 404: return cityNotFound;
        }
        return null;
    }
}
