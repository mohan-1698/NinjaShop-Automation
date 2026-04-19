package com.srm.hackathon.ninjashop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class SearchResultsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    private By productList = By.xpath("//div[contains(@class,'product-thumb')]");
    private By productLinks = By.xpath("//div[contains(@class,'product-thumb')]//h4/a");
    private By noResultMsg = By.xpath("//p[contains(text(),'There is no product')]");

    // ================= VALIDATIONS =================

    public boolean isProductDisplayed() {
        return driver.findElements(productList).size() > 0;
    }

    public boolean isNoResultDisplayed() {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(noResultMsg)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ================= ACTIONS =================

    public void clickFirstProduct() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productLinks));
        driver.findElements(productLinks).get(0).click();
    }
}