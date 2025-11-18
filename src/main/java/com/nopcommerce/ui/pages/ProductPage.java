package com.nopcommerce.ui.pages;

import com.nopcommerce.framework.drivers.GUIDriver;
import com.nopcommerce.ui.BasePage;

public class ProductPage extends BasePage {

    // variables
    private String productUrl;
    // locators

    // constructor
    public ProductPage(GUIDriver driver, String product){
        super(driver);
        productUrl = product;
    }

    // actions


    // validations
}
