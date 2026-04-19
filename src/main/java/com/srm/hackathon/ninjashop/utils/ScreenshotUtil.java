package com.srm.hackathon.ninjashop.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtil {

    public static String capture(WebDriver driver, String testName) {

        try {
            // 📁 Folder path
            String dirPath = System.getProperty("user.dir") + "/screenshots/";
            Path dir = Paths.get(dirPath);

            // ✅ Create folder if not exists
            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
            }

            // 🕒 Better timestamp (readable + unique)
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"));

            // 📸 Capture screenshot
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String filePath = dirPath + testName + "_" + timestamp + ".png";

            // ✅ Copy with replace option (safe)
            Files.copy(
                    src.toPath(),
                    Paths.get(filePath),
                    StandardCopyOption.REPLACE_EXISTING
            );

            return filePath;

        } catch (Exception e) {
            throw new RuntimeException("Failed to capture screenshot: " + testName, e);
        }
    }
}