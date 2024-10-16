package com.epam.gym.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.epam.gym.utils.WaitHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage {

    private static final Logger log = LoggerFactory.getLogger(LoginPage.class);
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//input[@id='email']")
    private WebElement email;


    @FindBy(xpath = "//input[@id='password']")
    private WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submit;

    @FindBy(xpath ="//div [@role = \"alert\"]")
    private WebElement toastElement;


    @FindBy (xpath = "//p [@id ='password-helper-text']")
    private WebElement invalidPassword;


    @FindBy(xpath = "//span[text()='CREATE NEW ACCOUNT']")
    private WebElement createAccountLink;

    @FindBy(xpath = "//p [text() = 'Invalid email address']")
    private WebElement invalidEmail;

    @FindBy(xpath = "//span[@class=\"MuiTypography-root MuiTypography-body1 css-1tje5hr-MuiTypography-root\"]") //spring
//    @FindBy(xpath = "//span[@class=\"MuiTypography-root MuiTypography-body1 css-v2ydgg\"]") //dagger
    private WebElement loginPage;

    public void openLoginPage(){
        loginPage.click();
    }

    public void enterEmail(String userEmailId){
        WaitHelper.waitForElementToBeClickable(email, 10, driver);
        email.sendKeys(userEmailId);
    }

    public void enterPassword(String userPassword){
        WaitHelper.waitForElementToBeClickable(password, 10, driver);
        password.sendKeys(userPassword);
    }

    public void submit(){
        WaitHelper.waitForElementToBeClickable(submit, 10, driver);
        submit.click();
    }


    public WebElement getToastMessage() {
        return WaitHelper.waitForElementToBeVisible(driver, toastElement, 100);
    }

    public WebElement getPassErrorMessage() {
        return WaitHelper.waitForElementToBeVisible(driver, invalidPassword, 10);

    }

    public WebElement getEmailErrorMessage() {
        return WaitHelper.waitForElementToBeVisible(driver, invalidEmail, 10);

    }

    public boolean isCreateAccountLinkPresent() {
        return createAccountLink.isDisplayed();
    }

    public RegistrationPage validateThePresenceOfRegistrationButton(){
        if(createAccountLink.isDisplayed()) {
            createAccountLink.click();
        }
        else {
            System.out.println("Login button is not displayed");
        }
        return new RegistrationPage(driver);
    }


}
