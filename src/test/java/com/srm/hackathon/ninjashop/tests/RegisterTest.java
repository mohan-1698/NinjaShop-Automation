package com.srm.hackathon.ninjashop.tests;

import com.srm.hackathon.ninjashop.base.BaseTest;
import com.srm.hackathon.ninjashop.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest {

    @Test
    public void testRegistration() {

        HomePage home = new HomePage(driver);
        RegisterPage register = new RegisterPage(driver);
        AccountSuccessPage success = new AccountSuccessPage(driver);

        home.waitForHomePage(); // 🔥 FIX
        home.goToRegister();

        String email = "test" + System.currentTimeMillis() + "@gmail.com";

        register.registerUser(
                "Mickey",
                "Mouse",
                email,
                "9876543210",
                "Test@123"
        );

        Assert.assertTrue(
                success.isRegistrationSuccessful(),
                "Registration failed"
        );
    }
}