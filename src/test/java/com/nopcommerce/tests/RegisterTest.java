package com.nopcommerce.tests;

import com.nopcommerce.defaultconditions.BaseTest;
import com.nopcommerce.framework.drivers.GUIDriver;
import com.nopcommerce.framework.utils.TimeManager;
import com.nopcommerce.framework.utils.dataReader.JsonReader;
import com.nopcommerce.ui.user.pages.RegisterPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest {
    //variables
    String timeStamp = TimeManager.getSimpleTimestamp();


    //Test cases
    @Test
    public void validUserRegistration_1() {

        new RegisterPage(driver)
                .navigate()
                .register(testData.getJsonData("valid[0].firstName"),
                        testData.getJsonData("valid[0].lastName"),
                        testData.getJsonData("valid[0].emailPrefix") + timeStamp + "@gmail.com",
                        testData.getJsonData("valid[0].company"),
                        testData.getJsonData("valid[0].password"))
                .isRegistrationCompleted();

        JsonReader loginData = new JsonReader("login-data");
        loginData.setJsonData("valid[0].email", testData.getJsonData("valid[0].emailPrefix") + timeStamp + "@gmail.com");
        loginData.setJsonData("valid[0].password", testData.getJsonData("valid[0].password"));
    }

    @Test
    public void invalidUserRegistration_ExistingEmail() {

        //First, register a user to create an existing email scenario
        new RegisterPage(driver)
                .register(testData.getJsonData("valid[0].firstName"),
                        testData.getJsonData("valid[0].lastName"),
                        testData.getJsonData("valid[0].emailPrefix") + timeStamp + "@gmail.com",
                        testData.getJsonData("valid[0].company"),
                        testData.getJsonData("valid[0].password"))
                .isRegistrationUserExist();

    }

    @Test
    public void inValidUserRegistration() {
        new RegisterPage(driver)
                .register("", "", "invalidEmail", "", "123")
                .isRegistrationNotCompleted();
    }

    //Configurations
    @BeforeClass
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
