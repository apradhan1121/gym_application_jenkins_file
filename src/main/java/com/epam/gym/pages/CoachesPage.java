package com.epam.gym.pages;

import com.epam.gym.utils.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CoachesPage {

    WebDriver driver;

    @FindBy(xpath = "//div[@class='MuiBox-root css-nbo5uq']/button")
    WebElement nightMode;

    @FindBy(xpath = "//button[text()='Book Workout']")
    WebElement bookWorkoutButton;

    @FindBy(xpath = "(//p[text()='3'])[1]")
    WebElement coachRating;

//    List<WebElement> coaches = driver.findElements(By.xpath("//div[@class='MuiBox-root css-1lekzkb']/h6"));

    @FindBy(xpath = "//p[text()='Workouts']")
    WebElement workoutsButton;

    @FindBy(xpath = "(//div[@class = 'MuiBox-root css-nbo5uq']//*[name()='svg'])[2]")
    WebElement profileButton;

    @FindBy(xpath = "//button[text()='Logout']")
    WebElement logoutButton;

    @FindBy(xpath ="//div[text() = 'Logged out successfully']")
    WebElement toastElement;

    @FindBy(xpath = "//li[text()='Edit account profile']")
    WebElement editAccountProfile;

    public CoachesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void clickOnBookWorkoutButton() {
        WaitHelper.waitForElementToBeVisible(driver, bookWorkoutButton, 10);
        bookWorkoutButton.click();
    }

    public BookWorkOutPage navigateToBookWorkoutPage() {
        bookWorkoutButton.click();
        return new BookWorkOutPage(driver);
    }

    public boolean nightModeIsEnabled(){
        nightMode.click();
        return nightMode.isEnabled();
    }


    public void clickOnProfile() {
        profileButton.click();
    }

    public void clickOnLogoutButton() {
        logoutButton.click();
    }

    public BookWorkOutPage selectCoachByName(String name){
        WebElement bookButtonUsingCoachName = driver.findElement(By.xpath("//h6[text()='" + name + "']/ancestor::div[1]/../following-sibling::div/button"));
        System.out.println(bookButtonUsingCoachName.getText());
        bookButtonUsingCoachName.click();
        return new BookWorkOutPage(driver);
    }

    public boolean logoutButtonIsClickable(){
        return logoutButton.isEnabled();
    }
    public String  getToastMessage() {
        WaitHelper.waitForElementToBeVisible(driver, toastElement, 3);
        return toastElement.getText();
    }

    public WorkoutStepPage navigateToWorkoutSPage() {
        workoutsButton.click();
        return new WorkoutStepPage(driver);
    }

}
