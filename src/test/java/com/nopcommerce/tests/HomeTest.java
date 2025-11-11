package com.nopcommerce.tests;

import com.nopcommerce.defaultconditions.BaseTest;
import com.nopcommerce.framework.drivers.GUIDriver;
import com.nopcommerce.framework.utils.TimeManager;
import com.nopcommerce.framework.utils.dataReader.JsonReader;
import com.nopcommerce.ui.user.pages.HomePage;
import com.nopcommerce.ui.user.pages.LoginPage;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("User Tests")
@Feature("HomePage Feature")
@Owner("Ziad Yehia")
public class HomeTest extends BaseTest {
    //variables
    String timeStamp = TimeManager.getSimpleTimestamp();


    //Test cases
    @Test(groups = {"homepage", "smoke", "regression", "positive"})
    @Story("User flow in HomePage")
    @Description("Validate that homepage is loaded successfully")
    @Severity(SeverityLevel.BLOCKER)
    public void homePageLoaded() {
        new HomePage(driver)
                .navigate()
                .verifyAllHomePageElementsLoaded();
    }

    @Test(groups = {"homepage", "smoke", "regression", "positive"})
    @Story("User flow in HomePage")
    @Description("Validate that User is navigated to product page upon clicking on image")
    @Severity(SeverityLevel.BLOCKER)
    public void featuredProduct_Image() {
        new HomePage(driver)
                .navigate()
                .isNavigatedToProductPage_Image();
    }
    @Test(groups = {"homepage", "smoke", "regression", "positive"})
    @Story("User flow in HomePage")
    @Description("Validate that User is navigated to product page upon clicking on title")
    @Severity(SeverityLevel.BLOCKER)
    public void featuredProduct_Title() {
        new HomePage(driver)
                .navigate()
                .isNavigatedToProductPage_Title();

    }


    @Test(groups = {"homepage", "smoke", "regression", "positive"})
    @Story("User flow in HomePage")
    @Description("Validate that user can vote on HomePage")
    @Severity(SeverityLevel.BLOCKER)
    public void voteUp() {
        new LoginPage(driver)
                .navigate()
                .login(testData.getJsonData("valid[0].email"),
                        testData.getJsonData("valid[0].password"))
                .isSuccessfulLogin()
                .verifyPollSection();

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
