package com.epam.gym.ui.stepdefinitions;

import com.epam.gym.browsers.DriverSingleton;
import com.epam.gym.pages.CancelAndPostFeedbackPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class CancelAndPostFeedbackSteps {
    CancelAndPostFeedbackPage cancelAndPostFeedback;

    public CancelAndPostFeedbackSteps(){
        WebDriver driver = DriverSingleton.getDriver();
        cancelAndPostFeedback = new CancelAndPostFeedbackPage(driver);
    }

    @Given("In the workout page, I click on the cancelResume workout button")
    public void inTheUpcomingWorkoutPageIClickOnTheCancelWorkoutButton() {
        cancelAndPostFeedback.cancelResumeWorkout();
    }

    @Then("I should see Resume workout button")
    public void iShouldSeeResumeWorkoutAndCancelWorkoutButton() {
        cancelAndPostFeedback.validateResumeWorkoutbutton();
    }

    @And("I click on Resume workout button")
    public void iClickOnResumeWorkoutButton() {
        cancelAndPostFeedback.resumeWorkout();
    }
    //2nd
    @Given("In the workout page, I click on the cancel workout button")
    public void inTheWorkoutPageIClickOnTheCancelWorkoutButton() {
        cancelAndPostFeedback.cancelWorkout();
    }

    @When("I click on Cancel element workout button")
    public void iClickOnCancelButtonAndVerifyTheWorkoutIsCancelled() {
        cancelAndPostFeedback.cancelElementWorkout();
    }

    @Then("I should see the confirmation message")
    public void iShouldSeeTheWorkoutIsCancelled() {
        cancelAndPostFeedback.validateWorkoutCancelled();
    }

    //3rd
    @Given("In the workout page, I click on the Finish workout button")
    public void inTheWorkoutPageIClickOnTheFinishWorkoutButton() {
        cancelAndPostFeedback.clickFinishWorkout();
    }

    @When("I provide the rating")
    public void iProvideTheRating() {
        cancelAndPostFeedback.provideRating();
    }

    @Then("I enter the feedback")
    public void iEnterTheFeedback() {
        cancelAndPostFeedback.enterFeedback();
    }

    @And("I click on the submit button")
    public void iClickOnTheSubmitButton() {
        cancelAndPostFeedback.submitFeedback();
    }

    @Given("In the workout page, I click on Finish workout button")
    public void inTheWorkoutPageIClickOnFinishWorkoutButton() {
        cancelAndPostFeedback.clickFinishWorkoutButton();
    }

    @When("I provide the feedback without rating")
    public void iProvideTheFeedbackWithoutRating() {
        cancelAndPostFeedback.enterFeedbackText();
    }

    @Then("I click on submit button")
    public void iClickOnSubmitButton() {
        cancelAndPostFeedback.submitFeedbackButton();
    }

    @When("I provide the rating without feedback")
    public void iProvideTheRatingWithoutFeedback() {
        cancelAndPostFeedback.provideStarRating();
    }


}
