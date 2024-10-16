package com.epam.gym.pages;

import com.epam.gym.utils.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CoachNavigation {

    WebDriver driver;

    @FindBy(xpath = "//p[text()='Coaches']")
    WebElement coaches;

    public CoachNavigation(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver=driver;
    }

    public CoachesPage navigateToCoachesPage(){
       WaitHelper.waitForElementToBeVisible(driver,coaches,45);
        coaches.click();
        return new CoachesPage(driver);
    }
}
