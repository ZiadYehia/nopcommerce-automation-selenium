package com.nopcommerce.ui.user.pages;

import com.nopcommerce.framework.drivers.GUIDriver;
import com.nopcommerce.ui.user.BasePage;
import com.nopcommerce.ui.user.common.components.FooterComponent;
import com.nopcommerce.ui.user.common.components.HeaderComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CheckoutPage extends BasePage {

    //variables
    private final GUIDriver driver;
    private HeaderComponent header;
    private FooterComponent footer;
    //locators

    //constructor
    public CheckoutPage(GUIDriver driver){
        super(driver);
        this.driver = driver;
        header = new HeaderComponent(driver);
        footer = new FooterComponent(driver);

    }

    //actions





    //validations

}
