package com.epam.gym.ui.stepdefinitions;

import com.epam.gym.pages.*;
import com.epam.gym.utils.ConfigLoader;
import com.epam.gym.browsers.SeleniumCommon;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import java.time.LocalDate;

public class UIBookWorkoutsSteps {

    private static String uri;
    private static WebDriver driver;
    private static LoginPage loginPage;
    CoachNavigation workoutsPage;
    CoachesPage coachesPage;
    BookWorkOutPage bookWorkOutPage;


    public static void theUserisLoggedIn(){
        String email = ConfigLoader.getProperty("email");
        String password = ConfigLoader.getProperty("password");
        uri= ConfigLoader.getProperty("browser.uri");
        driver = SeleniumCommon.openBrowser(uri+"login");
        loginPage = new LoginPage(driver);
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.submit();
    }

    @Given("the user navigates to the book workouts page")
    public void theUserIsOnTheBookWorkoutsPage() throws InterruptedException{
        theUserisLoggedIn();
        workoutsPage = new CoachNavigation(driver);
        Thread.sleep(5000);
        coachesPage = workoutsPage.navigateToCoachesPage();
        Thread.sleep(5000);
        bookWorkOutPage = coachesPage.navigateToBookWorkoutPage();
    }

    @When("the user selects year, month, date, and time slot {string}")
    public void theUserSelectsYearMonthDateAndTimeSlot(String timeSlot) throws InterruptedException {
        String month = LocalDate.now().plusMonths(1).getMonth().toString().toLowerCase();
        month = month.substring(0, 1).toUpperCase() + month.substring(1);
        System.out.println(month);
        int date = LocalDate.now().plusDays(133).getDayOfMonth();
        int year = LocalDate.now().getYear();
        System.out.println(date);
        Thread.sleep(3000);
        bookWorkOutPage.validateBookingDateTime(year, month, date, timeSlot);
        System.out.println(year);
    }

    @When("the user selects past year year, month, date, and time slot {string}")
    public void theUserSelectsPastYearYearMonthDateAndTimeSlot(String timeSlot) throws InterruptedException{
        String month = LocalDate.now().minusMonths(2).getMonth().toString().toLowerCase();
        month = month.substring(0, 1).toUpperCase() + month.substring(1);
        System.out.println(month);
        int date = LocalDate.now().minusDays(9).getDayOfMonth();
        int year = LocalDate.now().minusYears(1).getYear();
        System.out.println(year);
        Thread.sleep(3000);
        bookWorkOutPage.validateBookingDateTime(year,month,date,timeSlot);
        System.out.println(date);
    }

    @And("the user clicks on the book button")
    public void theUserClicksOnTheBookButton() {
        if (bookWorkOutPage.isBookWorkOutButtonEnabled()) {
            bookWorkOutPage.clickBookWorkOutButton();
        }else {
            System.out.println("Cannot book workout");
        }
    }

    @Then("the user should see the toast message as {string}")
    public void theUserShouldSeeTheToastMessageAs(String expectedToastMessage) {
        System.out.println(bookWorkOutPage.getToastMessage());
        Assert.assertTrue(bookWorkOutPage.getToastMessage().contains(expectedToastMessage),
               "Response does not contain the expected message: " + expectedToastMessage);
    }

    @Then("the book workout button should be disabled")
    public void theBookWorkoutButtonShouldBeDisabled() {
        Assert.assertTrue(bookWorkOutPage.isBookWorkOutButtonEnabled());
    }

    @Then("the time slot button should be disabled for {string}")
    public void theTimeSlotButtonShouldBeDisabled(String timeSlot) {
        Assert.assertTrue(bookWorkOutPage.isTimeSlotButtonDisabled(timeSlot));
    }
}
