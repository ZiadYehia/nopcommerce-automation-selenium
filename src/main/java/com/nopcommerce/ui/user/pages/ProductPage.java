package com.nopcommerce.ui.user.pages;

import com.nopcommerce.framework.drivers.GUIDriver;
import com.nopcommerce.ui.user.BasePage;
import org.openqa.selenium.By;

public class ProductPage extends BasePage {

    // variables
    private String productUrl;
    private GUIDriver driver;

    // locators


    // constructor
    public ProductPage(GUIDriver driver, String product){
        super(driver);
        this.driver = driver;
        productUrl = product;
    }

    // actions


    // validations
}
