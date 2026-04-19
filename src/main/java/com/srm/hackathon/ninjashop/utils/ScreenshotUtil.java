package com.srm.hackathon.ninjashop.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;

public class ScreenshotUtil {

    public static String capture(WebDriver driver, String testName) {
        try {
            String dirPath = System.getProperty("user.dir") + "/screenshots/";
            File dir = new File(dirPath);

            // ✅ Create folder if not exists
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String filePath = dirPath + testName + "_" + System.currentTimeMillis() + ".png";

            Files.copy(src.toPath(), new File(filePath).toPath());

            return filePath;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}