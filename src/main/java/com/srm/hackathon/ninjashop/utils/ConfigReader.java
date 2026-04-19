package com.srm.hackathon.ninjashop.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties prop;

    public static Properties init() {
        try {
            prop = new Properties();
            FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prop;
    }

    public static String get(String key) {
        return prop.getProperty(key);
    }
}