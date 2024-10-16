package com.epam.gym.ui.stepdefinitions;

import com.epam.gym.pages.RegistrationPage;
import com.epam.gym.utils.ConfigLoader;
import com.epam.gym.utils.ScreenshotUtil;
import com.epam.gym.browsers.SeleniumCommon;
import io.cucumber.java.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class UIRegistrationSteps {
    private static String uri;
    private static WebDriver driver;
    static RegistrationPage registrationPage;


    public static void openTheRegistrationPage() throws InterruptedException{
        uri= ConfigLoader.getProperty("browser.uri");
        driver = SeleniumCommon.openBrowser(uri);
        Thread.sleep(3000);
        registrationPage = new RegistrationPage(driver);
    }

    @When("I enter {string}, {string} and {string} in their fields")
    public void iEnterAndInTheirFields(String name, String email, String password) throws InterruptedException{
        openTheRegistrationPage();
        Thread.sleep(3000);
        registrationPage.enterName(name);
        registrationPage.enterEmail(email);
        registrationPage.enterPassword(password);
    }

    @And("I select {string} from the target dropdown")
    public void iSelectFromTheTargetDropdown(String target) {
        registrationPage.targetDropDown();
        registrationPage.selectTarget(target);
    }

    @And("I select {string} from the preferable activity dropdown")
    public void iSelectFromThePreferableActivityDropdown(String activity) {
        registrationPage.activityDropDown();
        registrationPage.selectActivity(activity);
    }

    @And("I click the register button")
    public void iClickTheRegisterButton() {
        registrationPage.clickOnCreateAccount();
    }


    @Then("I should see the toast message as {string}")
    public void iShouldSeeTheToastMessageAs(String expectedToastMessage) {
        System.out.println(registrationPage.getToastMessage());
        Assert.assertTrue(registrationPage.getToastMessage().contains(expectedToastMessage),
                "Response does not contain the expected message: " + expectedToastMessage);
    }

    @Then("I should see the error message at the password field as {string}")
    public void iShouldSeeTheErrorMessageAsErrorMessage(String errorMessage){
        Assert.assertTrue(registrationPage.getPasswordErrorMessage().contains(errorMessage),
                "Response does not contain the expected message: " + errorMessage);
    }

    @Then("I should see the error message at the email field as {string}")
    public void iShouldSeeTheErrorMessageAtTheEmailFieldAs(String errorMessage) {
        Assert.assertTrue(registrationPage.getEmailErrorMessage().contains(errorMessage),
                "Response does not contain the expected message: "+errorMessage);
    }

    @Then("I should see the error message at the name field as {string}")
    public void iShouldSeeTheErrorMessageAtTheNameFieldAs(String errorMessage) {
        Assert.assertTrue(registrationPage.getNameErrorMessage().contains(errorMessage),
                "Response does not contain the expected message: "+errorMessage);
    }

    @Then("I should see the error message at the target dropdown as {string}")
    public void iShouldSeeTheErrorMessageAtTheTargetDropdownAs(String errorMessage) {
        Assert.assertTrue(registrationPage.getTargetErrorMessage().contains(errorMessage),
                "Response does not contain the expected message: "+errorMessage);
    }

    @And("I should see the error message at the activity dropdown as {string}")
    public void iShouldSeeTheErrorMessageAtTheActivityDropdownAs(String errorMessage) {
        Assert.assertTrue(registrationPage.getActivityErrorMessage().contains(errorMessage),
                "Response does not contain the expected message: "+errorMessage);
    }

    @When("I click the login button")
    public void iClickTheLoginButton() {
        registrationPage.validateThePresenceOfLoginButton();
    }

    @Then("I should directly go to the login page")
    public void iShouldDirectlyGoToTheLoginPage() {
        Assert.assertTrue(driver.getCurrentUrl().contains("login"));
    }

    @After
    public void takeScreenshotOnFailure(Scenario scenario) {
        if (scenario.isFailed()) {
            ScreenshotUtil.takeScreenshot(driver, scenario.getName());
        }
    }
}

  /*  @AfterAll
    public static void closeDriver(){
        DriverSingleton.closeDriver();
    }
}*/
