package com.srm.hackathon.ninjashop.listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.srm.hackathon.ninjashop.utils.ExtentManager;
import com.srm.hackathon.ninjashop.utils.ScreenshotUtil;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListeners implements ITestListener {
    ExtentTest test;

    public void onTestStart(ITestResult result) {
        test = ExtentManager.getInstance().createTest(result.getName());
    }

    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test Passed");
    }

    public void onTestFailure(ITestResult result) {
        String path = ScreenshotUtil.capture(null, result.getName());
        test.fail(result.getThrowable());
        if (path != null) {
            test.addScreenCaptureFromPath(path);
        }
    }
}