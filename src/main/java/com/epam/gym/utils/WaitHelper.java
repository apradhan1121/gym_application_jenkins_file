package com.epam.gym.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitHelper {

    public static void waitForElement(WebElement element, long timeOutInSeconds, WebDriver driver)
    {
        WebDriverWait myWait= new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        myWait.until(ExpectedConditions.visibilityOf(element));
    }

    // Overloaded method for waiting for a WebElement
    public static WebElement waitForElementToBeVisible(WebDriver driver, WebElement element, long timeOutInSeconds) {
        WebDriverWait myWait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        return myWait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementToBeClickable(WebElement element, long timeOutInSeconds, WebDriver driver) {
        WebDriverWait myWait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        myWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitForvisibilityOfElementLocated(By element, long  timeOutInSeconds, WebDriver driver) {
        WebDriverWait myWait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        return myWait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public static void waitForElementToBeInvisible(WebDriver driver, WebElement element, long timeOutInSeconds) {
        WebDriverWait myWait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        myWait.until(ExpectedConditions.invisibilityOf(element));
    }

    public static void waitForPageLoad(WebDriver driver, int timeoutInSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

}
