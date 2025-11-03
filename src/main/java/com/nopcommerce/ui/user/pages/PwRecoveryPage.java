package com.nopcommerce.ui.user.pages;

import com.nopcommerce.framework.drivers.GUIDriver;
import com.nopcommerce.framework.utils.actions.ElementActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class PwRecoveryPage {

    //variables
    private final GUIDriver driver;
    //locators
    By pwRecoveryTitle = By.cssSelector("[class=\"page-title\"]");
    By pwRecoveryMsg = By.cssSelector("[class=\"tooltip\"]");
    By emailInput = By.cssSelector("[id=\"Email\"]");
    By recoverBtn = By.cssSelector("[name=\"send-email\"]");
    By pwRecoveryErrorMsg = By.cssSelector("[id=\"Email-error\"]");
    By pwRecoverySuccessMsg = By.cssSelector("[class=\"content\"]");

    //constructor
    public PwRecoveryPage(GUIDriver driver) {
        this.driver = driver;
    }

    //actions


    //validations
}
