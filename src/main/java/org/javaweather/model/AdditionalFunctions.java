package org.javaweather.model;

import org.json.JSONArray;
import org.json.JSONObject;

public class AdditionalFunctions {

    public static String changePlusToSpace(String stringWithPluses) {
        return stringWithPluses.replace("+", " ");
    }

    public static String changeSpaceToPlus(String stringWithSpaces) {
        if (stringWithSpaces != null) {
            return stringWithSpaces.replace(" ", "+");
        } else {
            return "";
        }
    }

    public static JSONObject convertOneElementJsonArrayToJsonObject(JSONArray oneElementJsonArray) {
        return oneElementJsonArray.getJSONObject(0);
    }

    public static String convertPlusToSpaceIfRequired(String city) {
        if (city.contains("+")) {
            return changePlusToSpace(city);
        } else {
            return city;
        }
    }
}
