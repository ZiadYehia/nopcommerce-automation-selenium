package com.nopcommerce.defaultconditions;

import com.nopcommerce.framework.drivers.GUIDriver;
import com.nopcommerce.framework.drivers.WebDriverProvider;
import com.nopcommerce.framework.utils.dataReader.JsonReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;

@Listeners({com.nopcommerce.framework.listeners.TestNGListeners.class})
public class BaseTest implements WebDriverProvider {
    protected GUIDriver driver;
    protected JsonReader testData;

    @Override
    public WebDriver getWebDriver() {
        return driver.get();
    }
}
