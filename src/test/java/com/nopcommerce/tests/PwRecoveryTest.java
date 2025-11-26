package com.nopcommerce.tests;

import com.nopcommerce.defaultconditions.BaseTest;
import com.nopcommerce.framework.drivers.GUIDriver;
import com.nopcommerce.framework.utils.dataReader.JsonReader;
import com.nopcommerce.ui.user.pages.PwRecoveryPage;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("User Tests")
@Feature("Registration Feature")
@Owner("Ziad Yehia")
public class PwRecoveryTest extends BaseTest {

    //variables

    //Test cases

    @Test(groups = {"pw_recovery", "positive"}, priority = 1)
    @Story("User Forgot Password with Registered Email")
    @Description("Validate that user can recover his password using registered email")
    @Severity(SeverityLevel.BLOCKER)
    public void validPwRecovery(){
        new PwRecoveryPage(driver)
                .navigate()
                .isPwRecoveryPageAppear()
                .recoverPassword(testData.getJsonData("valid[0].email"))
                .isPwRecoverySuccessMsgAppear();
    }

    @Test(groups = {"pw_recovery", "negative"})
    @Story("User Forgot Password with Non-Registered Email")
    @Description("Validate that user can recover his password using non-registered email")
    @Severity(SeverityLevel.BLOCKER)
    public void inValidPwRecovery_NonRegistered(){
        new PwRecoveryPage(driver)
                .navigate()
                .isPwRecoveryPageAppear()
                .recoverPassword(testData.getJsonData("invalid[1].email"))
                .isEmailNotRegistered();
    }

    @Test(groups = {"pw_recovery", "negative"})
    @Story("User Forgot Password with invalid Email")
    @Description("Validate that user can recover his password using invalid email format")
    @Severity(SeverityLevel.BLOCKER)
    public void inValidPwRecovery_EmailFormat(){
        new PwRecoveryPage(driver)
                .navigate()
                .isPwRecoveryPageAppear()
                .recoverPassword(testData.getJsonData("invalid[3].email"))
                .isPwRecoveryErrorMsgAppear();
    }

    @Test(groups = {"pw_recovery", "negative"})
    @Story("User Forgot Password with Empty Email")
    @Description("Validate that user can recover his password using Empty email")
    @Severity(SeverityLevel.BLOCKER)
    public void inValidPwRecovery_EmptyEmail(){
        new PwRecoveryPage(driver)
                .navigate()
                .isPwRecoveryPageAppear()
                .recoverPassword(testData.getJsonData("invalid[0].email"))
                .isPwRecoveryErrorMsgAppear();
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
