package com.nopcommerce.ui.user.pages;

import com.nopcommerce.framework.drivers.GUIDriver;
import com.nopcommerce.framework.utils.dataReader.PropertyReader;
import org.openqa.selenium.By;

import java.io.File;

public class RegSuccessPage {

    //variables
    private GUIDriver driver;
    //locators
    private final By continueButton = By.cssSelector("a[class=\"button-1 register-continue-button\"]");
    private final By homeMainBanner = By.cssSelector("[id=\"main\"]");

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
        driver.validation().assertPageUrl(PropertyReader.getProperty("baseUrlWeb")+ "/");
        driver.verification().isElementVisible(homeMainBanner);
        return new HomePage(driver);
    }
}
