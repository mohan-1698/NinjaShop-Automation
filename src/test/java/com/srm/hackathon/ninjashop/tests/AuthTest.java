package com.srm.hackathon.ninjashop.tests;

import com.srm.hackathon.ninjashop.base.BaseTest;
import com.srm.hackathon.ninjashop.pages.*;
import com.srm.hackathon.ninjashop.utils.ScreenshotUtil;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthTest extends BaseTest {

    @Test
    public void testValidLogin() {

        HomePage home = new HomePage(driver);
        LoginPage login = new LoginPage(driver);
        AccountPage account = new AccountPage(driver);

        home.waitForHomePage(); // 🔥 FIX
        home.goToLogin();

        login.login("validuser@gmail.com", "Test@123");

        Assert.assertTrue(
                account.isAccountPageLoaded(),
                "Login failed with valid credentials"
        );
    }

    @Test
    public void testInvalidLogin() {

        HomePage home = new HomePage(driver);
        LoginPage login = new LoginPage(driver);

        home.waitForHomePage(); // 🔥 FIX
        home.goToLogin();

        login.login("validuser@gmail.com", "wrongpass");

        Assert.assertTrue(login.isErrorDisplayed(), "Error message not displayed");

        ScreenshotUtil.capture(driver, "InvalidLogin_" + System.currentTimeMillis());

        Assert.assertTrue(
                login.getErrorMessage().contains("No match"),
                "Incorrect error message"
        );
    }

    @Test
    public void testLogout() {

        HomePage home = new HomePage(driver);
        LoginPage login = new LoginPage(driver);
        AccountPage account = new AccountPage(driver);

        home.waitForHomePage(); // 🔥 FIX
        home.goToLogin();

        login.login("validuser@gmail.com", "Test@123");

        Assert.assertTrue(account.isAccountPageLoaded());

        account.logout();

        Assert.assertTrue(account.isLogoutSuccessful(), "Logout failed");

        account.clickContinueAfterLogout();

        Assert.assertTrue(
                driver.getTitle().contains("Your Store"),
                "Not redirected to home page after logout"
        );
    }
}