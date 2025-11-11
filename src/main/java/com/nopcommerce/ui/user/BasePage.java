package com.nopcommerce.ui.user;

import com.nopcommerce.framework.drivers.GUIDriver;

public class BasePage {

    protected final GUIDriver driver;
    protected int cartQty = 0;
    protected int wishListQty = 0;
    protected BasePage(GUIDriver driver){
        this.driver=driver;
    }
}
