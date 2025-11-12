package com.nopcommerce.tests;

import com.nopcommerce.defaultconditions.BaseTest;
import com.nopcommerce.framework.drivers.GUIDriver;
import com.nopcommerce.framework.utils.TimeManager;
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
public class RegisterTest extends BaseTest {
    //variables
    String timeStamp = TimeManager.getSimpleTimestamp();


    //Test cases
    @Test(groups = {"registration", "smoke", "regression", "positive"}, priority = 1)
    @Story("User Registration with valid data")
    @Description("Validate that user can register with valid credentials")
    @Severity(SeverityLevel.BLOCKER)
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

    @Test(groups = {"registration", "regression", "negative"}, dependsOnMethods = {"validUserRegistration_1"})
    @Story("User Registration with existing email")
    @Description("Validate that user can't register with already registered email")
    @Severity(SeverityLevel.CRITICAL)
    public void invalidUserRegistration_ExistingEmail() {

        //First, register a user to create an existing email scenario
        new RegisterPage(driver)
                .navigate()
                .register(testData.getJsonData("valid[0].firstName"),
                        testData.getJsonData("valid[0].lastName"),
                        testData.getJsonData("valid[0].emailPrefix") + timeStamp + "@gmail.com",
                        testData.getJsonData("valid[0].company"),
                        testData.getJsonData("valid[0].password"))
                .isRegistrationUserExist()
                .isRegistrationNotCompleted();

    }

    @Test(groups = {"registration", "regression", "negative"})
    @Story("User Registration with empty fields")
    @Description("Validate that User can't register while leaving all fields empty")
    @Severity(SeverityLevel.NORMAL)
    public void invalidUserRegistration_Empty() {
        new RegisterPage(driver)
                .navigate()
                .register(testData.getJsonData("invalid[0].firstName"),
                        testData.getJsonData("invalid[0].lastName"),
                        testData.getJsonData("invalid[0].emailPrefix"),
                        testData.getJsonData("invalid[0].company"),
                        testData.getJsonData("invalid[0].password"))
                .isRegistrationNotCompleted();
    }

    @Test(groups = {"registration", "regression", "negative"})
    @Story("User Registration with missing first name")
    @Severity(SeverityLevel.NORMAL)
    @Description("Validate that User can't Register with a missing first name")
    public void invalidUserRegistration_FN() {


        new RegisterPage(driver)
                .navigate()
                .register(testData.getJsonData("invalid[1].firstName"),
                        testData.getJsonData("invalid[1].lastName"),
                        testData.getJsonData("invalid[1].emailPrefix") + timeStamp + "@gmail.com",
                        testData.getJsonData("invalid[1].company"),
                        testData.getJsonData("invalid[1].password"))
                .isRegistrationNotCompleted();

    }

    @Test(groups = {"registration", "regression", "negative"})
    @Story("User Registration with invalid email format")
    @Severity(SeverityLevel.NORMAL)
    @Description("Validate that User can't Register with invalid email formate (no @)")
    public void invalidUserRegistration_Email() {


        new RegisterPage(driver)
                .navigate()
                .register(testData.getJsonData("invalid[2].firstName"),
                        testData.getJsonData("invalid[2].lastName"),
                        testData.getJsonData("invalid[2].emailPrefix") + timeStamp + "gmail.com",
                        testData.getJsonData("invalid[2].company"),
                        testData.getJsonData("invalid[2].password"))
                .isRegistrationNotCompleted();

    }

    @Test(groups = {"registration", "regression", "negative"})
    @Story("User Registration with invalid email format")
    @Severity(SeverityLevel.NORMAL)
    @Description("Validate that User can't Register with invalid email formate (no domain)")
    public void invalidUserRegistration_Email2() {


        new RegisterPage(driver)
                .navigate()
                .register(testData.getJsonData("invalid[3].firstName"),
                        testData.getJsonData("invalid[3].lastName"),
                        testData.getJsonData("invalid[3].emailPrefix") + timeStamp + "@",
                        testData.getJsonData("invalid[3].company"),
                        testData.getJsonData("invalid[3].password"))
                .isRegistrationNotCompleted();

    }

    @Test(groups = {"registration", "regression", "negative"})
    @Story("User Registration with short password")
    @Severity(SeverityLevel.NORMAL)
    @Description("Validate that User can't Register with too short password")
    public void invalidUserRegistration_ShortPW() {


        new RegisterPage(driver)
                .navigate()
                .register(testData.getJsonData("invalid[4].firstName"),
                        testData.getJsonData("invalid[4].lastName"),
                        testData.getJsonData("invalid[4].emailPrefix") + timeStamp + "@gmail.com",
                        testData.getJsonData("invalid[4].company"),
                        testData.getJsonData("invalid[4].password"))
                .isRegistrationNotCompleted();

    }

    @Test(groups = {"registration", "regression", "negative"})
    @Story("User Registration with no special char in password")
    @Severity(SeverityLevel.NORMAL)
    @Description("Validate that User can't Register with no special char in password")
    public void invalidUserRegistration_NoSpecialPW() {


        new RegisterPage(driver)
                .navigate()
                .register(testData.getJsonData("invalid[5].firstName"),
                        testData.getJsonData("invalid[5].lastName"),
                        testData.getJsonData("invalid[5].emailPrefix") + timeStamp + "@gmail.com",
                        testData.getJsonData("invalid[5].company"),
                        testData.getJsonData("invalid[5].password"))
                .isRegistrationNotCompleted();

    }

    @Test(groups = {"registration", "regression", "negative"})
    @Story("User Registration with no uppercase password")
    @Severity(SeverityLevel.NORMAL)
    @Description("Validate that User can't Register with no uppercase password")
    public void invalidUserRegistration_NoUPCasePW() {


        new RegisterPage(driver)
                .navigate()
                .register(testData.getJsonData("invalid[6].firstName"),
                        testData.getJsonData("invalid[6].lastName"),
                        testData.getJsonData("invalid[6].emailPrefix") + timeStamp + "@gmail.com",
                        testData.getJsonData("invalid[6].company"),
                        testData.getJsonData("invalid[6].password"))
                .isRegistrationNotCompleted();

    }

    @Test(groups = {"registration", "regression", "negative"})
    @Story("User Registration with no company")
    @Severity(SeverityLevel.NORMAL)
    @Description("Validate that User can't Register with no company")
    public void invalidUserRegistration_NoCompany() {


        new RegisterPage(driver)
                .navigate()
                .register(testData.getJsonData("invalid[7].firstName"),
                        testData.getJsonData("invalid[7].lastName"),
                        testData.getJsonData("invalid[7].emailPrefix") + timeStamp + "@gmail.com",
                        testData.getJsonData("invalid[7].company"),
                        testData.getJsonData("invalid[7].password"))
                .isRegistrationNotCompleted();

    }

    @Test(groups = {"registration", "regression", "negative"})
    @Story("User Registration with spaces only")
    @Severity(SeverityLevel.NORMAL)
    @Description("Validate that User can't Register with spaces only")
    public void invalidUserRegistration_SpaceOnly() {


        new RegisterPage(driver)
                .navigate()
                .register(testData.getJsonData("invalid[8].firstName"),
                        testData.getJsonData("invalid[8].lastName"),
                        testData.getJsonData("invalid[8].emailPrefix") + timeStamp + "@gmail.com",
                        testData.getJsonData("invalid[8].company"),
                        testData.getJsonData("invalid[8].password"))
                .isRegistrationNotCompleted();

    }

    @Test(groups = {"registration", "regression", "negative"})
    @Story("User Registration with special char in names")
    @Severity(SeverityLevel.NORMAL)
    @Description("Validate that User can't Register with special char in names")
    public void invalidUserRegistration_SpecialNames() {


        new RegisterPage(driver)
                .navigate()
                .register(testData.getJsonData("invalid[9].firstName"),
                        testData.getJsonData("invalid[9].lastName"),
                        testData.getJsonData("invalid[9].emailPrefix") + timeStamp + "@gmail.com",
                        testData.getJsonData("invalid[9].company"),
                        testData.getJsonData("invalid[9].password"))
                .isRegistrationNotCompleted();

    }

    @Test(groups = {"registration", "regression", "negative"})
    @Story("User Registration with long email")
    @Severity(SeverityLevel.NORMAL)
    @Description("Validate that User can't Register with long email")
    public void invalidUserRegistration_LongEmail() {


        new RegisterPage(driver)
                .navigate()
                .register(testData.getJsonData("invalid[10].firstName"),
                        testData.getJsonData("invalid[10].lastName"),
                        testData.getJsonData("invalid[10].emailPrefix") + timeStamp + "@gmail.com",
                        testData.getJsonData("invalid[10].company"),
                        testData.getJsonData("invalid[10].password"))
                .isRegistrationNotCompleted();

    }


    //Configurations
    @BeforeClass(alwaysRun = true)
    protected void preCondition() {
        testData = new JsonReader("register-data");
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
