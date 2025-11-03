package com.nopcommerce.tests;

import com.nopcommerce.framework.drivers.GUIDriver;
import com.nopcommerce.ui.user.pages.RegisterPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class RegSuccessTest {
    //variables
    GUIDriver driver;
    String successfulRegMsg = "Your registration completed";


    //Test cases
    @Test
    public void continueTC() {
        new RegisterPage(driver)
                .register("ziad", "yehia", "ziadTest22@gmail.com", "TestCompany", "P@ssw0rd")
                .isRegistrationCompleted()
                .continueReg()
                .isHomePageAppear();
    }

    //Configurations
    @BeforeMethod
    public void setup() {
        driver = new GUIDriver();
    }

    @AfterMethod
    public void tearDown() {
        driver.quitDriver();
    }

}
