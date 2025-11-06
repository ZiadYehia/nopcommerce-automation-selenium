package com.nopcommerce.tests;

import com.nopcommerce.defaultconditions.BaseTest;
import com.nopcommerce.framework.drivers.GUIDriver;
import com.nopcommerce.framework.utils.dataReader.JsonReader;
import com.nopcommerce.ui.user.pages.RegisterPage;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


@Epic("User Tests")
@Feature("Registration Feature")
@Owner("Ziad Yehia")
public class RegSuccessTest extends BaseTest {
    //variables
    private final String timeStamp = String.valueOf(System.currentTimeMillis());
    //Test cases
    @Test(groups = {"regression", "smoke"}, priority = 1)
    @Description("Continue User Registration Success Flow")
    @Story("User Registration")
    @Severity(SeverityLevel.CRITICAL)
    public void continueTC() {
        new RegisterPage(driver)
                .navigate()
                .register(testData.getJsonData("valid[0].firstName"),
                        testData.getJsonData("valid[0].lastName"),
                        testData.getJsonData("valid[0].emailPrefix") + timeStamp + "@gmail.com",
                        testData.getJsonData("valid[0].company"),
                        testData.getJsonData("valid[0].password"))
                .isRegistrationCompleted()
                .continueReg()
                .isHomePageAppear();

        JsonReader loginData = new JsonReader("login-data");
        loginData.setJsonData("valid[0].email", testData.getJsonData("valid[0].emailPrefix") + timeStamp + "@gmail.com");
        loginData.setJsonData("valid[0].password", testData.getJsonData("valid[0].password"));
    }

    //Configurations
    @BeforeClass(alwaysRun = true)
    protected void preCondition() {
        testData = new JsonReader("register-data");
    }

    @BeforeMethod
    public void setup() {
        driver = new GUIDriver();
    }

    @AfterMethod
    public void tearDown() {
        driver.quitDriver();
    }

}
