package com.srm.hackathon.ninjashop.tests;

import com.srm.hackathon.ninjashop.base.BaseTest;
import com.srm.hackathon.ninjashop.pages.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class CartDebugTest extends BaseTest {

    @Test
    public void debugCartTable() {

        HomePage home = new HomePage(driver);
        RegisterPage register = new RegisterPage(driver);
        AccountSuccessPage success = new AccountSuccessPage(driver);

        // Step 1: Register
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

        // Step 2: Add product
        home.addFirstProductToCart();
        home.waitForCartToUpdate("1 item");

        // Step 3: Go to cart
        home.goToCart();

        // Step 4: Check all tables (debug only)
        List<WebElement> tables = driver.findElements(
                By.xpath("//table[contains(@class,'table-bordered')]")
        );

        System.out.println("Tables found: " + tables.size());

        if (tables.size() == 0) {
            System.out.println("❌ Cart table NOT found");
            return;
        }

        System.out.println("✅ Cart page loaded");

        // 🔥 Step 5: TARGET ONLY PRODUCT TABLE
        WebElement productTable = driver.findElement(
                By.xpath("//div[@class='table-responsive']//table")
        );

        // Step 6: Get ONLY product rows
        List<WebElement> rows = productTable.findElements(By.xpath(".//tbody/tr"));

        System.out.println("Rows count (actual products): " + rows.size());

        // Step 7: Print product details safely
        for (WebElement row : rows) {

            try {
                String name = row.findElement(By.xpath(".//td[2]//a")).getText();
                String model = row.findElement(By.xpath(".//td[3]")).getText();
                String price = row.findElement(By.xpath(".//td[5]")).getText();

                System.out.println("Product: " + name);
                System.out.println("Model: " + model);
                System.out.println("Price: " + price);
                System.out.println("----------------------");

            } catch (Exception e) {
                System.out.println("⚠️ Skipping non-product row");
            }
        }
    }
}