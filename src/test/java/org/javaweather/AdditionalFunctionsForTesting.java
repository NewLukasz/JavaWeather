package org.javaweather;

//import org.javaweather.model.WeatherInformationTest;
import org.json.JSONObject;

import java.io.IOException;

public class AdditionalFunctionsForTesting {
    public static JSONObject getJsonObjectFromFile(String fileName) {
        try {
            String inputStringFromResources = new String(AdditionalFunctionsForTesting.class.getClassLoader().getResourceAsStream(fileName).readAllBytes());
            return new JSONObject(inputStringFromResources);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }
}
