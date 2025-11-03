package com.nopcommerce.ui.user.pages;

import com.nopcommerce.framework.drivers.GUIDriver;
import org.openqa.selenium.By;

public class RegSuccessPage {

    //variables
    private GUIDriver driver;
    //locators
    private final By continueButton = By.cssSelector("a[class=\"button-1 register-continue-button\"]");

    //constructor
    public RegSuccessPage(GUIDriver driver) {
        this.driver = driver;
    }

    //actions
    public RegSuccessPage continueReg() {
        driver.element().click(continueButton);
        return new RegSuccessPage(driver);
    }

    //validations
    public HomePage isHomePageAppear() {
        driver.validation().assertPageUrl("baseUrlWeb");
        return new HomePage(driver);
    }
}
