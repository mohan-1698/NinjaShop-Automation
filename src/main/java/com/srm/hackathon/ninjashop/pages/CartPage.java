package com.srm.hackathon.ninjashop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ================= LOCATORS =================

    private By productTable = By.xpath("//div[@class='table-responsive']//table");
    private By emptyMsg = By.xpath("//p[contains(text(),'Your shopping cart is empty')]");

    // ================= PAGE LOAD =================

    public void waitForCartPage() {
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(productTable),
                ExpectedConditions.visibilityOfElementLocated(emptyMsg)
        ));
    }

    // ================= VALIDATIONS =================

    public boolean isProductPresent(String expectedProduct) {

        List<WebElement> rows = driver.findElements(
                By.xpath("//div[@class='table-responsive']//table//tbody/tr")
        );

        for (WebElement row : rows) {
            String name = row.findElement(By.xpath(".//td[2]//a")).getText();
            if (name.equalsIgnoreCase(expectedProduct)) {
                return true;
            }
        }
        return false;
    }

    public String getFirstProductName() {

        List<WebElement> rows = driver.findElements(
                By.xpath("//div[@class='table-responsive']//table//tbody/tr")
        );

        return rows.size() > 0
                ? rows.get(0).findElement(By.xpath(".//td[2]//a")).getText()
                : "";
    }

    public String getFirstProductPrice() {

        List<WebElement> rows = driver.findElements(
                By.xpath("//div[@class='table-responsive']//table//tbody/tr")
        );

        return rows.size() > 0
                ? rows.get(0).findElement(By.xpath(".//td[5]")).getText()
                : "";
    }

    public void updateQuantity(String qty) {

        // Get rows
        List<WebElement> rows = driver.findElements(
                By.xpath("//div[@class='table-responsive']//table//tbody/tr")
        );

        if (rows.size() > 0) {

            WebElement qtyField = rows.get(0).findElement(
                    By.xpath(".//input[contains(@name,'quantity')]")
            );

            WebElement updateBtn = rows.get(0).findElement(
                    By.xpath(".//button[@data-original-title='Update']")
            );

            // Update quantity
            qtyField.clear();
            qtyField.sendKeys(qty);
            updateBtn.click();

            // 🔥 IMPORTANT: Wait for refresh (DON'T reuse old element)
            wait.until(ExpectedConditions.refreshed(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//div[@class='table-responsive']//table//tbody/tr")
                    )
            ));

            // 🔥 Re-fetch element after refresh
            WebElement updatedQtyField = driver.findElement(
                    By.xpath("//div[@class='table-responsive']//table//tbody/tr[1]//input[contains(@name,'quantity')]")
            );

            wait.until(ExpectedConditions.attributeToBe(updatedQtyField, "value", qty));
        }
    }

    // ================= REMOVE =================

    public void removeProduct() {

        List<WebElement> rows = driver.findElements(
                By.xpath("//div[@class='table-responsive']//table//tbody/tr")
        );

        if (rows.size() > 0) {

            WebElement removeBtn = rows.get(0).findElement(By.xpath(".//button[@data-original-title='Remove']"));
            removeBtn.click();

            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(emptyMsg),
                    ExpectedConditions.numberOfElementsToBe(
                            By.xpath("//div[@class='table-responsive']//table//tbody/tr"), 0
                    )
            ));
        }
    }

    public boolean isCartEmpty() {
        return driver.findElements(emptyMsg).size() > 0;
    }
}