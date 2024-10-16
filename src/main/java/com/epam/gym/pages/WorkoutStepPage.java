package com.epam.gym.pages;

import com.epam.gym.browsers.DriverSingleton;
import com.epam.gym.utils.WaitHelper;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WorkoutStepPage {
    WebDriver driver;
    public WorkoutStepPage(WebDriver driver){
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//h5[contains(text(),\"Hello\")]")
    WebElement helloText;

    @FindBy(xpath = "//p[text()=\"Workouts\"]")
    WebElement workoutButton;

    @FindBy(xpath = "//p[text()=\"Coaches\"]")
    WebElement coachButton;

    @FindBy(xpath = "//button[@class=\"MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium css-197byy7\"]")
    WebElement toggleDarkButton;

    @FindBy(xpath = "//button[@class=\"MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium css-g6t0nw\"]")
    WebElement toggleLightButton;

    @FindBy(xpath = "(//h6[contains(text(),\"rock climbing\")])[1]")
    WebElement scheduledWorkout;


    public void verifyWorkoutPage() {
        WaitHelper.waitForElementToBeVisible(driver,helloText,20);
        Assert.assertTrue(helloText.isDisplayed());

    }

    public void verifyWorkoutButton() {
        WaitHelper.waitForElementToBeVisible(driver,workoutButton,20);
        Assert.assertTrue(workoutButton.isDisplayed());
        workoutButton.click();
    }


    public void verifyCoachButton() {
        WaitHelper.waitForElementToBeVisible(driver,coachButton,20);
        Assert.assertTrue(coachButton.isDisplayed());
        coachButton.click();
    }

    public void verifyToggleDarkButton() {
        WaitHelper.waitForElementToBeClickable(toggleDarkButton,20,driver);
        toggleDarkButton.click();
    }

    public void verifyToggleLightButton() {
        WaitHelper.waitForElementToBeClickable(toggleLightButton,20,driver);
        toggleLightButton.click();
    }

    public void verifyScheduledWorkout() {
        WaitHelper.waitForElementToBeVisible(driver,scheduledWorkout,20);
    }
}
