package com.nopcommerce.ui.user.pages;

import com.nopcommerce.framework.drivers.GUIDriver;
import com.nopcommerce.framework.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class LoginPage {
    //variables
    private final GUIDriver driver;
    private final String endpoint = "/login";
    //locators
    private final By emailInput = By.cssSelector("[id=\"Email\"]");
    private final By passwordInput = By.cssSelector("[id=\"Password\"]");
    private final By rememberMeCheck = By.cssSelector("[id=\"RememberMe\"]");
    private final By loginButton = By.cssSelector("[class*=\"login-button\"]");
    private final By forgotPassword = By.cssSelector("[href=\"/passwordrecovery\"]");
    private final By eyeIcon = By.cssSelector("[class=\"password-eye\"]"); //check type property is coverted from password to text

    //constructors
    public LoginPage(GUIDriver driver) {
        this.driver = driver;
    }

    //actions

    @Step("Navigation to Login Page")
    public LoginPage navigate() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb") + endpoint);
        return this;
    }

    @Step("User Entering data in login input fields")
    public LoginPage login(String email, String password) {
        driver.element().type(emailInput, email);
        driver.element().type(passwordInput, password);
        driver.element().click(rememberMeCheck);
        driver.element().click(loginButton);
        return this;
    }

    public PwRecoveryPage forgetPassword() {
        driver.element().click(forgotPassword);
        return new PwRecoveryPage(driver);
    }

    public LoginPage clickEyeIcon() {
        driver.element().click(eyeIcon);
        return this;
    }

    //validations
    public HomePage isSuccessfulLogin() {
        driver.verification().Equals(driver.element().getCurrentUrl(), PropertyReader.getProperty("baseUrlWeb")+"/", "This is not HomePage URL after login");
        return new HomePage(driver);
    }

    public LoginPage isNotSuccessfulLogin() {
        driver.verification().Equals(driver.element().getCurrentUrl(), driver.element().getCurrentUrl(), "This is not LoginPage URL after login failure");
        return new LoginPage(driver);
    }

    public LoginPage isEnableEyeIconWorking() {
        driver.verification().Equals(driver.element().getDomProperty(passwordInput, "type"), "text", "Eye icon is not working");
        return new LoginPage(driver);
    }

    public LoginPage isDisableEyeIconWorking() {
        driver.verification().Equals(driver.element().getDomProperty(passwordInput, "type"), "password", "Eye icon is not working");
        return new LoginPage(driver);
    }
}
