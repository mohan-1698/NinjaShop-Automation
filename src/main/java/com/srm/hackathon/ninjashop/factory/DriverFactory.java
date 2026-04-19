package com.srm.hackathon.ninjashop.factory;

import com.srm.hackathon.ninjashop.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;

import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    public static void initDriver() {

        String browser = ConfigReader.get("browser").toLowerCase();
        boolean headless = Boolean.parseBoolean(ConfigReader.get("headless"));

        WebDriver driver;

        switch (browser) {

            case "firefox":
                WebDriverManager.firefoxdriver().setup();

                FirefoxOptions fOptions = new FirefoxOptions();
                if (headless) {
                    fOptions.addArguments("-headless");
                }

                driver = new FirefoxDriver(fOptions);
                break;

            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();

                ChromeOptions options = new ChromeOptions();

                // 🔥 Disable popups
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("profile.default_content_setting_values.notifications", 2);
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);

                options.setExperimentalOption("prefs", prefs);

                if (headless) {
                    options.addArguments("--headless=new");
                    options.addArguments("--window-size=1920,1080");
                }

                // Stability
                options.addArguments("--disable-notifications");
                options.addArguments("--disable-infobars");
                options.addArguments("--disable-extensions");
                options.addArguments("--disable-popup-blocking");
                options.addArguments("--remote-allow-origins=*");

                driver = new ChromeDriver(options);
                break;
        }

        try {
            driver.manage().window().maximize();
        } catch (Exception ignored) {}

        DriverManager.setDriver(driver);
    }
}