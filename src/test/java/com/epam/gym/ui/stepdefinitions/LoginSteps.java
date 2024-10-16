package com.epam.gym.ui.stepdefinitions;

import com.epam.gym.browsers.DriverSingleton;
import com.epam.gym.pages.LoginPage;
import com.epam.gym.utils.ConfigLoader;
import com.epam.gym.browsers.SeleniumCommon;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginSteps {
    private LoginPage loginPage;

    @Given("I am on the Login page")
    public void iAmOnTheLoginPage(){
        System.out.println("I am on the Login page");
        String uri = ConfigLoader.getProperty("browser.uri");
        WebDriver driver = DriverSingleton.getDriver();
        loginPage = new LoginPage(driver);
        SeleniumCommon.openBrowser( uri + "login");
    }

    @When("I enter {string} and {string}")
    public void iEnterEmailAndPassword(String email, String password){
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
    }

    @And("I click on the login button")
    public void iClickOnTheLoginButton(){
        loginPage.submit();
    }

    @Then("I should see the {string} in the {string}")
    public void iShouldSeeTheMessage(String message, String locator) throws InterruptedException{
        Thread.sleep(2000);
        if (locator.toLowerCase().equals("toast")){
            WebElement toastElement = loginPage.getToastMessage();
            System.out.println(toastElement.getText());
            Assert.assertEquals(toastElement.getText(), message);
        }
        else if(locator.toLowerCase().equals("PassInline")){
            WebElement passwordElement = loginPage.getPassErrorMessage();
            Assert.assertEquals(passwordElement.getText(), message);
        }
        else if(locator.toLowerCase().equals("EmailInline")){
            WebElement passwordElement = loginPage.getEmailErrorMessage();
            Assert.assertEquals(passwordElement.getText(), message);
        }

    }


    @Then("I should see the Create New Account link")
    public void iShouldSeeTheCreateNewAccountLink() {
        boolean isLinkPresent = loginPage.isCreateAccountLinkPresent();
        Assert.assertTrue(isLinkPresent, "The Create New Account link is not displayed.");
        loginPage.validateThePresenceOfRegistrationButton();
    }
}
