package com.epam.gym.ui.stepdefinitions;

import com.epam.gym.browsers.DriverSingleton;
import com.epam.gym.pages.CoachesPage;
import com.epam.gym.pages.LoginPage;
import com.epam.gym.pages.CoachNavigation;
import com.epam.gym.utils.ConfigLoader;
import com.epam.gym.browsers.SeleniumCommon;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class UICoachesSteps {
    private static WebDriver driver;
    private static String u;
    private static LoginPage loginPage;
    CoachNavigation workoutsPage;
    CoachesPage coachesPage;

    @Given("I have logged in to the application")
    public void iHaveLoggedInToTheApplication() {
        String email = ConfigLoader.getProperty("email");
        String password = ConfigLoader.getProperty("password");
        driver = DriverSingleton.getDriver();
        String uri = ConfigLoader.getProperty("browser.uri");
        SeleniumCommon.openBrowser(uri + "login");
        loginPage = new LoginPage(driver);
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.submit();
    }

    @When("I view the list of coaches")
    public void iViewTheListOfCoaches() {
        workoutsPage = new CoachNavigation(driver);
        coachesPage = workoutsPage.navigateToCoachesPage();
    }

    @When("I click the Book Workout button for coach {string}")
    public void iClickTheButtonForCoach(String coachName) throws InterruptedException {
        Thread.sleep(3000);
        coachesPage.selectCoachByName(coachName);
    }

    @And("The book workouts page should be displayed and the url should contain {string}")
    public void theBookWorkoutsPageShouldBeDisplayed(String coachName) {
        Assert.assertTrue(driver.getCurrentUrl().contains(coachName));
    }

    @Then("I log out from the application")
    public void iLogOutFromTheApplication() {
        coachesPage.clickOnProfile();
        Assert.assertTrue(coachesPage.logoutButtonIsClickable());
        coachesPage.clickOnLogoutButton();
    }

    @And("I should see the toast message down as {string}")
    public void iShouldSeeTheToastMessageDownAs(String expectedToastMessage) {
        System.out.println(coachesPage.getToastMessage());
        Assert.assertTrue(coachesPage.getToastMessage().contains(expectedToastMessage),
                "Response does not contain the expected message: " + expectedToastMessage);
    }
}
