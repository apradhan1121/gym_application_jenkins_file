package com.epam.gym.pages;

import com.epam.gym.utils.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.time.LocalDate;

public class CancelAndPostFeedbackPage {

    WebDriver driver;
    //1.ResumeWorkout
    @FindBy(xpath = "(//button[text()=\"Cancel Workout\"])[1]")
    WebElement cancelResumeWorkout;

    @FindBy(xpath = "(//button[text()='Resume Workout'])[1]")
    WebElement resumeWorkout;

    //2.CancelWorkout
    @FindBy(xpath = "(//button[text()=\"Cancel Workout\"])[6]")
    WebElement cancelWorkout;

    @FindBy(xpath = "(//button[text()=\"Cancel Workout\"])[15]")
    WebElement cancelElementWorkout;

    @FindBy(xpath = "(//p[text()=\"Canceled\"])[last()]")
    WebElement cancelStatus;

    //3.FinishWorkout
    @FindBy(xpath = "(//button[text()=\"Finish Workout\"])[1]")
    WebElement finishWorkout;

    @FindBy(xpath = "(//span[text()=\"3 Stars\"]/ancestor::label)[1]")
    WebElement rating3Stars;

    @FindBy(xpath = "(//textarea)[15]")
    WebElement feedback;

    @FindBy(xpath = "(//button[text()=\"Submit Feedback\"])[8]")
    WebElement submitFeedback;

    //Providing Error message without feedback and rating
    @FindBy(xpath = "(//button[text()=\"Finish Workout\"])[9]")
    WebElement finishWorkoutButton;

    @FindBy(xpath = "(//textarea)[17]")
    WebElement feedbackText;

    @FindBy(xpath = "(//button[text()=\"Submit Feedback\"])[1]")
    WebElement submitFeedbackButton;

    @FindBy(xpath = "(//span[text()=\"4 Stars\"]/ancestor::label)[9]")
    WebElement rating4Stars;

    @FindBy(xpath ="//div [@role = \"alert\"]")
    private WebElement toastElement;

    @FindBy(xpath ="((//p[text()='James']/ancestor::div[@class=\"MuiBox-root css-k008qs\"]/following-sibling::div)//textarea)[1]")
    private WebElement feedbackTextArea;

    @FindBy(xpath = "(//div[@class = 'MuiBox-root css-nbo5uq']//*[name()='svg'])[2]")
    WebElement profileButton;

    @FindBy(xpath = "//button[text()='Logout']")
    WebElement logoutButton;





    public CancelAndPostFeedbackPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void cancelResumeWorkout() {
        System.out.println("Cancel workout button is clicked");
        WaitHelper.waitForElementToBeClickable(cancelResumeWorkout, 20, driver);
        cancelResumeWorkout.click();
    }

    public void validateResumeWorkoutbutton() {
        WaitHelper.waitForElementToBeClickable(resumeWorkout, 10, driver);
        Assert.assertEquals(resumeWorkout.isDisplayed(),true);
    }

    public void resumeWorkout() {
        WaitHelper.waitForElementToBeClickable(resumeWorkout,10,driver);
        resumeWorkout.click();
    }

    public void cancelWorkout() {
        System.out.println("Cancel workout button is clicked");
        WaitHelper.waitForElementToBeClickable(cancelWorkout, 20, driver);
        cancelWorkout.click();
    }


    public void cancelElementWorkout() {
        WaitHelper.waitForElementToBeClickable(cancelElementWorkout, 10, driver);
        cancelElementWorkout.click();
    }

    public void validateWorkoutCancelled() {
        WaitHelper.waitForElementToBeVisible(driver,cancelStatus,10);
        System.out.println(cancelStatus.getText());
        Assert.assertEquals(cancelStatus.getText(),"Canceled");
    }

    public void clickFinishWorkout() {
        WaitHelper.waitForElementToBeClickable(finishWorkout,20,driver);
        finishWorkout.click();
    }

    public void clickFinishWorkout(LocalDate dateTime) {
        String finishWorkoutButton = "//p[text()='" + dateTime + "']/parent::*/following-sibling::div[@class=\"MuiBox-root css-1bvc4cc\"]/button[text()=\"Finish Workout\"]";
        System.out.println(finishWorkoutButton);
        WebElement finishWorkout = driver.findElement(By.xpath(finishWorkoutButton));
        System.out.println(finishWorkout.getText());

        WaitHelper.waitForElementToBeClickable(finishWorkout,20,driver);
        finishWorkout.click();
    }


    public void provideRating() {
        WaitHelper.waitForElementToBeClickable(rating3Stars,10,driver);
        rating3Stars.click();
    }

    public void provideRating(LocalDate dateTime, int stars){
        //spring
        String ratingStars = "//p[@class='MuiTypography-root MuiTypography-body2 css-eulr2z-MuiTypography-root' and contains(., 'Date: " + dateTime +"')]/ancestor::div[@class=\"MuiBox-root css-k008qs\"]/following-sibling::div//span[text()='" + stars + " Stars']/ancestor::label";
        //p[@class='MuiTypography-root MuiTypography-body2 css-eulr2z-MuiTypography-root' and contains(., 'Date: 2025-01-17')]//ancestor::div[@class="MuiBox-root css-k008qs"]/following-sibling::div//span[text()='5 Stars']/ancestor::label

        //dagger
        //String ratingStars = "//p[@class='MuiTypography-root MuiTypography-body2 css-fosoue' and contains(text(), 'Date: " + dateTime +"')]/ancestor::div[@class=\"MuiBox-root css-k008qs\"]/following-sibling::div//span[text()='" + stars + " Stars']/ancestor::label";
        System.out.println(ratingStars);
        WebElement starsButton = driver.findElement(By.xpath(ratingStars));
        starsButton.click();
    }

    public void enterFeedback() {
        WaitHelper.waitForElementToBeClickable(feedback,10,driver);
        feedback.sendKeys("Good");
    }

    public void enterFeedback(String feedbackText){
        feedbackTextArea.sendKeys(feedbackText);
    }

    public void enterFeedback(LocalDate localDate, String feedbackText){
        String feedbackTextArea = "//p[@class='MuiTypography-root MuiTypography-body2 css-eulr2z-MuiTypography-root' and contains(., 'Date: " + localDate +"')]/ancestor::div[@class=\"MuiBox-root css-k008qs\"]/following-sibling::div//textarea";
        //p[@class='MuiTypography-root MuiTypography-body2 css-fosoue' and contains(., 'Date: 2025-01-13')]/ancestor::div[@class="MuiBox-root css-k008qs"]/following-sibling::div//textarea
        //p[@class='MuiTypography-root MuiTypography-body2 css-fosoue' and contains(text(), 'Date: 2025-01-14')]/ancestor::div[@class="MuiBox-root css-k008qs"]/following-sibling::div//textarea
        System.out.println(feedbackTextArea);
        WebElement feedbackElement = driver.findElement(By.xpath(feedbackTextArea));
        feedbackElement.sendKeys(feedbackText);
    }

    public void submitFeedback() {
        WaitHelper.waitForElementToBeClickable(submitFeedback,10,driver);
        submitFeedback.click();
    }

    public void clickFinishWorkoutButton() {
        WaitHelper.waitForElementToBeClickable(finishWorkoutButton,20,driver);
        finishWorkoutButton.click();
    }


    public void enterFeedbackText() {
        WaitHelper.waitForElementToBeClickable(feedbackText,10,driver);
        feedbackText.sendKeys("Good");
    }

    public void submitFeedbackButton() {
        WaitHelper.waitForElementToBeClickable(submitFeedbackButton,10,driver);
        submitFeedbackButton.click();
    }

    public void submitFeedbackButton(LocalDate localDate)
    {
        //p[@class='MuiTypography-root MuiTypography-body2 css-fosoue' and contains(., 'Date: 2025-01-13')]/ancestor::div[@class="MuiDialogContent-root css-1s63zyj"]//following-sibling::div[@class="MuiDialogActions-root MuiDialogActions-spacing css-b07ifn"]//button
        //String submitFeedbackButton ="//p[@class='MuiTypography-root MuiTypography-body2 css-fosoue' and contains(., 'Date: " + localDate +"')]/ancestor::div[@class='MuiDialogContent-root css-1s63zyj']//following-sibling::div[@class='MuiDialogActions-root MuiDialogActions-spacing css-b07ifn']//button";

        //spring
        //p[@class='MuiTypography-root MuiTypography-body2 css-eulr2z-MuiTypography-root' and contains(., 'Date: 2025-01-20')]/ancestor::div[@class="MuiDialogContent-root css-1kxd7on-MuiDialogContent-root"]//following-sibling::div[@class="MuiDialogActions-root MuiDialogActions-spacing css-19kok5i-MuiDialogActions-root"]//button
        String submitFeedbackButton ="//p[@class='MuiTypography-root MuiTypography-body2 css-eulr2z-MuiTypography-root' and contains(., 'Date: " + localDate +"')]/ancestor::div[@class='MuiDialogContent-root css-1kxd7on-MuiDialogContent-root']//following-sibling::div[@class='MuiDialogActions-root MuiDialogActions-spacing css-19kok5i-MuiDialogActions-root']//button";
        System.out.println(submitFeedbackButton);
        WebElement submitFeedback = driver.findElement(By.xpath(submitFeedbackButton));
        submitFeedback.click();
    }

    public void provideStarRating() {
        WaitHelper.waitForElementToBeClickable(rating4Stars,10,driver);
        rating4Stars.click();
    }

    public String  getToastMessage() {
        WaitHelper.waitForElementToBeVisible(driver, toastElement, 15);
        return toastElement.getText();
    }

    public void logout() throws  InterruptedException{
        profileButton.click();
        Thread.sleep(2000);
        logoutButton.click();

    }
}
