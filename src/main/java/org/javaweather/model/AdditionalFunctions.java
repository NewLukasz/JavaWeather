package org.javaweather.model;

import org.json.JSONArray;
import org.json.JSONObject;

public class AdditionalFunctions {

    public static String changePlusesToSpaces(String stringWithPluses) {
        return stringWithPluses.replace("+", " ");
    }

    public static String changeSpaceToPlus(String stringWithSpaces) {
        return stringWithSpaces.replace(" ", "+");
    }

    public static JSONObject convertOneElementJsonArrayToJsonObject(JSONArray oneElementJsonArray) {
        return oneElementJsonArray.getJSONObject(0);
    }

    public static String convertPlusesToSpacesIfRequired(String city){
        if (city.contains("+")) {
            return changePlusesToSpaces(city);
        } else {
            return city;
        }
    }
}
