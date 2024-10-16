package com.epam.gym.browsers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class WebDriverFactory {

    private static final Logger logger = LogManager.getLogger(WebDriverFactory.class);

    public enum BrowserType {
        CHROME,
        EDGE,
        FIREFOX
    }

    public static WebDriver getDriver() {
        BrowserType browserType = getBrowserTypeFromConfig();
        return createDriver(browserType);
    }

    private static BrowserType getBrowserTypeFromConfig() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(fis);
            String browserTypeString = properties.getProperty("browser.type", "firefox"); // Default to firefox if property is not found
            return parseBrowserType(browserTypeString);
        } catch (IOException e) {
            logger.error("Error loading config.properties: {}", e.getMessage());
        }

        return BrowserType.CHROME;
    }


    private static BrowserType parseBrowserType(String browserTypeString) {
        switch (browserTypeString.toLowerCase()) {
            case "chrome":
                return BrowserType.CHROME;
            case "edge":
                return BrowserType.EDGE;
            case "firefox":
                return BrowserType.FIREFOX;
            default:
                throw new IllegalArgumentException("Browser \"" + browserTypeString + "\" not supported.");
        }
    }


    private static WebDriver createDriver(BrowserType browserType) {
        switch (browserType) {
            case CHROME:
                return new ChromeDriver();
            case EDGE:
                return new EdgeDriver();
            case FIREFOX:
                return new FirefoxDriver();
            default:
                throw new IllegalArgumentException("Browser \"" + browserType + "\" not supported.");
        }
    }
}
