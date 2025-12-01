package com.nopcommerce.tests;

import com.nopcommerce.defaultconditions.BaseTest;
import com.nopcommerce.framework.drivers.GUIDriver;
import com.nopcommerce.framework.utils.dataReader.JsonReader;
import com.nopcommerce.ui.user.pages.CategoryPage;
import com.nopcommerce.ui.user.pages.ProductPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductTest extends BaseTest {

    //variables


    //Test cases

    @Test(groups = {"productpage", "smoke", "regression", "positive"})
    @Story("User flow in Category Page")
    @Description("Validate that User can add number of products and go to cart from Product Page")
    @Severity(SeverityLevel.BLOCKER)
    public void addproduct(){
        new CategoryPage(driver, categoryOptionName)
                .navigate()
                .isNavigatedToProductPageByTitle()
                .isProductPageLoaded()
                .setProductCount(4)
                .inputProductCount()
                .isProductCountCorrect()
                .clickAddToCartButton()
                .isProductAddedToCartSuccessfully()
                .clickGotoCartButton();
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
