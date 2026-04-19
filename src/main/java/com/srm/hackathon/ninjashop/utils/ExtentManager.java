package com.srm.hackathon.ninjashop.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {

            String reportDir = System.getProperty("user.dir") + "/reports/";
            File dir = new File(reportDir);

            // ✅ Create reports folder
            if (!dir.exists()) {
                dir.mkdirs();
            }

            ExtentSparkReporter reporter =
                    new ExtentSparkReporter(reportDir + "report.html");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }

        return extent;
    }
}