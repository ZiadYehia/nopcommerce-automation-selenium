package com.nopcommerce.ui.user.pages;

import com.nopcommerce.framework.drivers.GUIDriver;
import com.nopcommerce.framework.utils.dataReader.PropertyReader;
import com.nopcommerce.ui.user.BasePage;
import com.nopcommerce.ui.user.common.components.FooterComponent;
import com.nopcommerce.ui.user.common.components.HeaderComponent;
import io.qameta.allure.Step;
import org.apache.commons.math3.analysis.function.Add;
import org.openqa.selenium.By;

public class AddToCartPage extends BasePage {
    //variables
    private final GUIDriver driver;
    private HeaderComponent header;
    private FooterComponent footer;
    private int index;
    //locators
    private By itemUnitPrice(){
        return By.xpath("(//span[@class=\"product-unit-price\"])["+index+"]");
    }
    private By itemTotalPrice(){
        return By.xpath("(//span[@class=\"product-subtotal\"])["+index+"]");
    }
    private By itemQtyInCart(){
        return By.xpath("(//input[@aria-label=\"Qty.\"])["+index+"]");
    }
    private By itemRemoveCheckbox(){
        return By.xpath("(//button[@class=\"remove-btn\"])["+index+"]");
    }
    private By itemUpButton(){
        return By.xpath("(//div[@class=\"quantity up\"])["+index+"]");
    }
    private By itemDownButton(){
        return By.xpath("(//div[@class=\"quantity down\"])["+index+"]");
    }
    private By itemsCountInCart = By.cssSelector("table[class=\"cart\"] tbody tr");

    private By giftWrapDropdown = By.cssSelector("//select[@id='checkout_attribute_1']");
    private By termsCheckbox = By.cssSelector("[id=\"termsofservice\"]");
    private By checkoutButton = By.cssSelector("[id=\"checkout\"]");
    private By discountCodeInput = By.cssSelector("[id=\"discountcouponcode\"]");
    private By applyDiscountButton = By.cssSelector("[id=\"applydiscountcouponcode\"]");
    private By discountSuccessMessage = By.cssSelector("[class=\"message-success\"]");
    private By subtotalPrice = By.xpath("(//span[@class=\"value-summary\"])[1]");
    private By taxPrice = By.xpath("(//span[@class=\"value-summary\"])[2]");
    private By dicountPrice = By.xpath("(//span[@class=\"value-summary\"])[3]");
    private By emptyCartMessage = By.cssSelector("[class=\"no-data\"]");
    //constructor
    public AddToCartPage(GUIDriver driver){
        super(driver);
        this.driver = driver;
        header = new HeaderComponent(driver);
        footer = new FooterComponent(driver);
        this.index=1;
    }

    //actions

    @Step("Set index to {index}")
    public AddToCartPage setIndex(int index){
        this.index = index;
        return this;
    }

    @Step("Click on Terms of Service checkbox")
    public AddToCartPage clickTermsCheckbox(){
        driver.element().click(termsCheckbox);
        return this;
    }

    @Step("Click on Checkout button")
    public CheckoutPage clickCheckoutButton(){
        driver.element().click(checkoutButton);
        return new CheckoutPage(driver);
    }

    @Step("Apply discount code {code}")
    public AddToCartPage applyDiscountCode(String code){
        driver.element().type(discountCodeInput, code);
        driver.element().click(applyDiscountButton);
        return this;
    }

    @Step("Remove item at index {index} from cart")
    public AddToCartPage removeItemFromCart(int index){
        setIndex(index);
        driver.element().click(itemRemoveCheckbox());
        return this;
    }

    @Step("Increase quantity of item at index {index} in cart")
    public AddToCartPage increaseItemQuantity(int index){
        setIndex(index);
        driver.element().click(itemUpButton());
        return this;
    }

    @Step("Decrease quantity of item at index {index} in cart")
    public AddToCartPage decreaseItemQuantity(int index){
        setIndex(index);
        driver.element().click(itemDownButton());
        return this;
    }

    @Step("Get unit price of item at index {index}")
    public AddToCartPage getItemUnitPrice(int index){
        setIndex(index);
        driver.element().getText(itemUnitPrice());
        return this;
    }

    @Step("Get total price of item at index {index}")
    public AddToCartPage getItemTotalPrice(int index){
        setIndex(index);
        driver.element().getText(itemTotalPrice());
        return this;
    }

    @Step("Get quantity of item at index {index} in cart")
    public AddToCartPage getItemQuantityInCart(int index){
        setIndex(index);
        driver.element().getDomProperty(itemQtyInCart(), "value");
        return this;
    }

    @Step("Select gift wrap option {option}")
    public AddToCartPage selectGiftWrapOption(String option){
        driver.element().selectFromDropdown_Select(giftWrapDropdown, option);
        return this;
    }

    @Step("Set quantity of item at index {index} in cart to {quantity}")
    public AddToCartPage setItemQuantityInCart(int index, int quantity){
        setIndex(index);
        driver.element().type(itemQtyInCart(), String.valueOf(quantity));
        return this;
    }

    @Step("Remove all products from cart")
    public AddToCartPage removeAllProductsFromCart(){
        int itemsCount = driver.element().findElements(itemsCountInCart).size();
        for (int i = itemsCount; i >=1 ; i--) {
            removeItemFromCart(i);
        }
        return this;
    }

    //validations

    @Step("Validate item total price at index {index} is correct")
    public AddToCartPage isItemTotalPriceCorrect(int index){
        setIndex(index);
        String unitPriceText = driver.element().getText(itemUnitPrice()).replace("$", "");
        String totalPriceText = driver.element().getText(itemTotalPrice()).replace("$", "");
        String qtyText = driver.element().getDomProperty(itemQtyInCart(), "value");

        double unitPrice = Double.parseDouble(unitPriceText);
        double totalPrice = Double.parseDouble(totalPriceText);
        int quantity = Integer.parseInt(qtyText);

        driver.verification().Equals(String.valueOf(totalPrice), String.valueOf(unitPrice * quantity), "Item total price is not correct");
        return this;
    }

    @Step("Validate item quantity at index {index} in cart is {expectedQuantity}")
    public AddToCartPage isItemQuantityInCartCorrect(int index, int expectedQuantity){
        setIndex(index);
        String actualQuantity = driver.element().getDomProperty(itemQtyInCart(), "value");
        driver.verification().Equals(String.valueOf(actualQuantity), String.valueOf(expectedQuantity), "Item quantity in cart is not correct");
        return this;
    }

    @Step("Validate navigation to Checkout Page")
    public CheckoutPage isNavigatedToCheckoutPage(){
        driver.verification().Equals(driver.element().getCurrentUrl(), PropertyReader.getProperty("baseUrlWeb")+"/checkout", "Not navigated to Checkout Page");
        return new CheckoutPage(driver);
    }

    @Step("Validate the discount code is applied successfully")
    public AddToCartPage isDiscountCodeAppliedSuccessfully(){
        driver.verification().isElementVisible(discountSuccessMessage);
        return this;
    }

    @Step("Validate discount is applied correctly to total price")
    public AddToCartPage isDiscountAppliedCorrectly(double expectedDiscountAmount){
        String actualDiscountAmount = driver.element().getText(dicountPrice).replace("$", "").replace("(","").replace(")","");
        driver.verification().Equals(String.valueOf(actualDiscountAmount), String.valueOf(expectedDiscountAmount), "Discount amount is not correct");
        return this;

    }

    @Step("Validate cart is empty")
    public AddToCartPage isCartEmpty(){
        driver.verification().isElementVisible(emptyCartMessage);
        return this;
    }

}
