package com.twu.biblioteca.utils;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class JSONLoader {

    protected JSONParser jsonParser = new JSONParser();

    /**
     * Wrapper for JSON file loading
     * @param fileName File name
     * @return JSON object array
     * @throws IOException For file not found exception
     * @throws ParseException For parsing errors
     */
    public JSONArray load(String fileName) throws IOException, ParseException {
        Reader reader = new FileReader(fileName);
        return (JSONArray) this.getJsonParser().parse(reader);
    }

    public JSONParser getJsonParser() {
        return this.jsonParser;
    }
}
