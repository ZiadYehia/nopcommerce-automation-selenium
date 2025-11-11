// java
package com.nopcommerce.ui.user.pages;

import com.nopcommerce.framework.drivers.GUIDriver;
import com.nopcommerce.ui.user.BasePage;
import com.nopcommerce.ui.user.common.components.FooterComponent;
import com.nopcommerce.ui.user.common.components.HeaderComponent;
import org.openqa.selenium.By;

public class HomePage extends BasePage {
    // variables
    private final GUIDriver driver;
    private HeaderComponent header;
    private FooterComponent footer;
    /**
     * Featured products currently visible on home page:
     * 1. Build your own computer
     * 2. Apple MacBook Pro
     * 3. HTC smartphone
     * 4. $25 Virtual Gift Card
     */
    private String optionName;
    /**
     * Community Poll
     * 1. Excellent
     * 2. Good
     * 3. Poor
     * 4. Very bad
     *
     */
    private int pollOption;

    // static locators
    private By featuredProducts = By.cssSelector("section.product-grid.home-page-product-grid article.product-item"); // returns all featured products (4)
    private By welcomeMsg = By.cssSelector("[class=\"topic-block-title\"]");
    private By voteUpRadio = By.cssSelector("[id=\"pollanswers-" + pollOption + "\"]");
    private By voteButton = By.cssSelector("[id=\"vote-poll-1\"]");
    private By voteResultText = By.cssSelector("[class=\"poll-total-votes\"]");
    private By cardByName = By.xpath("//section[contains(@class,'home-page-product-grid')]//a[normalize-space()='" + optionName + "']/ancestor::article[contains(@class,'product-item')]");
    private By titleByName = By.xpath("//section[contains(@class,'home-page-product-grid')]//a[normalize-space()='" + optionName + "']");
    private By imageByName = By.xpath("//section[contains(@class,'home-page-product-grid')]//a[normalize-space()='" + optionName + "']/ancestor::article//div[contains(@class,'picture')]//img");
    private By priceByName = By.xpath("//section[contains(@class,'home-page-product-grid')]//a[normalize-space()='" + optionName + "']/ancestor::article//div[contains(@class,'prices')]//span[contains(@class,'actual-price') or contains(@class,'price')]");
    private By ratingByName = By.xpath("//section[contains(@class,'home-page-product-grid')]//a[normalize-space()='" + optionName + "']/ancestor::article//div[contains(@class,'rating')]");
    private By addToCartByName = By.xpath("//section[contains(@class,'home-page-product-grid')]//a[normalize-space()='" + optionName + "']/ancestor::article//button[contains(@class,'product-box-add-to-cart-button')]");
    private By wishlistByName = By.xpath("//section[contains(@class,'home-page-product-grid')]//a[normalize-space()='" + optionName + "']/ancestor::article//button[contains(@class,'add-to-wishlist-button')]");
    private By compareByName = By.xpath("//section[contains(@class,'home-page-product-grid')]//a[normalize-space()='" + optionName + "']/ancestor::article//button[contains(@class,'add-to-compare-list-button')]");
    private By descriptionByName = By.xpath("//section[contains(@class,'home-page-product-grid')]//a[normalize-space()='" + optionName + "']/ancestor::article//div[contains(@class,'description')]");


    // constructor
    public HomePage(GUIDriver driver) {
        super(driver);
        this.driver = driver;
        this.header = new HeaderComponent(driver);
        this.optionName = "HTC smartphone";
        this.pollOption = 1;
    }

    // actions
    public HomePage navigate() {
        driver.browser().navigateTo("baseUrlWeb");
        return this;
    }

//    public HomePage setFeaturedOption(String name) {
//        this.optionName = name;
//        return this;
//    }
//
//    public HomePage setPollOption(int option) {
//        this.pollOption = option;
//        return this;
//    }

    public ProductPage openFeaturedProduct() {
        driver.element().click(titleByName);
        return new ProductPage(driver, driver.element().getDomProperty(titleByName, "href"));
    }

    public BasePage clickAddToCart() {
        String previousUrl = driver.browser().getCurrentUrl();
        driver.element().click(addToCartByName);
        if (previousUrl.equalsIgnoreCase(driver.browser().getCurrentUrl())) {
            return this;
        } else {
            return new ProductPage(driver, driver.element().getDomProperty(titleByName, "href"));
        }
    }

    public BasePage clickWishlist() {
        String previousUrl = driver.browser().getCurrentUrl();
        driver.element().click(wishlistByName);
        if (previousUrl.equalsIgnoreCase(driver.browser().getCurrentUrl())) {
            return this;
        } else {
            return new ProductPage(driver, driver.element().getDomProperty(titleByName, "href"));
        }
    }

    public HomePage clickCompare() {
        driver.element().click(compareByName);
        return this;
    }

    public HomePage clickImage() {
        driver.element().click(imageByName);
        return this;
    }

    public HomePage voteUp() {
        driver.element().click(voteUpRadio);
        driver.element().click(voteButton);
        return this;
    }

    public int getFeaturedProductsCount() {
        return driver.element().findElements(featuredProducts).size();
    }

    public String getTitleText() {
        return driver.element().getText(titleByName);
    }

    public String getPriceText() {
        return driver.element().getText(priceByName);
    }

    public String getRatingText() {
        return driver.element().getText(ratingByName);
    }

    public String getWelcomeMessage() {
        return driver.element().getText(welcomeMsg);
    }

    public String getVoteResultText() {
        return driver.element().getText(voteResultText);
    }

    // validations

    public HomePage isHeaderVisible() {
        header.isHeaderComponentVisible();
        return this;
    }

    public HomePage isFooterVisible() {
        footer.isFooterComponentVisible();
        return this;
    }

    public HomePage isWelcomeMessageVisible() {
        driver.verification().isElementVisible(welcomeMsg);
        return this;
    }

    public HomePage isProductDescriptionPresent() {
        driver.verification().isElementVisible(descriptionByName);
        return this;
    }

    public HomePage isAllProductsCounted(){
        String count = String.valueOf(getFeaturedProductsCount());
        driver.verification().Equals(count,"4","Featured Products are not 4 Products");
        return this;
    }

    public HomePage isFeaturedProductsSectionVisible() {
        driver.verification().isElementVisible(featuredProducts);
        return this;
    }
    public HomePage isProductTitleVisible() {
        driver.verification().isElementVisible(titleByName);
        return this;
    }

    public HomePage isProductImageVisible() {
        driver.verification().isElementVisible(imageByName);
        return this;
    }

    public HomePage isProductPriceVisible() {
        driver.verification().isElementVisible(priceByName);
        return this;
    }

    public HomePage isProductRatingVisible() {
        driver.verification().isElementVisible(ratingByName);
        return this;
    }

    public HomePage isAddToCartButtonVisible() {
        driver.verification().isElementVisible(addToCartByName);
        return this;
    }

    public HomePage isWishlistButtonVisible() {
        driver.verification().isElementVisible(wishlistByName);
        return this;
    }

    public HomePage isCompareButtonVisible() {
        driver.verification().isElementVisible(compareByName);
        return this;
    }

    public HomePage isProductDescriptionVisible() {
        driver.verification().isElementVisible(descriptionByName);
        return this;
    }

    public HomePage verifyAllFeaturedProductsElements() {
        isFeaturedProductsSectionVisible();
        isAllProductsCounted();
        isProductTitleVisible();
        isProductImageVisible();
        isProductPriceVisible();
        isProductRatingVisible();
        isAddToCartButtonVisible();
        isWishlistButtonVisible();
        isCompareButtonVisible();
        isProductDescriptionVisible();
        return this;
    }

    public HomePage isPollSectionVisible() {
        driver.verification().isElementVisible(voteButton);
        driver.verification().isElementVisible(voteUpRadio);
        return this;
    }

    public HomePage isVoteResultTextVisible() {
        driver.verification().isElementVisible(voteResultText);
        return this;
    }

    public HomePage verifyPollSection() {
        isPollSectionVisible();
        isVoteResultTextVisible();
        return this;
    }



}
