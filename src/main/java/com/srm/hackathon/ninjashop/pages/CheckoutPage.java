package com.srm.hackathon.ninjashop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CheckoutPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Checkout button
    private By checkoutBtn = By.xpath("//a[contains(@href,'checkout/checkout')]");

    // Stock error message
    private By stockError = By.xpath("//div[contains(@class,'alert-danger')]");

    public void clickCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();
    }

    public boolean isStockErrorDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(stockError)
        ).isDisplayed();
    }

    public String getStockErrorMessage() {
        return driver.findElement(stockError).getText();
    }
}