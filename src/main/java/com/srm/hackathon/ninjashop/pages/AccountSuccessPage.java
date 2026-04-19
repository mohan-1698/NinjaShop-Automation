package com.srm.hackathon.ninjashop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountSuccessPage {

    private WebDriver driver;

    public AccountSuccessPage(WebDriver driver) {
        this.driver = driver;
    }

    private By successMessage = By.xpath("//h1[text()='Your Account Has Been Created!']");
    private By logo = By.xpath("//a[text()='Qafox.com']");

    public boolean isRegistrationSuccessful() {
        return driver.findElement(successMessage).isDisplayed();
    }

    public void clickLogoToHome() {
        driver.findElement(logo).click();
    }
}