package com.nopcommerce.ui.user.pages;

import com.nopcommerce.framework.drivers.GUIDriver;
import com.nopcommerce.framework.utils.dataReader.PropertyReader;
import com.nopcommerce.ui.user.BasePage;
import com.nopcommerce.ui.user.common.components.FooterComponent;
import com.nopcommerce.ui.user.common.components.HeaderComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Random;

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
    private final List<String> optionNames = List.of("HTC smartphone", "Build your own computer", "Apple MacBook Pro", "$25 Virtual Gift Card");
    private String optionName;
    /**
     * Community Poll
     * 1. Excellent
     * 2. Good
     * 3. Poor
     * 4. Very bad
     *
     */
    private static final List<Integer> pollOptions = List.of(1, 2, 3, 4);
    private int pollOption;
    // static locators
    private By featuredProducts = By.cssSelector("section.product-grid.home-page-product-grid article.product-item");
    ; // returns all featured products (4)
    private By welcomeMsg = By.cssSelector("[class=\"topic-block-title\"]");
    private By voteButton = By.cssSelector("[id=\"vote-poll-1\"]");
    private By voteResultText = By.cssSelector("[class=\"poll-total-votes\"]");

    // dynamic locators
    private By voteUpRadio() {
        return By.cssSelector("[id=\"pollanswers-" + pollOption + "\"]");
    }

    private By cardByName() {
        return By.xpath("//section[contains(@class,'home-page-product-grid')]//a[normalize-space()='" + optionName + "']/ancestor::article[contains(@class,'product-item')]");
    }

    private By titleByName() {
        return By.xpath("//section[contains(@class,'home-page-product-grid')]//a[normalize-space()='" + optionName + "']");
    }

    private By imageByName() {
        return By.xpath("//section[contains(@class,'home-page-product-grid')]//a[normalize-space()='" + optionName + "']/ancestor::article//div[contains(@class,'picture')]//img");
    }

    private By priceByName() {
        return By.xpath("//section[contains(@class,'home-page-product-grid')]//a[normalize-space()='" + optionName + "']/ancestor::article//div[contains(@class,'prices')]//span[contains(@class,'actual-price') or contains(@class,'price')]");
    }

    private By ratingByName() {
        return By.xpath("//section[contains(@class,'home-page-product-grid')]//a[normalize-space()='" + optionName + "']/ancestor::article//div[contains(@class,'rating')]");
    }

    private By addToCartByName() {
        return By.xpath("//section[contains(@class,'home-page-product-grid')]//a[normalize-space()='" + optionName + "']/ancestor::article//button[contains(@class,'product-box-add-to-cart-button')]");
    }

    private By wishlistByName() {
        return By.xpath("//section[contains(@class,'home-page-product-grid')]//a[normalize-space()='" + optionName + "']/ancestor::article//button[contains(@class,'add-to-wishlist-button')]");
    }

    private By compareByName() {
        return By.xpath("//section[contains(@class,'home-page-product-grid')]//a[normalize-space()='" + optionName + "']/ancestor::article//button[contains(@class,'add-to-compare-list-button')]");
    }

    // constructor

    public HomePage(GUIDriver driver) {
        super(driver);
        this.driver = driver;
        this.header = new HeaderComponent(driver);
        this.footer = new FooterComponent(driver);
        this.optionName = optionNames.get(new Random().nextInt(optionNames.size()));
        this.pollOption = pollOptions.get(new Random().nextInt(pollOptions.size()));
    }

    // actions
    @Step("Navigation to Home Page")
    public HomePage navigate() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb"));
        return this;
    }

    @Step("Select Feature Product {name}")
    public HomePage setFeaturedOption(String name) {
        this.optionName = name;
        return this;
    }

    @Step("Select Poll Option {option}")
    public HomePage setPollOption(int option) {
        this.pollOption = option;
        return this;
    }

    @Step("Click on Feature Product")
    public ProductPage clickFeaturedProduct() {
        String predictedEndpoint = driver.element().getDomProperty(titleByName(), "href");
        driver.element().click(titleByName());
        return new ProductPage(driver, predictedEndpoint);
    }

    @Step("Click on AddToCart Button")
    public BasePage clickAddToCart() {
        String predictedEndpoint = driver.element().getDomProperty(titleByName(), "href");
        driver.element().click(addToCartByName());
        if (driver.verification().isElementVisibleBoolean(header.cartSuccess)) {
            cartQty++;
            return this;
        } else {
            return new ProductPage(driver, predictedEndpoint);
        }
    }

    @Step("Click on AddToWishList Button")
    public BasePage clickWishlist() {
        String previousUrl = driver.browser().getCurrentUrl();
        String predictedEndpoint = driver.element().getDomProperty(titleByName(), "href");
        driver.element().click(wishlistByName());

        if (driver.verification().isElementVisibleBoolean(header.notificationMsg)) {
            wishListQty++;
            return this;
        } else {
            return new ProductPage(driver, predictedEndpoint);
        }
    }

    @Step("Click on Compare Button")
    public HomePage clickCompare() {
        driver.element().click(compareByName());
        return this;
    }

    @Step("Click on Featured Product image")
    public ProductPage clickImage() {
        String predictedEndpoint = driver.element().getDomProperty(titleByName(), "href");
        driver.element().click(imageByName());
        return new ProductPage(driver, predictedEndpoint);
    }

    @Step("Click on Vote Button After Random Voting")
    public HomePage voteUp() {
        driver.element().click(voteUpRadio());
        driver.element().click(voteButton);
        return this;
    }

    @Step("Get the count of Featured Products")
    public int getFeaturedProductsCount() {
        return driver.element().findElements(featuredProducts).size();
    }

    @Step("Get the Title of a Featured Product")
    public String getTitleText() {
        return driver.element().getText(titleByName());
    }

    @Step("Get the price of a Featured Product")
    public String getPriceText() {
        return driver.element().getText(priceByName());
    }

    @Step("Get the rate of a Featured Product")
    public String getRatingText() {
        return driver.element().getText(ratingByName());
    }

    @Step("Get HomePage Welcome message")
    public String getWelcomeMessage() {
        return driver.element().getText(welcomeMsg);
    }

    @Step("Get the result of vote")
    public String getVoteResultText() {
        return driver.element().getText(voteResultText);
    }

    // validations
    @Step("Validate if the Header Components are visible")
    public HomePage isHeaderVisible() {
        header.isHeaderComponentVisible();
        return this;
    }

    @Step("Validate if the Footer Components are visible")
    public HomePage isFooterVisible() {
        footer.isFooterComponentVisible();
        return this;
    }

    @Step("Validate that Welcome message appears")
    public HomePage isWelcomeMessageVisible() {
        driver.verification().isElementVisible(welcomeMsg);
        return this;
    }

    @Step("Validate that all featured products are counted")
    public HomePage isAllProductsCounted() {
        String count = String.valueOf(getFeaturedProductsCount());
        driver.verification().Equals(count, Integer.toString(optionNames.size()), "Featured Products are not 4 Products");
        return this;
    }

    @Step("Validate that Featured Products section is visible")
    public HomePage isFeaturedProductsSectionVisible() {
        driver.verification().isElementVisible(featuredProducts);
        return this;
    }

    @Step("Validate that Featured Product title is visible")
    public HomePage isProductTitleVisible() {
        driver.verification().isElementVisible(titleByName());
        return this;
    }

    @Step("Validate that clicking a featured product title navigates to its product page.")
    public ProductPage isNavigatedToProductPage_Title() {
        String expectedEndpoint = driver.element().getDomProperty(titleByName(), "href");
        clickFeaturedProduct();
        driver.verification().Equals(driver.browser().getCurrentUrl(), expectedEndpoint, "User isn't navigated to product url");
        return new ProductPage(driver, expectedEndpoint);
    }

    @Step("Validate that clicking a featured product image navigates to its product page.")
    public ProductPage isNavigatedToProductPage_Image() {
        String expectedEndpoint = driver.element().getDomProperty(titleByName(), "href");
        clickImage();
        driver.verification().Equals(driver.browser().getCurrentUrl(), expectedEndpoint, "User isn't navigated to product url");
        return new ProductPage(driver, expectedEndpoint);
    }

    @Step("Validate that Featured Product image is visible")
    public HomePage isProductImageVisible() {
        driver.verification().isElementVisible(imageByName());
        return this;
    }

    @Step("Validate that Featured Product price is visible")
    public HomePage isProductPriceVisible() {
        driver.verification().isElementVisible(priceByName());
        return this;
    }

    @Step("Validate that Featured Product rating is visible")
    public HomePage isProductRatingVisible() {
        driver.verification().isElementVisible(ratingByName());
        return this;
    }

    @Step("Validate that Add To Cart button is visible")
    public HomePage isAddToCartButtonVisible() {
        driver.verification().isElementVisible(addToCartByName());
        return this;
    }

    @Step("Validate that Wishlist button is visible")
    public HomePage isWishlistButtonVisible() {
        driver.verification().isElementVisible(wishlistByName());
        return this;
    }

    @Step("Validate that Compare button is visible")
    public HomePage isCompareButtonVisible() {
        driver.verification().isElementVisible(compareByName());
        return this;
    }

    @Step("Verify that all Home Page elements are loaded correctly")
    public HomePage verifyAllHomePageElementsLoaded() {
        isFeaturedProductsSectionVisible();
        isAllProductsCounted();
        isProductTitleVisible();
        isProductImageVisible();
        isProductPriceVisible();
        isProductRatingVisible();
        isAddToCartButtonVisible();
        isWishlistButtonVisible();
        isCompareButtonVisible();
        isHeaderVisible();
        isFooterVisible();
        isWelcomeMessageVisible();
        return this;
    }

    @Step("Validate that Add To Cart functionality works correctly")
    public BasePage isAddToCartWorking() {
        verifyAllHomePageElementsLoaded();
        BasePage result = clickAddToCart();
        if (result instanceof HomePage) {
            header.isNotificationVisible();
            header.isShoppingCartQtyCorrect(Integer.toString(cartQty));
        } else {
            header.isShoppingCartQtyCorrect(Integer.toString(cartQty));
        }
        return result;
    }

    @Step("Validate that Poll section is visible")
    public HomePage isPollSectionVisible() {
        driver.verification().isElementVisible(voteButton);
        driver.verification().isElementVisible(voteUpRadio());
        return this;
    }

    @Step("Validate that Vote Result text is visible")
    public HomePage isVoteResultTextVisible() {
        driver.verification().isElementVisible(voteResultText);
        return this;
    }

    @Step("Verify Poll Section functionality (Vote and see results)")
    public HomePage verifyPollSection() {
        isPollSectionVisible();
        voteUp();
        isVoteResultTextVisible();
        return this;
    }
}
