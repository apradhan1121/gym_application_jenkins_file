package com.epam.gym.pages;

import com.epam.gym.utils.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class UpdatePage {
    static WebDriver driver;

    public UpdatePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//div[@class='MuiBox-root css-nbo5uq']//*[name()='svg'])[2]")
     WebElement profileIcon;

    @FindBy(xpath = "(//li[@tabindex=\"-1\"])[1]")
    WebElement updateIcon;

    @FindBy(xpath = "(//p[contains(text(),'My Account')])[1]")
    WebElement myAccount;

    @FindBy(xpath = "//input[@name = 'name']")
    WebElement nameElement;

    @FindBy(id = "mui-component-select-target")
    WebElement targetDropDown;

    @FindBy(css = "#menu-target ul li")
    List<WebElement> targetDropDownOptions;

    @FindBy(id = "mui-component-select-activity")
    WebElement activityDropDown;

    @FindBy(xpath = "//div/p[contains(text(),'Activity is required')]")
    WebElement activityErrorMessage;

    @FindBy(css = "#menu-activity ul li")
    List<WebElement> activityDropDownOptions;

    @FindBy(xpath = "(//button)[3]")
    WebElement submitButton;

    @FindBy(xpath ="//div [@role = \"alert\"]")
    private static WebElement toastElement;



    public void clickProfileIcon()  {
        WaitHelper.waitForElementToBeClickable(profileIcon, 20, driver);
        profileIcon.click();
        WaitHelper.waitForElementToBeClickable(updateIcon, 20, driver);
        updateIcon.click();
    }

    public String validateMyAccount() {
        WaitHelper.waitForElementToBeVisible(driver, myAccount, 20);
        return myAccount.getText();
    }


    public void enterName(String name) {
        WaitHelper.waitForElementToBeClickable(nameElement, 20, driver);
        nameElement.clear();
        nameElement.sendKeys(name);
    }

    public void targetDropDown(){
        WaitHelper.waitForElementToBeClickable(targetDropDown, 20, driver);
        targetDropDown.click();
    }

    public void selectTarget(String target) {
        // Check if target is null or empty
        if (target == null || target.isEmpty()) {
            System.out.println("No target provided, skipping selection.");
            targetDropDown();
            return;  // Exit the method if no target is provided
        }

        // If target is provided, continue with selecting the option
        List<String> optionTexts = new ArrayList<>();
        for (WebElement element : targetDropDownOptions) {
            String optionText = element.getText();
            optionTexts.add(optionText);
            if (optionText.equalsIgnoreCase(target)) {
                element.click();  // Select the target if it matches the dropdown option
                break;
            }
        }
    }


    public void activityDropDown(){
        WaitHelper.waitForElementToBeClickable(activityDropDown, 20, driver);
        activityDropDown.click();
    }

    public void selectActivity(String activity){
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

    public void submit() {
        WaitHelper.waitForElementToBeClickable(submitButton, 20, driver);
        submitButton.click();
    }

    public static WebElement getToastMessage() {
        By.ByXPath toastElement = new By.ByXPath("//div [@role = \"alert\"]");
         WebElement toastMessage = WaitHelper.waitForvisibilityOfElementLocated(toastElement, 20, driver);
        return toastMessage;
    }
}


