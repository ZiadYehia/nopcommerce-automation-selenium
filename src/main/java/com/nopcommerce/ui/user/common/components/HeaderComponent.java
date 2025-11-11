package com.nopcommerce.ui.user.common.components;

import com.nopcommerce.framework.drivers.GUIDriver;
import com.nopcommerce.framework.utils.dataReader.PropertyReader;
import org.openqa.selenium.By;

public class HeaderComponent{

    // variables
    private final GUIDriver driver;
    private String category;
    private String optionName;

    // locators
    By currencyDropdown = By.cssSelector("[id=\"customerCurrency\"]");
    By storeLogo = By.cssSelector("[class=\"header-logo\"] img");
    By loginLink = By.cssSelector("[class=\"ico-login\"]");
    By logoutLink = By.cssSelector("[class=\"ico-logout\"]");
    By registerLink = By.cssSelector("[class=\"ico-register\"]");
    By dynamicMenuLink = By.cssSelector("//div[@class=\"menu\"] /div["+category+"] //a[.=\""+optionName+"\"]");
    By searchInput = By.cssSelector("[id=\"small-searchterms\"]");
    By searchButton = By.cssSelector("[type=\"submit\"]");
    By shoppingCartLink = By.cssSelector("[class=\"ico-cart\"]");
    By shoppingCartQty = By.cssSelector("[class=\"cart-qty\"]");
    By myAccountLink = By.cssSelector("[class=\"ico-account\"]");
    By wishlistLink = By.cssSelector("[class=\"ico-wishlist\"]");
    By wishlistQty = By.cssSelector("[class=\"wishlist-qty\"]");
    By notificationMsg = By.cssSelector("[id=\"bar-notification\"]");

    // constructor
    public HeaderComponent(GUIDriver driver) {
        this.driver = driver;
    }

    // actions

    public HeaderComponent navigate(){
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb"));
        return this;
    }

    public HeaderComponent changeCurrency(String currency) {
        driver.element().selectFromDropdown(currencyDropdown, currency);
        return this;
    }

    public HeaderComponent clickStoreLogo() {
        driver.element().click(storeLogo);
        return this;
    }

    public HeaderComponent clickLoginLink() {
        driver.element().click(loginLink);
        return this;
    }

    public HeaderComponent clickLogoutLink() {
        driver.element().click(logoutLink);
        return this;
    }

    public HeaderComponent clickRegisterLink() {
        driver.element().click(registerLink);
        return this;
    }

    public HeaderComponent clickDynamicMenuLink(String category, String optionNameName) {
        this.category = category;
        this.optionName = optionNameName;
        driver.element().click(dynamicMenuLink);
        return this;
    }

    public HeaderComponent searchProduct(String productName) {
        driver.element().type(searchInput, productName);
        driver.element().click(searchButton);
        return this;
    }

    public HeaderComponent clickShoppingCartLink() {
        driver.element().click(shoppingCartLink);
        return this;
    }

    public HeaderComponent clickMyAccountLink() {
        driver.element().click(myAccountLink);
        return this;
    }

    public HeaderComponent clickWishlistLink() {
        driver.element().click(wishlistLink);
        return this;
    }

    public String getShoppingCartQty() {
        return driver.element().getText(shoppingCartQty);
    }
    public String getWishlistQty() {
        return driver.element().getText(wishlistQty);
    }





    // validations


    public HeaderComponent isHeaderComponentVisible() {
        driver.verification().isElementVisible(currencyDropdown);
        driver.verification().isElementVisible(storeLogo);
        driver.verification().isElementVisible(registerLink);
        driver.verification().isElementVisible(searchInput);
        driver.verification().isElementVisible(searchButton);
        driver.verification().isElementVisible(shoppingCartLink);
        driver.verification().isElementVisible(wishlistLink);
        return this;
    }

    public HeaderComponent isLogoutLinkVisible() {
        driver.verification().isElementVisible(logoutLink);
        return this;
    }

    public HeaderComponent isLoginLinkVisible() {
        driver.verification().isElementVisible(loginLink);
        return this;
    }

    public HeaderComponent isShoppingCartQtyCorrect(String expectedQty) {
        driver.verification().Equals(driver.element().getText(shoppingCartQty), expectedQty, "Shopping cart quantity is not as expected");
        return this;
    }

    public HeaderComponent isWishlistQtyCorrect(String expectedQty) {
        driver.verification().Equals(driver.element().getText(wishlistQty), expectedQty, "Wishlist quantity is not as expected");
        return this;
    }

    public HeaderComponent isCurrencySelected(String expectedCurrency) {
        driver.verification().Equals(driver.element().getSelectedOptionFromDropdown(currencyDropdown), expectedCurrency, "Selected currency is not as expected");
        return this;
    }

    public HeaderComponent isNotificationVisible(){
        driver.verification().isElementVisible(notificationMsg);
        return this;
    }

}
