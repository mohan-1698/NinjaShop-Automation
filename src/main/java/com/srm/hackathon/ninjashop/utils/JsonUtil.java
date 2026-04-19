package com.srm.hackathon.ninjashop.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonUtil {

    public static JsonArray readJsonArray(String path) {
        try {
            String json = new String(Files.readAllBytes(Paths.get(path)));
            return JsonParser.parseString(json).getAsJsonArray();
        } catch (Exception e) {
            throw new RuntimeException("Failed to read JSON file");
        }
    }
}