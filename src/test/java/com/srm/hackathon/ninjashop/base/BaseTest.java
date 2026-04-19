package com.srm.hackathon.ninjashop.base;

import com.srm.hackathon.ninjashop.factory.DriverFactory;
import com.srm.hackathon.ninjashop.factory.DriverManager;
import com.srm.hackathon.ninjashop.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.Properties;

public class BaseTest {

    protected WebDriver driver;
    protected Properties prop;

    @BeforeMethod
    public void setup() {

        prop = ConfigReader.init();

        DriverFactory.initDriver();

        driver = DriverManager.getDriver();

        String url = prop.getProperty("baseUrl"); // ✅ FIXED

        if (url == null || url.isEmpty()) {
            throw new RuntimeException("baseUrl is missing in config.properties");
        }

        driver.get(url);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
        DriverManager.unload();
    }
}