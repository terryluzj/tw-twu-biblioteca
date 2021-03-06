package com.twu.biblioteca.utils;

import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

public class JSONLoaderTest {
    protected final JSONLoader jsonLoader = new JSONLoader();

    @Test
    public void testProperty() {
        Assert.assertNotNull(jsonLoader.getJsonParser());
    }

    @Test
    public void testLoadJSONObject() throws IOException, ParseException {
        JSONArray jsonObjectArray = jsonLoader.load("movie_data.json");
        Assert.assertEquals(
            "Witches, The",
            ((JSONObject) jsonObjectArray.get(0)).get("movie_title")
        );
    }
}
