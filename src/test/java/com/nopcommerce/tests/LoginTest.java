package com.nopcommerce.tests;

import com.nopcommerce.defaultconditions.BaseTest;
import com.nopcommerce.framework.drivers.GUIDriver;
import com.nopcommerce.framework.utils.dataReader.JsonReader;
import com.nopcommerce.ui.user.pages.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    //variables


    //Test cases
    @Test(groups = {"login", "smoke", "regression", "positive"}, priority = 1)
    @Story("User login with valid data")
    @Description("Validate that user can login with valid credentials")
    @Severity(SeverityLevel.BLOCKER)
    public void validLogin() {
        new LoginPage(driver)
                .navigate()
                .login(testData.getJsonData("valid[0].email"),
                        testData.getJsonData("valid[0].password"))
                .isSuccessfulLogin();
    }
    @Test(groups = {"login", "smoke", "regression", "negative"}, priority = 1)
    @Story("User login with empty data")
    @Description("Validate that user can't login with empty inputs")
    @Severity(SeverityLevel.BLOCKER)
    public void invalidLoginEmpty() {
        new LoginPage(driver)
                .navigate()
                .login(testData.getJsonData("invalid[0].email"),
                        testData.getJsonData("invalid[0].password"))
                .isNotSuccessfulLogin();
    }

    //Configurations
    @BeforeClass(alwaysRun = true)
    protected void preCondition() {
        testData = new JsonReader("login-data");
    }

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        driver = new GUIDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quitDriver();
    }
}
