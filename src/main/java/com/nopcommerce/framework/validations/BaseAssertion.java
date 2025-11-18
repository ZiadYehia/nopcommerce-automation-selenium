package com.nopcommerce.framework.validations;

import com.nopcommerce.framework.utils.file.FileManager;
import com.nopcommerce.framework.utils.WaitManager;
import com.nopcommerce.framework.utils.actions.ElementActions;
import com.nopcommerce.framework.utils.logs.LogsManager;
import org.openqa.selenium.*;

public abstract class BaseAssertion {
    protected WebDriver driver;
    protected WaitManager waitManager;
    protected ElementActions elementActions;

    protected BaseAssertion() {

    }

    protected BaseAssertion(WebDriver driver) {
        this.driver = driver;
        this.waitManager = new WaitManager(driver);
        this.elementActions = new ElementActions(driver);
    }

    protected abstract void assertTrue(boolean condition, String message);

    protected abstract void assertFalse(boolean condition, String message);

    protected abstract void assertEquals(String actual, String expected, String message);

    public boolean EqualsBoolean(String actual, String expected, String message) {
        try {
            assertEquals(actual, expected, message);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public BaseAssertion Equals(String actual, String expected, String message) {
        assertEquals(actual, expected, message);
        return this;
    }

    public void isElementVisible(By locator) {
        boolean flag = waitManager.fluentWait().until(driver1 ->
        {
            try {
                driver1.findElement(locator).isDisplayed();
                LogsManager.info("Searching for Element: " + locator);
                return true;
            } catch (Exception e) {
                LogsManager.error("Element is not visible: " + locator);
                return false;
            }
        });
        assertTrue(flag, "Element is not visible: " + locator);
    }

    public boolean isElementVisibleBoolean(By locator) {
        try {
            boolean flag =  waitManager.fluentWait().until(driver1 -> {
                try {
                    LogsManager.info("Searching for Element: " + locator);
                    return driver1.findElement(locator).isDisplayed();
                } catch (Exception e) {
                    LogsManager.error("Element is not visible: " + locator);
                    return false;
                }
            });
            LogsManager.warn("Element visibility for " + locator + ": " + flag);
            return flag;
        } catch (org.openqa.selenium.TimeoutException | AssertionError e) {
            return false;
        }
    }

//    public boolean isElementVisibleMai(By locator) {
//        boolean flag = false;
//        LogsManager.info("flag main: " + flag);
//        try {
//            flag = waitManager.fluentWait().until(driver1 -> {
//                LogsManager.info("flag try: "+locator);
//                return driver1.findElement(locator).isDisplayed();
//            });
//            LogsManager.info("flag before: " + flag);
//        } catch (Exception e) {
//            LogsManager.info("flag catch: " + flag);
//        }
//        LogsManager.info("flag after: " + flag);
//        return flag;
//    }


    // verify page url
    public void assertPageUrl(String expectedUrl) {
        String actualUrl = driver.getCurrentUrl();
        assertEquals(actualUrl, expectedUrl, "URL does not match. Expected: " + expectedUrl + ", Actual: " + actualUrl);
    }

    // verify page title
    public void assertPageTitle(String expectedTitle) {
        String actualTitle = driver.getTitle();
        assertEquals(actualTitle, expectedTitle, "Title does not match. Expected: " + expectedTitle + ", Actual: " + actualTitle);
    }

    //verify that file exists
    public void assertFileExists(String fileName, String message) {
        waitManager.fluentWait().until(
                d -> FileManager.isFileExists(fileName)
        );
        assertTrue(FileManager.isFileExists(fileName), message);
    }
}
