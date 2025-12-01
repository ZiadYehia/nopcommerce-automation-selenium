package com.nopcommerce.ui.user.pages;

import com.nopcommerce.framework.drivers.GUIDriver;
import com.nopcommerce.framework.utils.dataReader.PropertyReader;
import com.nopcommerce.ui.user.BasePage;
import com.nopcommerce.ui.user.common.components.FooterComponent;
import com.nopcommerce.ui.user.common.components.HeaderComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.Random;

public class CategoryPage extends BasePage {

    // variables

    private final GUIDriver driver;
    private int itemCount;
    private int itemIndex;
    private HeaderComponent header;
    private FooterComponent footer;

    // locators
    private By itemsInPage(){

        return By.xpath("//div[@class='item-box']");
    }
    private By itemSelected(){
        return By.xpath("(//div[@class='item-box'])["+ getRandomItemIndex()+"]");
    }
    private By addToCartButtonSelected(){
        return By.xpath("(//button[@class='button-2 product-box-add-to-cart-button'])["+getRandomItemIndex()+"]");
    }
    private By compareButtonSelected(){
        return By.xpath("(//button[@class=\"button-2 add-to-compare-list-button\"])["+getRandomItemIndex()+"]");
    }
    private By addToWishlistButtonSelected(){
        return By.xpath("(//button[@class=\"button-2 add-to-wishlist-button\"])["+getRandomItemIndex()+"]");
    }
    private By titleByName(){
        return By.xpath("(//div[@class='item-box'])["+ getRandomItemIndex() +"]//h2[@class='product-title']/a");
    }
    private int getItemCount(){
        return itemCount = driver.element().findElements(itemsInPage()).size();
    }
    private int getRandomItemIndex(){
        Random random = new Random();
        return itemIndex = random.nextInt(getItemCount()) + 1;
    }
    // constructor
    public CategoryPage(GUIDriver driver, String category){
        super(driver);
        this.driver = driver;
        categoryUrl = category;
        header = new HeaderComponent(driver);
        footer = new FooterComponent(driver);

    }

    // actions
    @Step("Navigation to Category Page")
    public CategoryPage navigate() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb")+categoryUrl);
        return this;
    }

    @Step("Add random item to Cart from Category Page")
    public BasePage addRandomItemToCart(){
        String predictedEndpoint = driver.element().getDomProperty(titleByName(), "href");
        driver.element().click(addToCartButtonSelected());
        if (driver.verification().isElementVisibleBoolean(header.getCartNotification())) {
            cartQty++;
            return this;
        } else {
            return new ProductPage(driver, predictedEndpoint);
        }
    }
    // add multiple items to cart
    @Step("Add multiple random items to Cart from Category Page")
    public CategoryPage addMultipleRandomItemsToCart(int nItems){
        for (int i = 0; i < nItems; i++) {
            addRandomItemToCart();
        }
        return this;
    }

    @Step("Add random item to Compare from Category Page")
    public CategoryPage addRandomItemToCompare(){
        driver.element().click(compareButtonSelected());
        return this;
    }

    @Step("Add multiple random items to Wishlist from Category Page")
    public CategoryPage addMultipleRandomItemsToCompare(int nItems){
        for (int i = 0; i < nItems; i++) {
            addRandomItemToCompare();
        }
        return this;
    }

    @Step("Add random item to Wishlist from Category Page")
    public BasePage addRandomItemToWishlist(){
        String predictedEndpoint = driver.element().getDomProperty(titleByName(), "href");
        driver.element().click(addToWishlistButtonSelected());
        if (driver.verification().isElementVisibleBoolean(header.getWishlistNotification())) {
            wishListQty++;
            return this;
        } else {
            return new ProductPage(driver, predictedEndpoint);
        }
    }

    @Step("Add multiple random items to Wishlist from Category Page")
    public CategoryPage addMultipleRandomItemsToWishlist(int nItems){
        for (int i = 0; i < nItems; i++) {
            addRandomItemToWishlist();
        }
        return this;
    }

    @Step("Navigate to Product Page by clicking on random item title from Category Page")
    public ProductPage navigateToProductPageByTitle(By titleByName){
        String predictedEndpoint = driver.element().getDomProperty(titleByName, "href");
        driver.element().click(titleByName);
        return new ProductPage(driver, predictedEndpoint);
    }
    // validations

    @Step("Validate navigation to Product Page by clicking on random item title from Category Page")
    public ProductPage isNavigatedToProductPageByTitle(){
        By titleByName = titleByName();
        String predictedEndpoint = driver.element().getDomProperty(titleByName, "href");
        navigateToProductPageByTitle(titleByName);
        driver.verification().Equals(driver.browser().getCurrentUrl(), predictedEndpoint,
                "Current URL matches the predicted URL for the product page.");
        return new ProductPage(driver, predictedEndpoint);
    }

    @Step("Validate that random item is added to Cart from Category Page")
    public BasePage isRandomItemAddedToCart(){
        BasePage result = addRandomItemToCart();
        if (result instanceof CategoryPage) {
            header.isNotificationVisible();
            header.isShoppingCartQtyCorrect(Integer.toString(cartQty));
        } else {
            header.isShoppingCartQtyCorrect(Integer.toString(cartQty));
        }
        return result;
    }

    @Step("Validate that random item is added to Compare from Category Page")
    public CategoryPage isRandomItemAddedToCompare(){
        addRandomItemToCompare();
        header.isNotificationVisible();
        return this;
    }

    @Step("Validate that Wishlist quantity is updated in header after adding random item to Wishlist from Category Page")
    public CategoryPage isWishlistQtyUpdatedInHeader(){
        driver.verification().Equals(header.getWishlistQty().replace("(","").replace(")","")
                ,Integer.toString(wishListQty)
                , "Wishlist quantity in header doesn't match expected quantity after adding item to Wishlist.");
        return this;
    }


    @Step("Validate that cart quantity is updated in header after adding random item to Cart from Category Page")
    public CategoryPage isCartQtyUpdatedInHeader(){
        driver.verification().Equals(header.getShoppingCartQty().replace("(","").replace(")","")
                ,Integer.toString(cartQty)
                , "Cart quantity in header doesn't match expected quantity after adding item to cart.");
        return this;
    }

//    @Step("Validate random item is added to Cart from Category Page")
//    public BasePage isRandomItemAddedToCart(){
//        String predictedEndpoint = driver.element().getDomProperty(titleByName(), "href");
//        BasePage returnPage = addRandomItemToCart();
//        if (returnPage instanceof CategoryPage) {
//            driver.verification().Equals(Integer.toString(cartQty),
//                    driver.element().getText(header.getShoppingCartQty()),
//                    "Cart quantity in header matches expected quantity after adding item to cart.");
//            return returnPage;
//        } else {
//            driver.verification().Equals(driver.browser().getCurrentUrl(), predictedEndpoint,
//                    "Current URL matches the predicted URL for the product page.");
//            return new ProductPage(driver, predictedEndpoint);
//        }
//    }
}
