package com.epam.gym.ui.stepdefinitions;

import com.epam.gym.browsers.DriverSingleton;
import com.epam.gym.pages.LoginPage;
import com.epam.gym.pages.UpdatePage;
import com.epam.gym.utils.ConfigLoader;
import com.epam.gym.browsers.SeleniumCommon;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class UpdateProfile {
    WebDriver driver;
    UpdatePage updatePage;
    @Given("I am on the Login Page of the Gym Management Application")
    public void iAmOnTheLoginPageOfTheGymManagementApplication() {
        driver = DriverSingleton.getDriver();
        String URI = ConfigLoader.getProperty("browser.uri");
        String loginURI = URI + "login";
        SeleniumCommon.openBrowser(loginURI);
        updatePage = new UpdatePage(driver);
    }


    @And("I enter email and password")
    public void iEnterEmailAndPassword() throws InterruptedException{
        LoginPage loginPage = new LoginPage(driver);
        String email = ConfigLoader.getProperty("email");
        String password = ConfigLoader.getProperty("password");
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.submit();
        Thread.sleep(3000);
    }


    @Given("I click on the Edit account Profile button")
    public void iClickOnTheEditAccountProfileButton() throws InterruptedException {
        updatePage = new UpdatePage(driver);
        Thread.sleep(3000);
        updatePage.clickProfileIcon();
    }

    @Then("I should navigate to the update profile page")
    public void iShouldNavigateToTheUpdateProfilePage() throws InterruptedException {
        Thread.sleep(2000);
        String profileUpdatePage = updatePage.validateMyAccount();
        Assert.assertEquals(profileUpdatePage, "My Account");

    }

    @And("I enter {string}, {string}, and {string}")
    public void iEnterAnd(String Name, String Your_Target, String Prefarable_Activity) throws InterruptedException{
        Thread.sleep(3000);
        updatePage.enterName(Name);
        if(!Your_Target.isEmpty()){
            updatePage.targetDropDown();
            updatePage.selectTarget(Your_Target);
        }
        System.out.println("Going to activity dropdown");

        if(!Prefarable_Activity.isEmpty()){
            updatePage.activityDropDown();
            updatePage.selectActivity(Prefarable_Activity);
        }
    }

    @And("I click on Save changes button")
    public void iClickOnSaveChangesButton() throws InterruptedException {
        Thread.sleep(2000);
        updatePage.submit();
        Thread.sleep(2000);
    }

    @Then("I should see {string} in the {string}")
    public void iShouldSeeTheMessage(String message, String locator) {
        if (locator.toLowerCase().equals("toast")){
            WebElement toastElement = UpdatePage.getToastMessage();
            Assert.assertEquals(toastElement.getText(), message);
        }

    }
}
