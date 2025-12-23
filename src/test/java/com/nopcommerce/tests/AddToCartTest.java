package com.nopcommerce.tests;

import com.nopcommerce.defaultconditions.BaseTest;
import com.nopcommerce.framework.drivers.GUIDriver;
import com.nopcommerce.framework.utils.TimeManager;
import com.nopcommerce.framework.utils.dataReader.JsonReader;
import com.nopcommerce.ui.user.pages.CategoryPage;
import com.nopcommerce.ui.user.pages.LoginPage;
import com.nopcommerce.ui.user.pages.RegisterPage;
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
    String timeStamp = TimeManager.getSimpleTimestamp();

    //Test cases
    @Test(groups = {"categorypage", "smoke", "regression", "positive"})
    @Story("User flow in AddToCart Page")
    @Description("Validate that Cart is empty after removing all products from cart")
    @Severity(SeverityLevel.BLOCKER)
    public void emptyCartAfterRemovingProducts(){
        new CategoryPage(driver, categoryOptionName)
                .navigate()
                .addMultipleRandomItemsToCart(3)
                .isNavigatedToProductPageByTitle()
                .clickGotoCartButton()
                .removeAllProductsFromCart()
                .isCartEmpty();
    }

    @Test(groups = {"categorypage", "smoke", "regression", "positive"})
    @Story("User flow in AddToCart Page")
    @Description("Validate that user can update product quantity in cart")
    @Severity(SeverityLevel.BLOCKER)
    public void updateProductQuantityInCart(){
        new CategoryPage(driver, categoryOptionName)
                .navigate()
                .addMultipleRandomItemsToCart(3)
                .isCartQtyUpdatedInHeader()
                .isNavigatedToProductPageByTitle()
                .clickGotoCartButton()
                .increaseItemQuantity(1);
    }

    @Test(groups = {"categorypage", "smoke", "regression", "positive"})
    @Story("User flow in AddToCart Page")
    @Description("Validate that user checkout process works correctly from cart page")
    @Severity(SeverityLevel.BLOCKER)
    public void checkoutFromCartPage(){
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
        new LoginPage(driver)
                .navigate()
                .login(testData2.getJsonData("valid[0].email"),
                        testData2.getJsonData("valid[0].password"))
                .isSuccessfulLogin();
        new CategoryPage(driver, categoryOptionName)
                .navigate()
                .addMultipleRandomItemsToCart(3)
                .isCartQtyUpdatedInHeader()
                .isNavigatedToProductPageByTitle()
                .clickGotoCartButton()
                .clickTermsCheckbox()
                .clickCheckoutButton();
    }

    @Test(groups = {"categorypage", "smoke", "regression", "positive"})
    @Story("User flow in AddToCart Page")
    @Description("Validate that user can apply discount code in cart page")
    @Severity(SeverityLevel.BLOCKER)
    public void applyDiscountCodeInCartPage(){
        new CategoryPage(driver, categoryOptionName)
                .navigate()
                .addMultipleRandomItemsToCart(3)
                .isCartQtyUpdatedInHeader()
                .isNavigatedToProductPageByTitle()
                .clickGotoCartButton()
                .applyDiscountCode(discountCode)
                .isDiscountCodeAppliedSuccessfully();
    }

    //Configurations
    @BeforeClass(alwaysRun = true)
    protected void preCondition() {
        testData = new JsonReader("register-data");
        testData2 = new JsonReader("login-data");

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
