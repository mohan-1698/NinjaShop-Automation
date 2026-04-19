package com.srm.hackathon.ninjashop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ================= LOCATORS =================
    private By myAccount = By.xpath("//a[@title='My Account']/span");
    private By registerLink = By.linkText("Register");
    private By loginLink = By.linkText("Login");

    private By searchBox = By.name("search");
    private By searchBtn = By.xpath("//button[contains(@class,'btn-lg')]");

    private By desktopsMenu = By.linkText("Desktops");
    private By macOption = By.linkText("Mac (1)");

    private By logo = By.xpath("//a[text()='Qafox.com']");

    // ================= PAGE LOAD =================
    public void waitForHomePage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(logo));
    }

    // ================= AUTH ACTIONS =================
    public void clickMyAccount() {
        wait.until(ExpectedConditions.presenceOfElementLocated(myAccount));
        wait.until(ExpectedConditions.visibilityOfElementLocated(myAccount));
        wait.until(ExpectedConditions.elementToBeClickable(myAccount)).click();
    }

    public void clickRegister() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }

    public void goToRegister() {
        clickMyAccount();
        clickRegister();
    }

    public void goToLogin() {
        clickMyAccount();
        clickLogin();
    }

    // ================= SEARCH =================
    public void searchProduct(String product) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox)).clear();
        driver.findElement(searchBox).sendKeys(product);
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
    }

    // ================= CATEGORY =================
    public void navigateToMacCategory() {

        Actions actions = new Actions(driver);

        actions.moveToElement(
                wait.until(ExpectedConditions.visibilityOfElementLocated(desktopsMenu))
        ).perform();

        wait.until(ExpectedConditions.elementToBeClickable(macOption)).click();
    }
}