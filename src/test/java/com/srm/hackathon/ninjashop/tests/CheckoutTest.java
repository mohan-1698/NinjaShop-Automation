package com.srm.hackathon.ninjashop.tests;

import com.srm.hackathon.ninjashop.base.BaseTest;
import com.srm.hackathon.ninjashop.pages.*;
import com.srm.hackathon.ninjashop.utils.ScreenshotUtil;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    private void registerUser(HomePage home, RegisterPage register, AccountSuccessPage success) {

        home.waitForHomePage();
        home.goToRegister();

        String email = "test" + System.currentTimeMillis() + "@gmail.com";

        register.registerUser(
                "Mickey",
                "Mouse",
                email,
                "9876543210",
                "Test@123"
        );

        success.clickLogoToHome();
    }

    @Test
    public void testCheckoutWithStockLimitation() {

        HomePage home = new HomePage(driver);
        RegisterPage register = new RegisterPage(driver);
        AccountSuccessPage success = new AccountSuccessPage(driver);
        CartPage cart = new CartPage(driver);
        CheckoutPage checkout = new CheckoutPage(driver);

        // Step 1: Register
        registerUser(home, register, success);

        // Step 2: Add product
        home.addFirstProductToCart();
        home.waitForCartToUpdate("1 item");

        // Step 3: Go to cart
        home.goToCart();
        cart.waitForCartPage();

        // Step 4: Click checkout
        checkout.clickCheckout();

        // Step 5: Handle stock limitation
        if (checkout.isStockErrorDisplayed()) {

            String errorMsg = checkout.getStockErrorMessage();

            System.out.println("⚠️ STOCK LIMITATION DETECTED:");
            System.out.println(errorMsg);

            // Screenshot for proof
            ScreenshotUtil.capture(driver, "Stock_Limitation");

            // Pass the test (expected behavior)
            Assert.assertTrue(errorMsg.contains("not available"),
                    "Stock limitation message mismatch");

        } else {

            Assert.fail("Expected stock limitation message not displayed");
        }
    }
}