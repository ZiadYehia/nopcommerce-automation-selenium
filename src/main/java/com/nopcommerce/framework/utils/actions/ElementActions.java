package com.nopcommerce.framework.utils.actions;

import com.nopcommerce.framework.utils.WaitManager;
import com.nopcommerce.framework.utils.logs.LogsManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.List;

public class ElementActions {
    private final WebDriver driver;
    private WaitManager waitManager;

    public ElementActions(WebDriver driver) {
        this.driver = driver;
        this.waitManager = new WaitManager(driver);
    }

    // Click action
    public void click(By by) {
        waitManager.fluentWait()
                .until(d -> {
                    try {
                        WebElement element = d.findElement(by);
                        LogsManager.info("Scrolling to click on element:", by.toString());
                        scrollToElementJs(by);
                        element.click();
                        LogsManager.info("Clicked on element:", by.toString());
                        return true;
                    } catch (Exception e) {
                        LogsManager.error("Failed to click on element:", by.toString(), e.getMessage());
                        return false;
                    }
                });
    }

    // Type action
    public void type(By by, String text) {
        waitManager.fluentWait()
                .until(d -> {
                    try {
                        WebElement element = d.findElement(by);
                        LogsManager.info("Scrolling to type in element:", by.toString());
                        scrollToElementJs(by);
                        LogsManager.info("Typing text in element:", by.toString());
                        element.clear();
                        element.sendKeys(text);
                        LogsManager.info("Typed text in element:", by.toString());
                        return true;
                    } catch (Exception e) {
                        LogsManager.error("Failed to type in element:", by.toString(), e.getMessage());
                        return false;
                    }
                });
    }

    // hover action
    public void hover(By by) {
        waitManager.fluentWait()
                .until(d -> {
                    try {
                        WebElement element = d.findElement(by);
                        LogsManager.info("Scrolling to hover on element:", by.toString());
                        scrollToElementJs(by);
                        LogsManager.info("Hovering on element:", by.toString());
                        new Actions(driver).moveToElement(element).perform();
                        LogsManager.info("Hovered on element:", by.toString());
                        return true;
                    } catch (Exception e) {
                        LogsManager.error("Failed to hover on element:", by.toString(), e.getMessage());
                        return false;
                    }
                });

    }

    public void clear(By by){
        waitManager.fluentWait()
                .until(d->{
                    try{
                        WebElement element = d.findElement(by);
                        LogsManager.info("Scrolling to clear element:", by.toString());
                        scrollToElementJs(by);
                        LogsManager.info("Clearing text in element:", by.toString());
                        element.clear();
                        LogsManager.info("Cleared text in element:", by.toString());
                        return true;
                    }catch (Exception e) {
                        LogsManager.error("Failed to clear element:", by.toString(), e.getMessage());
                        return false;
                    }
                });
    }

    // Get text action
    public String getText(By by) {
        return waitManager.fluentWait()
                .until(d -> {
                    try {
                        WebElement element = d.findElement(by);
                        LogsManager.info("Scrolling to get text from element:", by.toString());
                        scrollToElementJs(by);
                        if (!element.getText().isEmpty()) {
                            LogsManager.info("Got text from element:", by.toString());
                            return element.getText();
                        } else {
                            LogsManager.warn("No text found in element:", by.toString());
                            return null;
                        }
                    } catch (Exception e) {
                        LogsManager.error("Failed to get text from element:", by.toString(), e.getMessage());
                        return null;
                    }
                });
    }

    // Get Current URL
    public String getCurrentUrl() {
        return waitManager.fluentWait()
                .until(d -> {
                    try {
                        LogsManager.info("Getting current URL");
                        return d.getCurrentUrl();
                    } catch (Exception e) {
                        LogsManager.error("Failed to get current URL:", e.getMessage());
                        return null;
                    }
                });
    }

    // Get Dom Property
    public String getDomProperty(By by, String property) {
        return waitManager.fluentWait()
                .until(d -> {
                    try {
                        WebElement element = d.findElement(by);
                        LogsManager.info("Scrolling to get DOM property from element:", by.toString());
                        scrollToElementJs(by);
                        String propValue = element.getDomProperty(property);
                        if (!propValue.isEmpty()) {
                            LogsManager.info("Got DOM property from element:", by.toString());
                            return propValue;
                        } else {
                            LogsManager.warn("No DOM property found in element:", by.toString());
                            return null;
                        }
                    } catch (Exception e) {
                        LogsManager.error("Failed to get DOM property from element:", by.toString(), e.getMessage());
                        return null;
                    }
                });
    }

    public void scrollToElement(By by) {
        waitManager.fluentWait()
                .until(d -> {
                    try {
                        WebElement element = driver.findElement(by);
                        LogsManager.info("Scrolling to element:", by.toString());
                        scrollToElementJs(by);
                        LogsManager.info("Scrolled to element:", by.toString());
                        return true;
                    } catch (Exception e) {
                        LogsManager.error("Failed to scroll to element:", by.toString(), e.getMessage());
                        return false;
                    }
                });
    }

    // Scroll to Element using javaScript with behavior auto , block center , inline center
    public void scrollToElementJs(By by) {
        waitManager.fluentWait()
                .until(d -> {
                    try {
                        WebElement element = driver.findElement(by);
                        LogsManager.info("Scrolling to element using JavaScript:", by.toString());
                        ((org.openqa.selenium.JavascriptExecutor) driver)
                                .executeScript("arguments[0].scrollIntoView({behavior: 'auto', block: 'center', inline: 'center'});", element);
                        LogsManager.info("Scrolled to element using JavaScript:", by.toString());
                        return true;
                    } catch (Exception e) {
                        LogsManager.error("Failed to scroll to element using JavaScript:", by.toString(), e.getMessage());
                        return false;
                    }
                });
    }

    public WebElement findElement(By by) {
        return waitManager.fluentWait()
                .until(d -> {
                    try {
                        WebElement element = d.findElement(by);
                        LogsManager.info("Scrolling to find element:", by.toString());
                        scrollToElementJs(by);
                        LogsManager.info("Found element:", by.toString());
                        return element;
                    } catch (Exception e) {
                        LogsManager.error("Failed to find element:", by.toString(), e.getMessage());
                        return null;
                    }
                });
    }

    public List<WebElement> findElements(By by) {
        return waitManager.fluentWait()
                .until(d -> {
                    try {
                        List<WebElement> elements = d.findElements(by);
                        if (elements != null && !elements.isEmpty()) {
                            LogsManager.info("Scrolling to first element of list:", by.toString());
                            scrollToElementJs(by);
                            LogsManager.info("Found " + elements.size() + " elements for: " + by.toString());
                            return elements;
                        } else {
                            LogsManager.warn("No elements found for:", by.toString());
                            return null;
                        }
                    } catch (Exception e) {
                        LogsManager.error("Failed to find elements:", by.toString(), e.getMessage());
                        return null;
                    }
                });
    }

    // Upload File

    public void uploadFile(By by, String filePath) {
        String fileAbsolutePath = System.getProperty("user.dir") + File.separator + filePath;
        waitManager.fluentWait()
                .until(d -> {
                    try {
                        WebElement element = d.findElement(by);
                        LogsManager.info("Scrolling to upload file to element:", by.toString());
                        scrollToElementJs(by);
                        LogsManager.info("Uploading file to element:", by.toString());
                        element.sendKeys(fileAbsolutePath);
                        LogsManager.info("Uploaded file to element:", by.toString());
                        return true;
                    } catch (Exception e) {
                        LogsManager.error("Failed to upload file to element:", by.toString(), e.getMessage());
                        return false;
                    }
                });
    }

    //select from dropdown
    public ElementActions selectFromDropdown_Select(By locator, String value) {
        waitManager.fluentWait().until(d ->
                {
                    try {
                        WebElement element = d.findElement(locator);
                        scrollToElementJs(locator);
                        Select select = new Select(element);
                        select.selectByVisibleText(value);
                        LogsManager.info("Selected value '" + value + "' from dropdown: " + locator);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }
        );
        return this;
    }

    public ElementActions selectFromDropdown_Normal(By locator, By value) {
        waitManager.fluentWait().until(d ->
        {
            try {
                WebElement element = d.findElement(locator);
                scrollToElementJs(locator);
                new Actions(driver)
                        .moveToElement(element)
                        .perform();
                WebElement option = d.findElement(value);
                new Actions(driver)
                        .scrollToElement(option)
                        .click(option)
                        .perform();
                LogsManager.info("Selected option '" + value + "' from dropdown: " + locator);
                return true;
            } catch (Exception e) {
                LogsManager.error("Failed to select value '" + value.toString() + "' from dropdown: " + locator.toString(), e.getMessage());
                return false;
            }

        });
        return this;
    }

    public String getSelectedOptionFromDropdown_Select(By selectedDropdown) {
        return waitManager.fluentWait().until(d ->
        {
            try {
                WebElement element = d.findElement(selectedDropdown);
                scrollToElementJs(selectedDropdown);
                Select select = new Select(element);
                String selectedOption = select.getFirstSelectedOption().getText();
                LogsManager.info("Got selected option '" + selectedOption + "' from dropdown: " + selectedDropdown);
                return selectedOption;
            } catch (Exception e) {
                LogsManager.error("Failed to get selected option from dropdown:", selectedDropdown.toString(), e.getMessage());
                return null;
            }
        });
    }
}
