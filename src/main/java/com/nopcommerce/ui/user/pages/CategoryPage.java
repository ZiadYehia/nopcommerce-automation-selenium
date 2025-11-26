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
    private final String categoryUrl;
    private final GUIDriver driver;
    private int itemCount;
    private int itemIndex;
    private HeaderComponent header;
    private FooterComponent footer;
    // locators
    private By itemsInPage = By.xpath("//div[@class='item-box']");
    private By itemSelected(){
        return By.xpath("(//div[@class='item-box'])["+ itemIndex+"]");
    }
    private By addToCartButtonSelected(){
        return By.xpath("(//button[@class='button-2 product-box-add-to-cart-button'])["+itemIndex+"]");
    }
    private By compareButtonSelected(){
        return By.xpath("(//button[@class=\"button-2 add-to-compare-list-button\"])["+itemIndex+"]");
    }
    private By addToWishlistButtonSelected(){
        return By.xpath("(//button[@class=\"button-2 add-to-wishlist-button\"])["+itemIndex+"]");
    }
    private By titleByName(){
        return By.xpath("(//div[@class='item-box'])["+ itemIndex +"]//h2[@class='product-title']/a");
    }
    // constructor
    public CategoryPage(GUIDriver driver, String category){
        super(driver);
        this.driver = driver;
        categoryUrl = category;
        header = new HeaderComponent(driver);
        footer = new FooterComponent(driver);
        itemCount = driver.element().findElements(itemsInPage).size();
        itemIndex = new Random().nextInt(itemCount);
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

    @Step("Add random item to Compare from Category Page")
    public CategoryPage addRandomItemToCompare(){
        driver.element().click(compareButtonSelected());
        return this;
    }

    @Step("Add random item to Wishlist from Category Page")
    public CategoryPage addRandomItemToWishlist(){
        driver.element().click(addToWishlistButtonSelected());
        return this;
    }

    @Step("Navigate to Product Page by clicking on random item title from Category Page")
    public ProductPage navigateToProductPageByTitle(){
        String predictedEndpoint = driver.element().getDomProperty(titleByName(), "href");
        driver.element().click(titleByName());
        return new ProductPage(driver, predictedEndpoint);
    }
    // validations

    @Step("Validate navigation to Product Page by clicking on random item title from Category Page")
    public ProductPage isNavigatedToProductPageByTitle(){
        String predictedEndpoint = driver.element().getDomProperty(titleByName(), "href");
        navigateToProductPageByTitle();
        driver.verification().Equals(driver.browser().getCurrentUrl(), predictedEndpoint,
                "Current URL matches the predicted URL for the product page.");
        return new ProductPage(driver, predictedEndpoint);
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
