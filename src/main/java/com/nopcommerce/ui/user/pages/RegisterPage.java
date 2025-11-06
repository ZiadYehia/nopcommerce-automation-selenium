package com.nopcommerce.ui.user.pages;

import com.nopcommerce.framework.drivers.GUIDriver;
import com.nopcommerce.framework.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;


public class RegisterPage {

    //variables
    private final GUIDriver driver;
    private final String endpoint = "/register";
    // locators
    private final By registerText = By.cssSelector("h1");
    private final By maleGenderRadio = By.cssSelector("[id=\"gender-male\"]");
    private final By femaleGenderRadio = By.cssSelector("[id=\"gender-male\"]");
    private final By firstNameInput = By.cssSelector("[id=\"FirstName\"]");
    private final By lastNameInput = By.cssSelector("[id=\"LastName\"]");
    private final By emailInput = By.cssSelector("[id=\"Email\"]");
    private final By companayInput = By.cssSelector("[id=\"Company\"]");
    private final By newsletterCheckbox = By.cssSelector("#NewsLetterSubscriptions_0__IsActive");
    private final By passwordInput = By.cssSelector("[id=\"Password\"]");
    private final By confirmPasswordInput = By.cssSelector("[id=\"ConfirmPassword\"]");
    private final By registerButton = By.cssSelector("[id=\"register-button\"]");
    private final By registrationCompletedText = By.cssSelector("div[class=\"result\"]");
    private final By registrationExistErr = By.cssSelector("div[class=\"message-error validation-summary-errors\"]");

    //constructor
    public RegisterPage(GUIDriver driver) {
        this.driver = driver;
    }

    //actions
    @Step("Navigation to Registration Page")
    public RegisterPage navigate() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb") + endpoint);
        return this;
    }

    @Step("User Registration with FirstName: {firstName}, LastName: {lastName}, Email: {email}, Company: {company}, Password: {password}")
    public RegisterPage register(String firstName, String lastName, String email, String company, String password) {
        driver.element().click(maleGenderRadio);
        driver.element().type(firstNameInput, firstName);
        driver.element().type(lastNameInput, lastName);
        driver.element().type(emailInput, email);
        driver.element().click(newsletterCheckbox);
        driver.element().type(companayInput, company);
        driver.element().type(passwordInput, password);
        driver.element().type(confirmPasswordInput, password);
        driver.element().click(registerButton);
        return this;
    }

    //validations
    @Step("Verify that registration is completed successfully")
    public RegSuccessPage isRegistrationCompleted() {
        driver.verification().Equals(driver.element().getText(registrationCompletedText), "Your registration completed", "Registration completed message is not as expected");
        return new RegSuccessPage(driver);
    }

    @Step("Verify that registration is not completed")
    public RegisterPage isRegistrationNotCompleted() {
        driver.verification().Equals(driver.element().getText(registerText) ,"Register", "User is not navigated to Register page after registration failure");
        return this;
    }
    @Step("Verify that registration user already exists error message is displayed")
    public RegisterPage isRegistrationUserExist() {
        driver.verification().Equals(driver.element().getText(registrationExistErr),"The specified email already exists" ,"Registration exist error message is not displayed");
        return this;
    }

}
