package com.epam.gym.smoketest.runners;

import com.epam.gym.pages.BookWorkOutPage;
import com.epam.gym.pages.CoachNavigation;
import com.epam.gym.pages.CoachesPage;
import com.epam.gym.pages.LoginPage;
import com.epam.gym.utils.ConfigLoader;
import com.epam.gym.browsers.SeleniumCommon;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/resources/features/smoketest/gym_bookandfinishworkout.feature",
        glue = {"com.epam.gym.smoketest.stepdefinitions"},
        plugin = {"pretty", "json:build/reports/cucumber/report.json","io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"})
public class BookAndFinishWorkout extends AbstractTestNGCucumberTests {
    }
