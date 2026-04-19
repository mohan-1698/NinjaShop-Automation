package com.srm.hackathon.ninjashop.tests;

import com.srm.hackathon.ninjashop.base.BaseTest;
import com.srm.hackathon.ninjashop.pages.*;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

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

        Assert.assertTrue(success.isRegistrationSuccessful(), "Registration failed");

        success.clickLogoToHome();
    }

    // ================= ADD TO CART =================
    @Test
    public void testAddToCart() {

        HomePage home = new HomePage(driver);
        RegisterPage register = new RegisterPage(driver);
        AccountSuccessPage success = new AccountSuccessPage(driver);
        CartPage cart = new CartPage(driver);

        registerUser(home, register, success);

        // 🔥 Capture product name BEFORE adding
        String expectedProduct = home.getFirstProductName();

        home.addFirstProductToCart();
        home.waitForCartToUpdate("1 item");

        home.goToCart();
        cart.waitForCartPage();

        Assert.assertTrue(
                cart.isProductPresent(expectedProduct),
                "Correct product not added"
        );

        Assert.assertTrue(
                cart.getFirstProductPrice().contains("$"),
                "Price not displayed"
        );
    }

    // ================= UPDATE =================
    @Test
    public void testUpdateCartQuantity() {

        HomePage home = new HomePage(driver);
        RegisterPage register = new RegisterPage(driver);
        AccountSuccessPage success = new AccountSuccessPage(driver);
        CartPage cart = new CartPage(driver);

        registerUser(home, register, success);

        home.addFirstProductToCart();
        home.waitForCartToUpdate("1 item");

        home.goToCart();
        cart.waitForCartPage();

        String productName = cart.getFirstProductName();
        Assert.assertTrue(productName.length() > 0);

        cart.updateQuantity("2");

        // 🔥 Validate update
        Assert.assertTrue(
                cart.getFirstProductName().equals(productName),
                "Product mismatch after update"
        );
    }

    // ================= REMOVE =================
    @Test
    public void testRemoveFromCart() {

        HomePage home = new HomePage(driver);
        RegisterPage register = new RegisterPage(driver);
        AccountSuccessPage success = new AccountSuccessPage(driver);
        CartPage cart = new CartPage(driver);

        registerUser(home, register, success);

        home.addFirstProductToCart();
        home.waitForCartToUpdate("1 item");

        home.goToCart();
        cart.waitForCartPage();

        cart.removeProduct();

        Assert.assertTrue(
                cart.isCartEmpty(),
                "Cart not empty after removal"
        );
    }

    // ================= CART COUNT =================
    @Test
    public void testCartCount() {

        HomePage home = new HomePage(driver);
        RegisterPage register = new RegisterPage(driver);
        AccountSuccessPage success = new AccountSuccessPage(driver);

        registerUser(home, register, success);

        home.addFirstProductToCart();
        home.waitForCartToUpdate("1 item");

        String cartText = home.getCartCountText();

        Assert.assertTrue(
                cartText.contains("1 item"),
                "Cart count not updated"
        );
    }
}