package com.srm.hackathon.ninjashop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class AccountPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    private By myAccount = By.xpath("//span[text()='My Account']");
    private By logout = By.xpath("//a[contains(@href,'logout')]");
    private By logoutMsg = By.xpath("//h1[text()='Account Logout']");
    private By accountHeading = By.xpath("//h2[text()='My Account']");
    private By logo = By.xpath("//a[text()='Qafox.com']");
    private By continueBtn = By.xpath("//a[text()='Continue']");

    // ================= LOGIN SUCCESS =================
    public boolean isAccountPageLoaded() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(accountHeading)
        ).isDisplayed();
    }

    // ================= LOGOUT =================
    public void logout() {

        // Step 1: Click My Account dropdown
        wait.until(ExpectedConditions.elementToBeClickable(myAccount)).click();

        // Step 2: Click Logout
        wait.until(ExpectedConditions.elementToBeClickable(logout)).click();
    }

    public boolean isLogoutSuccessful() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(logoutMsg)
        ).isDisplayed();
    }

    // ================= AFTER LOGOUT =================
    public void clickContinueAfterLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
    }

    // ================= NAVIGATION =================
    public void clickLogoToHome() {
        wait.until(ExpectedConditions.elementToBeClickable(logo)).click();
    }
}