package com.epam.gym.smoketest.stepdefinitions;

import com.epam.gym.pages.*;
import com.epam.gym.utils.ConfigLoader;
import com.epam.gym.browsers.SeleniumCommon;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.time.LocalDate;

public class BookAndFinishWorkoutSteps {

    private static final Logger log = LoggerFactory.getLogger(BookAndFinishWorkoutSteps.class);
    private static String uri;
    private static WebDriver driver;
    private static LoginPage loginPage;
    private static LocalDate dateTime;
    CoachNavigation workoutsPage;
    CoachesPage coachesPage;
    BookWorkOutPage bookWorkOutPage;
    CancelAndPostFeedbackPage cancelAndPostFeedbackPage;
    String email = ConfigLoader.getProperty("email");
    String password = ConfigLoader.getProperty("password");

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        uri= ConfigLoader.getProperty("browser.uri");
        driver = SeleniumCommon.openBrowser(uri);
        loginPage = new LoginPage(driver);
        loginPage.openLoginPage();
    }

    @When("the user logs in with valid credentials")
    public void theUserLogsInWithValidCredentials() {
        System.out.println("In the login page");
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.submit();

    }

    @And("the user is redirected to the dashboard")
    public void theUserIsRedirectedToTheDashboard() throws InterruptedException{
        Thread.sleep(7000);
        workoutsPage = new CoachNavigation(driver);
        driver.navigate().refresh();
        coachesPage = workoutsPage.navigateToCoachesPage();
    }

    @When("the user navigates to the coaches page and selects a {string}")
    public void theUserNavigatesToTheCoachesPageAndSelectsA(String coachName) throws InterruptedException {
        Thread.sleep(4000);
        bookWorkOutPage = coachesPage.selectCoachByName(coachName);

    }

    @And("the user selects a workout for the date and time {string}")
    public void theUserSelectsAWorkoutForTheDateAndTime(String timeSlot) {

        dateTime = LocalDate.now().plusMonths(10);
        dateTime = dateTime.plusDays(20);

        // Print the updated dateTime
        System.out.println("Date time is: " + dateTime);

        String month = dateTime.getMonth().toString().toLowerCase();
        month = month.substring(0, 1).toUpperCase() + month.substring(1);

        int date = dateTime.getDayOfMonth();
        int year = dateTime.getYear();

        // Use the updated values in your method call
        bookWorkOutPage.validateBookingDateTime(year, month, date, timeSlot);
    }

    @And("the user clicks on the book workout button")
    public void theUserClicksOnTheBookWorkoutButton() throws InterruptedException{
        Thread.sleep(3000);
        bookWorkOutPage.clickBookWorkOutButton();
    }

    @Then("the workout is booked successfully with a toast message {string}")
    public void theWorkoutIsBookedSuccessfullyWithAToastMessage(String expectedToastMessage) {
    }

    @When("the user navigates to the workouts page")
    public void theUserNavigatesToThePage() throws InterruptedException{
        Thread.sleep(3000);
        cancelAndPostFeedbackPage = bookWorkOutPage.navigateToCancelAndPostFeedbackPage();
    }

    @And("the user clicks the finish workout button")
    public void theUserSelectsTheCompletedWorkoutOn() throws InterruptedException {
        Thread.sleep(10000);
        cancelAndPostFeedbackPage.clickFinishWorkout(dateTime);
    }

    @And("the user provides a rating {int} and feedback {string}")
    public void theUserProvidesARatingAndFeedback(int rating, String feedback) throws InterruptedException {
        Thread.sleep(4000);
        cancelAndPostFeedbackPage.provideRating(dateTime,rating);
        Thread.sleep(3000);
        cancelAndPostFeedbackPage.enterFeedback(dateTime,feedback);
        Thread.sleep(2000);
        cancelAndPostFeedbackPage.submitFeedbackButton(dateTime);
        System.out.println(cancelAndPostFeedbackPage.getToastMessage());
    }

    @Then("the workout rating and feedback is successfully submitted with message {string}")
    public void theWorkoutRatingAndFeedbackIsSuccessfullySubmittedWithMessage(String expectedToastMessage) {
        /*Assert.assertTrue(cancelAndPostFeedbackPage.getToastMessage().contains(expectedToastMessage),
                "Response does not contain the expected message: " + expectedToastMessage);*/
    }

    @And("the user clicks the cancel workout and further clicks on cancel workout button")
    public void theUserClicksTheCancelWorkoutAndFurtherClicksOnCancelWorkoutButton() throws InterruptedException {
        Thread.sleep(3000);
        cancelAndPostFeedbackPage.cancelWorkout();
        Thread.sleep(3000);
        cancelAndPostFeedbackPage.cancelElementWorkout();
    }

    @And("Logout the page")
    public void logoutThePage() throws InterruptedException {
        cancelAndPostFeedbackPage.logout();
    }
}
