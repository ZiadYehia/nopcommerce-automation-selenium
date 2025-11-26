package com.nopcommerce.ui.user.common.components;

import com.nopcommerce.framework.drivers.GUIDriver;
import com.nopcommerce.framework.utils.dataReader.PropertyReader;
import com.nopcommerce.ui.user.pages.CategoryPage;
import org.openqa.selenium.By;

import java.util.List;

public class HeaderComponent{

    // variables
    private final GUIDriver driver;
    /**
     * "Computers",
     * "Electronics",
     * "Apparel",
     * "Digital downloads",
     * "Books",
     * "Jewelry",
     * "Gift Cards"
     */
    private String category;
    /**
     * "Desktops",
     * "Notebooks",
     * "Software",
     * "Camera & photo",
     * "Cell phones",
     * "Others",
     * "Shoes",
     * "Clothing",
     * "Accessories",
     * "Digital downloads",
     * "Books",
     * "Jewelry",
     * "Gift Cards"
     */
    private String optionName;

    // locators
    //protected By currencyDropdown = By.cssSelector("[id=\"customerCurrency\"]");
    protected By storeLogo = By.cssSelector("[class=\"header-logo\"] img");
    protected By loginLink = By.cssSelector("[class=\"ico-login\"]");
    protected By logoutLink = By.cssSelector("[class=\"ico-logout\"]");
    protected By registerLink = By.cssSelector("[class=\"ico-register\"]");
    protected By searchInput = By.cssSelector("[id=\"small-searchterms\"]");
    protected By searchButton = By.cssSelector("[type=\"submit\"]");
    protected By shoppingCartLink = By.cssSelector("[class=\"ico-cart\"]");
    protected By shoppingCartQty = By.cssSelector("[class=\"cart-qty\"]");
    protected By cartSuccess = By.xpath(
            "//*[contains(@class,'bar-notification') and contains(@class,'success')]//*[contains(.,'added to your shopping cart')]"
    );
    protected By wishlistSuccess = By.xpath(
            "//p[@class='content']//a[.='wishlist']"
    );
    protected By myAccountLink = By.cssSelector("[class=\"ico-account\"]");
    protected By wishlistLink = By.cssSelector("[class=\"ico-wishlist\"]");
    protected By wishlistQty = By.cssSelector("[class=\"wishlist-qty\"]");
    protected By notificationMsg = By.cssSelector("[id=\"bar-notification\"]");

    // dynamic locator
    By dynamicCategoryMenu() {
        return By.xpath(
                "//div[@class='menu__item menu-dropdown']" +
                        "[.//a[@class='menu__link' and normalize-space()='" + this.category + "']]");
    }
    By dynamicOptionMenu() {
        return By.xpath(
                "//div[@class='menu__item menu-dropdown']" +
                        "[.//a[@class='menu__link' and normalize-space()='" + this.category + "']]" +
                        "//div[@class='menu__list-view']//a[@class='menu__link' and normalize-space()='" + this.optionName + "']"
        );
    }


    // constructor
    public HeaderComponent(GUIDriver driver) {
        this.driver = driver;
    }

    // actions

    public HeaderComponent navigate(){
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb"));
        return this;
    }

//    public HeaderComponent changeCurrency(String currency) {
//        driver.element().selectFromDropdown(currencyDropdown, currency);
//        return this;
//    }

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

    public CategoryPage clickDynamicMenuLink(String category, String optionName) {
        this.category = category;
        this.optionName = optionName;
        driver.element().selectFromDropdown_Normal(dynamicCategoryMenu(), dynamicOptionMenu());
        return new CategoryPage(driver, category);
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

    public By getCartNotification() {
        return cartSuccess;
    }
    public By getWishlistNotification() {
        return wishlistSuccess;
    }
    public By getNotification() {
        return notificationMsg;
    }

    public String getShoppingCartQty() {
        return driver.element().getText(shoppingCartQty);
    }
    public String getWishlistQty() {
        return driver.element().getText(wishlistQty);
    }





    // validations


    public HeaderComponent isHeaderComponentVisible() {
//        driver.verification().isElementVisible(currencyDropdown);
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
        driver.verification().Equals(getShoppingCartQty(), "("+expectedQty+")", "Shopping cart quantity is not "+expectedQty);
        return this;
    }

    public HeaderComponent isWishlistQtyCorrect(String expectedQty) {
        driver.verification().Equals(getWishlistQty(), "("+expectedQty+")", "Wishlist quantity is not "+expectedQty);
        return this;
    }

//    public HeaderComponent isCurrencySelected(String expectedCurrency) {
//        driver.verification().Equals(driver.element().getSelectedOptionFromDropdown(currencyDropdown), expectedCurrency, "Selected currency is not as expected");
//        return this;
//    }

    public HeaderComponent isNotificationVisible(){
        driver.verification().isElementVisible(notificationMsg);
        return this;
    }

    public HeaderComponent isNavigatedToCategoryPage(String category){
        driver.verification().assertPageUrl(PropertyReader.getProperty("baseUrlWeb")+"/"+category.toLowerCase().replace(" & ","-").replace(" ","-"));
        return this;
    }

    public HeaderComponent isNavigatedToOptionPage(String optionName){
        driver.verification().assertPageUrl(PropertyReader.getProperty("baseUrlWeb")+"/"+optionName.toLowerCase().replace(" & ","-").replace(" ","-"));
        return this;
    }

}
