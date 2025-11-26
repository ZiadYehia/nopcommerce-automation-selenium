package com.nopcommerce.ui.user.pages;

import com.nopcommerce.framework.drivers.GUIDriver;
import com.nopcommerce.framework.utils.actions.ElementActions;
import com.nopcommerce.framework.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class PwRecoveryPage {

    //variables
    private final GUIDriver driver;
    private final String endpoint = "/passwordrecovery";
    //locators
    By pwRecoveryTitle = By.cssSelector("[class=\"page-title\"]");
    By pwRecoveryMsg = By.cssSelector("[class=\"tooltip\"]");
    By emailInput = By.cssSelector("[id=\"Email\"]");
    By recoverBtn = By.cssSelector("[name=\"send-email\"]");
    By pwRecoveryErrorMsg = By.cssSelector("[id=\"Email-error\"]");
    By pwRecoverySuccessMsg = By.cssSelector("[class=\"content\"]");
    By notificationBar = By.cssSelector("[class=\"bar-notification error\"]");
    //constructor
    public PwRecoveryPage(GUIDriver driver) {
        this.driver = driver;
    }

    //actions
    @Step("Navigation to Password Recovery Page")
    public PwRecoveryPage navigate(){
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb")+endpoint);
        return this;
    }

    @Step("Clear Email Field in Password Recovery Page")
    public PwRecoveryPage clearEmailField(){
        driver.element().clear(emailInput);
        return this;
    }

    @Step("Recover Password with Email: {email}")
    public PwRecoveryPage recoverPassword(String email){
        driver.element().type(emailInput,email);
        driver.element().click(recoverBtn);
        return this;
    }



    //validations
    @Step("Verify that Password Recovery Page is displayed")
    public PwRecoveryPage isPwRecoveryPageAppear() {
        driver.verification().assertPageUrl(PropertyReader.getProperty("baseUrlWeb") + endpoint);
        driver.verification().isElementVisible(pwRecoveryTitle);
        driver.verification().isElementVisible(pwRecoveryMsg);
        return this;
    }
    //check error message for invalid email or empty email
    @Step("Verify that Password Recovery Error Message is Displayed")
    public PwRecoveryPage isPwRecoveryErrorMsgAppear() {
        driver.verification().isElementVisible(pwRecoveryErrorMsg);
        return this;
    }

    //check error message for not registered email
    @Step("Verify that Email Not Registered Notification is Displayed")
    public PwRecoveryPage isEmailNotRegistered(){
        driver.verification().isElementVisible(notificationBar);
        return this;
    }

    //check success message for registered email
    @Step("Verify that Password Recovery Success Message is Displayed")
    public PwRecoveryPage isPwRecoverySuccessMsgAppear() {
        driver.verification().isElementVisible(pwRecoverySuccessMsg);
        return this;
    }



}
