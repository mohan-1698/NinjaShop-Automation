package com.srm.hackathon.ninjashop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {

    private WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    private By productName = By.xpath("//h1");
    private By productPrice = By.xpath("//ul[@class='list-unstyled']//h2");

    public boolean isProductNameDisplayed() {
        return driver.findElement(productName).isDisplayed();
    }

    public boolean isProductPriceDisplayed() {
        return driver.findElement(productPrice).isDisplayed();
    }
}