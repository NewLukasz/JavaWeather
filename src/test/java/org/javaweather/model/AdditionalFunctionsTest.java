package org.javaweather.model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.javaweather.model.AdditionalFunctions.*;

class AdditionalFunctionsTest {
    @Test
    void changePlusToSpaceFunctionShouldReturnStringWithSpaceExceptPlus() {
        //given
        String stringWithPlus = "Nowa+Wies";
        //when
        String stringWithSpace = changePlusToSpace(stringWithPlus);
        //then
        assertThat(stringWithSpace, containsString("Nowa Wies"));
    }

    @Test
    void changeSpaceToPlusFunctionShouldReturnStringWithPlusExceptSpace() {
        //given
        String stringWithSpace = "Nowa Wies";
        //when
        String stringWithPlus = changeSpaceToPlus(stringWithSpace);
        //then
        assertThat(stringWithPlus, containsString("Nowa+Wies"));
    }

    @Test
    void convertOneElementJsonArrayToJsonObjectShouldReturnJsonObject() {
        //given
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonArray.put(jsonObject);
        //when
        JSONObject jsonObjectFromArray = convertOneElementJsonArrayToJsonObject(jsonArray);
        //then

        assertThat(jsonObjectFromArray, is(jsonObject));
    }

    @Test
    void convertPlusToSpaceIfRequiredShouldReturnStringWithPlus() {
        //given
        String stringWithPlus = "String+String";
        //when
        String stringWithSpace = convertPlusToSpaceIfRequired(stringWithPlus);
        //then
        assertThat(stringWithSpace, containsString(" "));
    }

}