package com.epam.gym.ui.stepdefinitions;

import com.epam.gym.browsers.DriverSingleton;
import com.epam.gym.pages.WorkoutStepPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;

public class WorkoutStep {
    WebDriver driver;
    WorkoutStepPage workoutStepPage;

    public WorkoutStep(){
        driver = DriverSingleton.getDriver();
        workoutStepPage = new WorkoutStepPage(driver);
    }
    @Given("I am on the Workouts page")
    public void iAmOnTheWorkoutsPage() {
        workoutStepPage.verifyWorkoutPage();
    }

    @Then("I should see Workouts button and click on it")
    public void iShouldSeeWorkoutsButtonInTheTopNavbar() throws InterruptedException {
        workoutStepPage.verifyWorkoutButton();
        Thread.sleep(3000);
        driver.navigate().back();
    }

    @And("I should see Coaches button in the top navbar and click on it")
    public void iShouldSeeCoachButtonInTheTopNavbar() throws InterruptedException {
        workoutStepPage.verifyCoachButton();
        Thread.sleep(3000);
        driver.navigate().back();
    }

    @Then("I should see toggle Dark button and click it")
    public void iShouldSeeToggleButtonAndEnableDarkMode() throws InterruptedException{
        workoutStepPage.verifyToggleDarkButton();
        Thread.sleep(3000);
    }
    @And("I should see toggle light button and click it")
    public void iShouldSeeToggleButtonAndEnableLightMode() throws InterruptedException{
        Thread.sleep(3000);
        workoutStepPage.verifyToggleLightButton();
        Thread.sleep(3000);
    }

    @And("I should navigate back to the Workouts page")
    public void iShouldNavigateBackToTheWorkoutsPage() throws InterruptedException {
        Thread.sleep(3000);
        driver.navigate().back();
    }

    @And("I should verify the presence of scheduled workout")
    public void iShouldVerifyThePresenceOfScheduledWorkout() {
        workoutStepPage.verifyScheduledWorkout();
    }
}
