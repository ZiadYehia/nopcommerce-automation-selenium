package com.nopcommerce.ui.user;

import com.nopcommerce.framework.drivers.GUIDriver;

public class BasePage {

    protected final GUIDriver driver;
    protected BasePage(GUIDriver driver){
        this.driver=driver;
    }
}
