package com.srm.hackathon.ninjashop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {

    private WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private By firstName = By.id("input-firstname");
    private By lastName = By.id("input-lastname");
    private By email = By.id("input-email");
    private By telephone = By.id("input-telephone");
    private By password = By.id("input-password");
    private By confirmPassword = By.id("input-confirm");
    private By privacyPolicy = By.name("agree");
    private By continueBtn = By.xpath("//input[@value='Continue']");

    // Actions
    public void enterFirstName(String fname) {
        driver.findElement(firstName).sendKeys(fname);
    }

    public void enterLastName(String lname) {
        driver.findElement(lastName).sendKeys(lname);
    }

    public void enterEmail(String mail) {
        driver.findElement(email).sendKeys(mail);
    }

    public void enterTelephone(String phone) {
        driver.findElement(telephone).sendKeys(phone);
    }

    public void enterPassword(String pwd) {
        driver.findElement(password).sendKeys(pwd);
    }

    public void confirmPassword(String pwd) {
        driver.findElement(confirmPassword).sendKeys(pwd);
    }

    public void acceptPrivacyPolicy() {
        driver.findElement(privacyPolicy).click();
    }

    public void clickContinue() {
        driver.findElement(continueBtn).click();
    }

    // Full Flow Method (BEST PRACTICE)
    public void registerUser(String fname, String lname, String mail, String phone, String pwd) {
        enterFirstName(fname);
        enterLastName(lname);
        enterEmail(mail);
        enterTelephone(phone);
        enterPassword(pwd);
        confirmPassword(pwd);
        acceptPrivacyPolicy();
        clickContinue();
    }
    
 // ================= VALIDATION METHODS =================

 // Error locators
 private By firstNameError = By.xpath("//div[contains(text(),'First Name must be')]");
 private By lastNameError = By.xpath("//div[contains(text(),'Last Name must be')]");
 private By emailError = By.xpath("//div[contains(text(),'E-Mail Address does not appear') or contains(text(),'E-Mail Address is already registered')]");
 private By telephoneError = By.xpath("//div[contains(text(),'Telephone must be')]");
 private By passwordError = By.xpath("//div[contains(text(),'Password must be')]");

 // Submit without filling form
 public void submitEmptyForm() {
     clickContinue();
 }

 // Check error messages
 public boolean isFirstNameErrorDisplayed() {
     return driver.findElements(firstNameError).size() > 0;
 }

 public boolean isLastNameErrorDisplayed() {
     return driver.findElements(lastNameError).size() > 0;
 }

 public boolean isEmailErrorDisplayed() {
     return driver.findElements(emailError).size() > 0;
 }

 public boolean isTelephoneErrorDisplayed() {
     return driver.findElements(telephoneError).size() > 0;
 }

 public boolean isPasswordErrorDisplayed() {
     return driver.findElements(passwordError).size() > 0;
 }
    
    
}