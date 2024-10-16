package com.epam.gym.pages;

import com.epam.gym.utils.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class RegistrationPage {

    WebDriver driver;

    @FindBy(xpath = "//input[@name='name']")
    WebElement yourName;

    @FindBy(xpath = "//div/p[@id=':r0:-helper-text']")
    WebElement nameErrorMessage;

    @FindBy(xpath = "//input[@name='email']")
    WebElement yourEmail;

    @FindBy(xpath = "//div/p[@id=':r1:-helper-text']")
    WebElement emailErrorMessage;

    @FindBy(xpath = "//input[@name='password']")
    WebElement yourPassword;

    @FindBy(xpath = "//div/p[@id=':r2:-helper-text']")
    WebElement passwordErrorMessage;

    @FindBy(id = "mui-component-select-target")
    WebElement targetDropDown;

    @FindBy(css = "#menu-target ul li")
    List<WebElement> targetDropDownOptions;

    @FindBy(xpath = "//div/p[contains(text(),'Target is required')]")
    WebElement targetErrorMessage;

    @FindBy(id = "mui-component-select-activity")
    WebElement activityDropDown;

    @FindBy(xpath = "//div/p[contains(text(),'Activity is required')]")
    WebElement activityErrorMessage;

    @FindBy(css = "#menu-activity ul li")
    List<WebElement> activityDropDownOptions;

    @FindBy(xpath = "//form/button")
    WebElement createAccountButton;

    @FindBy(xpath = "//span[text()='LOGIN HERE']")
    WebElement loginButton;

    @FindBy(xpath ="//div [@role = \"alert\"]")
    private WebElement toastElement;


    public RegistrationPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }


    public void enterName(String name){
        yourName.clear();
        WaitHelper.waitForElement(yourName,1000,driver);
        yourName.sendKeys(name);
    }

    public void enterEmail(String email){
        yourEmail.clear();
        WaitHelper.waitForElement(yourEmail,1000,driver);
        yourEmail.sendKeys(email);
    }

    public void enterPassword(String password){
        yourPassword.clear();
        yourPassword.sendKeys(password);
    }

    public void targetDropDown(){
        targetDropDown.click();
    }

    public void selectTarget(String target){
        WaitHelper.waitForElementToBeVisible(driver, targetDropDownOptions.get(0), 20);
        List<String> optionTexts = new ArrayList<>();
        for(WebElement element: targetDropDownOptions){
            String optionText = element.getText();
            optionTexts.add(optionText);
            if (optionText.equalsIgnoreCase(target)) {
                element.click();
                break;
            }
        }
    }

    public void activityDropDown(){
        activityDropDown.click();
    }

    public void selectActivity(String activity){
        WaitHelper.waitForElementToBeVisible(driver,activityDropDownOptions.get(0),20);
        List<String> optionTexts = new ArrayList<>();
        for(WebElement element: activityDropDownOptions){
            String optionText = element.getText();
            optionTexts.add(optionText);
            if (optionText.equalsIgnoreCase(activity)) {
                element.click();
                break;
            }
        }
    }

    public void clickOnCreateAccount(){
        createAccountButton.click();
    }

    public String getPasswordErrorMessage() {
        return passwordErrorMessage.getText();
    }

    public String getEmailErrorMessage(){
        return emailErrorMessage.getText();
    }

    public String getNameErrorMessage(){
        return nameErrorMessage.getText();
    }

    public String getTargetErrorMessage(){
        return targetErrorMessage.getText();
    }

    public String getActivityErrorMessage(){
        return activityErrorMessage.getText();
    }

    public LoginPage navigateToLoginPage(){
        return new LoginPage(driver);
    }

    public LoginPage validateThePresenceOfLoginButton(){
        if(loginButton.isDisplayed()) {
            loginButton.click();
        }
        else {
            System.out.println("Login button is not displayed");
        }
        return new LoginPage(driver);
    }

    public String getToastMessage() {
         WaitHelper.waitForElementToBeVisible(driver, toastElement, 15);
         return toastElement.getText();
    }

}
