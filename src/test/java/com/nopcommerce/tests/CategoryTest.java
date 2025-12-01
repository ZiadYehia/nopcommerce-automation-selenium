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

public class CategoryTest extends BaseTest {

    //variables

    //Test cases
    @Test(groups = {"categorypage", "smoke", "regression", "positive"})
    @Story("User flow in Category Page")
    @Description("Validate that User can add product to cart from Category Page")
    @Severity(SeverityLevel.BLOCKER)
    public void addToCartFromCategoryPage(){
        new CategoryPage(driver, categoryOptionName)
                .navigate()
                .addRandomItemToCart();
    }

    @Test(groups = {"categorypage", "smoke", "regression", "positive"})
    @Story("User flow in Category Page")
    @Description("Validate that User can add multiple products to cart from Category Page")
    @Severity(SeverityLevel.BLOCKER)
    public void addManyToCartFromCategoryPage(){
        new CategoryPage(driver, categoryOptionName)
                .navigate()
                .addMultipleRandomItemsToCart(5)
                .isCartQtyUpdatedInHeader();
    }

    @Test(groups = {"categorypage", "smoke", "regression", "positive"})
    @Story("User flow in Category Page")
    @Description("Validate that User can add product to Wishlist from Category Page")
    @Severity(SeverityLevel.BLOCKER)
    public void addToWishlistFromCategoryPage(){
        new CategoryPage(driver, categoryOptionName)
                .navigate()
                .addRandomItemToWishlist();
    }

    @Test(groups = {"categorypage", "smoke", "regression", "positive"})
    @Story("User flow in Category Page")
    @Description("Validate that User can add multiple products to Wishlist from Category Page")
    @Severity(SeverityLevel.BLOCKER)
    public void addManyToWishlistFromCategoryPage(){
        new CategoryPage(driver, categoryOptionName)
                .navigate()
                .addMultipleRandomItemsToWishlist(5)
                .isWishlistQtyUpdatedInHeader();
    }

    @Test(groups = {"categorypage", "smoke", "regression", "positive"})
    @Story("User flow in Category Page")
    @Description("Validate that User can navigate to product from Category Page")
    @Severity(SeverityLevel.BLOCKER)
    public void navigateToProductFromCategoryPage(){
        new CategoryPage(driver, categoryOptionName)
                .navigate()
                .isNavigatedToProductPageByTitle();
    }

    @Test(groups = {"categorypage", "smoke", "regression", "positive"})
    @Story("User flow in Category Page")
    @Description("Validate that User can Compare between multi products from Category Page")
    @Severity(SeverityLevel.BLOCKER)
    public void addManyToCompareFromCategoryPage(){
        new CategoryPage(driver, categoryOptionName)
                .navigate()
                .addMultipleRandomItemsToCompare(3);
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
