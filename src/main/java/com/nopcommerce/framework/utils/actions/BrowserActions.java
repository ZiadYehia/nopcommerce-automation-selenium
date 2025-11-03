package com.nopcommerce.framework.utils.actions;

import com.nopcommerce.framework.utils.WaitManager;
import com.nopcommerce.framework.utils.logs.LogsManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class BrowserActions {
    private final WebDriver driver;
    private WaitManager waitManager;
    public BrowserActions(WebDriver driver) {
        this.driver = driver;
        this.waitManager = new WaitManager(driver);
    }
    /**
     * Maximizes the browser window.
     */
    public void maximizeWindow() {
        LogsManager.info("Maximizing the browser window.");
        driver.manage().window().maximize();
    }

    /**
     * Gets the current page title.
     *
     * @return The title of the current page.
     */
    public String getPageTitle() {
        LogsManager.info("Page Title:", driver.getTitle());
        return driver.getTitle();
    }

    /**
     * Gets the current page URL.
     *
     * @return The URL of the current page.
     */
    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        LogsManager.info("Current URL:", url);
        return url;
    }

    /**
     * Navigates to the specified URL.
     *
     * @param url The URL to navigate to.
     */
    public void navigateTo(String url) {
        driver.get(url);
        LogsManager.info("Navigated to URL:", url);
    }

    /**
     * Refreshes the current page.
     */
    public void refreshPage() {
        driver.navigate().refresh();
        LogsManager.info("Page refreshed.");
    }

    /**
     * Navigates back to the previous page.
     */
    public void navigateBack() {
        driver.navigate().back();
        LogsManager.info("Navigated back to the previous page.");
    }

    /**
     * Navigates forward to the next page.
     */
    public void navigateForward() {
        driver.navigate().forward();
        LogsManager.info("Navigated forward to the next page.");
    }

    /**
     * Opens a new tab in the browser.
     */
    public void openNewTab() {
        driver.switchTo().newWindow(WindowType.TAB);
        LogsManager.info("Opened a new tab.");
    }

    /**
     * Opens a new window in the browser.
     */
    public void openNewWindow() {
        driver.switchTo().newWindow(WindowType.WINDOW);
        LogsManager.info("Opened a new window.");
    }


}
