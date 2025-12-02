package com.nopcommerce.tests;

import com.nopcommerce.defaultconditions.BaseTest;
import com.nopcommerce.framework.drivers.GUIDriver;
import com.nopcommerce.framework.utils.dataReader.JsonReader;
import com.nopcommerce.ui.user.pages.CategoryPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddToCartTest extends BaseTest {

    //variables
    private String discountCode = "ZiadYehia";
    //Test cases
    @Test(groups = {"categorypage", "smoke", "regression", "positive"})
    @Story("User flow in AddToCart Page")
    @Description("Validate that Cart is empty after removing all products from cart")
    @Severity(SeverityLevel.BLOCKER)
    public void emptyCartAfterRemovingProducts(){
        new CategoryPage(driver, categoryOptionName)
                .navigate()
                .addMultipleRandomItemsToCart(3)
                .isCartQtyUpdatedInHeader()
                .isNavigatedToProductPageByTitle()
                .clickGotoCartButton()
                .removeAllProductsFromCart()
                .isCartEmpty();
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
