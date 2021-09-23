package org.javaweather.controller.services;

public enum MessageCode {
    CITY_FOUND, CITY_NOT_FOUND, UNKNOWN;

    public static MessageCode fromCode(int code) {
        switch (code) {
            case 200:
                return CITY_FOUND;
            case 404:
                return CITY_NOT_FOUND;
            default:
                return UNKNOWN;
        }
    }
}
