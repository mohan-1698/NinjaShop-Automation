package com.srm.hackathon.ninjashop.listeners;

import com.aventstack.extentreports.*;
import com.srm.hackathon.ninjashop.factory.DriverManager;
import com.srm.hackathon.ninjashop.utils.ExtentManager;
import com.srm.hackathon.ninjashop.utils.ScreenshotUtil;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListeners implements ITestListener {

    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        test.set(extent.createTest(result.getName()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        WebDriver driver = DriverManager.getDriver();

        String path = ScreenshotUtil.capture(driver, result.getName());

        test.get().fail(result.getThrowable());

        if (path != null) {
            try {
                test.get().addScreenCaptureFromPath(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {
        extent.flush(); // 🔥 MUST BE CALLED
    }
}