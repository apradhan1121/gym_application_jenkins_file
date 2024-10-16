package com.epam.gym.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CoachProfilePage {

    WebDriver driver;

    @FindBy(xpath = "//input[@name='name']")
    WebElement yourName;

    @FindBy(xpath = "//input[@id=':r2:']")
    WebElement title;

    @FindBy(xpath = "//div/textarea[@id=':r3:']")
    WebElement about;

    @FindBy(xpath = "//input[@id=':r4:']")
    WebElement specialization;

    @FindBy(xpath = "//button[text()='Select File']")
    WebElement selectFileButton;

    @FindBy(xpath = "//button[text()='Save Changes']")
    WebElement saveChangesButton;

    public CoachProfilePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }

}
