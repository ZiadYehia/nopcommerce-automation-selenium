package com.nopcommerce.ui.user.common.components;

import com.nopcommerce.framework.drivers.GUIDriver;
import org.openqa.selenium.By;

public class FooterComponent {

    //variables
    private final GUIDriver driver;
    private String optionName;
    private String newsSuccessMsg = "Thank you for signing up! A verification email has been sent. We appreciate your interest.";
    private String newsErrorMsg = "Enter valid email";
    private String facebookUrl = "https://www.facebook.com/nopCommerce";
    private String twitterUrl = "https://twitter.com/nopCommerce";
    private String rssUrl = "https://www.nopcommerce.com/rss";
    private String youtubeUrl = "https://www.youtube.com/user/nopCommerce";
    private String instagramUrl = "https://www.instagram.com/nopcommerce/";

    //locators
    By facebookLink = By.cssSelector("[class=\"facebook\"]");
    By twitterLink = By.cssSelector("[class=\"twitter\"]");
    By rssLink = By.cssSelector("[class=\"rss\"]");
    By youtubeLink = By.cssSelector("[class=\"youtube\"]");
    By instagramLink = By.cssSelector("[class=\"instagram\"]");
    By dynamicFooterLinks = By.cssSelector("//nav[@class='footer-navigation'] //a[.="+optionName+"]");
    By footerNewsletterInput = By.cssSelector("[id=\"newsletter-email\"]");
    By footerNewsletterButton = By.cssSelector("[id=\"newsletter-subscribe-button\"]");
    By footerNewsletterMsg = By.cssSelector("[id=\"newsletter-result-block\"]"); //"Thank you for signing up! A verification email has been sent. We appreciate your interest." if successful and "Enter valid email" if not

    //constructor
    public FooterComponent(GUIDriver driver) {
        this.driver = driver;
    }

    //actions
    public FooterComponent subscribeToNewsletter(String email) {
        driver.element().type(footerNewsletterInput, email);
        driver.element().click(footerNewsletterButton);
        return this;
    }

    public String getNewsletterMessage() {
        return driver.element().getText(footerNewsletterMsg);
    }

    public FooterComponent clickSocialMediaLink(String platform) {
        switch (platform.toLowerCase()) {
            case "facebook" -> driver.element().click(facebookLink);
            case "twitter" -> driver.element().click(twitterLink);
            case "rss" -> driver.element().click(rssLink);
            case "youtube" -> driver.element().click(youtubeLink);
            case "instagram" -> driver.element().click(instagramLink);
            default -> throw new IllegalArgumentException("Invalid social media platform: " + platform);
        }
        return this;
    }

    public FooterComponent clickFooterLink(String optionName) {
        this.optionName = optionName;
        driver.element().click(dynamicFooterLinks);
        return this;
    }


    //validations

    public FooterComponent verifyNewsletterMessage() {
        driver.verification().Equals(driver.element().getText(footerNewsletterMsg), newsSuccessMsg, "Newsletter message does not match expected.");
        return this;
    }

    public FooterComponent verifyInvalidNewsletterMessage() {
        driver.verification().Equals(driver.element().getText(footerNewsletterMsg), newsErrorMsg, "Newsletter error message does not match expected.");
        return this;
    }

    public FooterComponent verifySocialMediaUrl(String platform) {
        String expectedUrl;
        switch (platform.toLowerCase()) {
            case "facebook" -> expectedUrl = facebookUrl;
            case "twitter" -> expectedUrl = twitterUrl;
            case "rss" -> expectedUrl = rssUrl;
            case "youtube" -> expectedUrl = youtubeUrl;
            case "instagram" -> expectedUrl = instagramUrl;
            default -> throw new IllegalArgumentException("Invalid social media platform: " + platform);
        }
        driver.verification().assertPageUrl(expectedUrl);
        return this;
    }

    public FooterComponent isFooterComponentVisible() {
        driver.verification().isElementVisible(footerNewsletterInput);
        driver.verification().isElementVisible(footerNewsletterButton);
        driver.verification().isElementVisible(facebookLink);
        driver.verification().isElementVisible(twitterLink);
        driver.verification().isElementVisible(rssLink);
        driver.verification().isElementVisible(youtubeLink);
        driver.verification().isElementVisible(instagramLink);
        return this;
    }

    public FooterComponent isFooterLinkVisible(String optionName) {
        this.optionName = optionName;
        driver.verification().isElementVisible(dynamicFooterLinks);
        return this;
    }

    public FooterComponent isNewsletterMessageVisible() {
        driver.verification().isElementVisible(footerNewsletterMsg);
        return this;
    }

    public FooterComponent isNewsletterInputVisible() {
        driver.verification().isElementVisible(footerNewsletterInput);
        return this;
    }

    public FooterComponent isNewsletterButtonVisible() {
        driver.verification().isElementVisible(footerNewsletterButton);
        return this;
    }
}
