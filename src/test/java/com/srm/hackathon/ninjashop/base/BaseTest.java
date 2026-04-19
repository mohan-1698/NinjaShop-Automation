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

        DriverFactory.initDriver(); // no return

        driver = DriverManager.getDriver(); // get from ThreadLocal

        driver.get(prop.getProperty("baseUrl"));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
        DriverManager.unload();
    }
}