package com.epam.gym.browsers;

import org.openqa.selenium.WebDriver;

public class DriverSingleton {

    private static WebDriver driver=null;

    private DriverSingleton() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            synchronized (DriverSingleton.class) {
                if (driver == null) {
                    driver = WebDriverFactory.getDriver();
                }
            }
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.close();
            driver = null;
        }
    }
}
