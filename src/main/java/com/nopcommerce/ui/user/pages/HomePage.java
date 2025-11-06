package com.nopcommerce.ui.user.pages;

import com.nopcommerce.framework.drivers.GUIDriver;
import com.nopcommerce.framework.utils.actions.ElementActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class HomePage {
    //variables
    private final GUIDriver driver;
    //locators
    By featuredProducts = By.cssSelector("div[class=\"product-item\"]");
    By welcomeMsg = By.cssSelector("[class=\"topic-block-title\"]");
    By voteUpRadio = By.cssSelector("[id=\"pollanswers-1\"]");
    By voteButton = By.cssSelector("[id=\"vote-poll-1\"]");
    By voteResultText = By.cssSelector("[class=\"poll-total-votes\"]");

    //constructor
    public HomePage(GUIDriver driver) {
        this.driver = driver;
    }

    //actions


    //validations


}
