package com.srm.hackathon.ninjashop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    private By email = By.id("input-email");
    private By password = By.id("input-password");
    private By loginBtn = By.xpath("//input[@value='Login']");
    private By errorMsg = By.xpath("//div[contains(@class,'alert-danger')]");

    // ================= ACTIONS =================

    public void enterEmail(String mail) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(email)).clear();
        driver.findElement(email).sendKeys(mail);
    }

    public void enterPassword(String pwd) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(password)).clear();
        driver.findElement(password).sendKeys(pwd);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
    }

    // Full flow method (best practice)
    public void login(String mail, String pwd) {
        enterEmail(mail);
        enterPassword(pwd);
        clickLogin();
    }

    // ================= VALIDATIONS =================

    public boolean isErrorDisplayed() {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(errorMsg)
            ).isDisplayed();
        } catch (Exception e) {
            return false; // prevents test crash
        }
    }

    public String getErrorMessage() {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(errorMsg)
            ).getText();
        } catch (Exception e) {
            return "";
        }
    }
}