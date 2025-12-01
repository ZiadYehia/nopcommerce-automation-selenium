package com.nopcommerce.ui.user.pages;

import com.nopcommerce.framework.drivers.GUIDriver;
import com.nopcommerce.framework.utils.dataReader.PropertyReader;
import com.nopcommerce.ui.user.BasePage;
import com.nopcommerce.ui.user.common.components.FooterComponent;
import com.nopcommerce.ui.user.common.components.HeaderComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ProductPage extends BasePage {

    // variables
    private String productUrl;
    private GUIDriver driver;
    private HeaderComponent header;
    private FooterComponent footer;
    private int productCount;

    // locators
    By productCountInput = By.cssSelector("[class*=\"qty-input\"]");
    By addToCartButton = By.cssSelector("[id*=\"add-to-cart-button\"]");


    // constructor
    public ProductPage(GUIDriver driver, String product) {
        super(driver);
        this.driver = driver;
        productUrl = product;
        header = new HeaderComponent(driver);
        footer = new FooterComponent(driver);
    }

    // actions
    @Step("Navigate to Product page")
    public ProductPage navigate() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb") + productUrl);
        return this;
    }

    @Step("set product count to {count}")
    public ProductPage setProductCount(int count) {
        this.productCount = count;
        return this;
    }

    @Step("put product count to input field")
    public ProductPage inputProductCount() {
        driver.element().type(productCountInput, String.valueOf(productCount));
        return this;
    }

    @Step("Click on Add to Cart button")
    public ProductPage clickAddToCartButton() {
        driver.element().click(addToCartButton);
        header.closeNotificationBar();
        return this;
    }


    @Step("Click on Go to Cart button from the header")
    public AddToCartPage clickGotoCartButton() {
        header.clickGoToCartButton();
        return new AddToCartPage(driver);
    }


    // validations

    @Step("Validate Product page is loaded")
    public ProductPage isProductPageLoaded(){
        driver.verification().Equals(driver.element().getCurrentUrl(), productUrl, "Product page is not loaded correctly");
        return this;
    }

    @Step("Validate product count is correct")
    public ProductPage isProductCountCorrect(){
        driver.verification().Equals(driver.element().getDomProperty(productCountInput, "value"), String.valueOf(productCount), "Product count is not correct");
        return this;
    }

    @Step("Validate product added to cart successfully")
    public ProductPage isProductAddedToCartSuccessfully(){
        header.isNotificationVisible();
        return this;
    }



}
