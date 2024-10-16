package com.epam.gym.ui.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = {"src/test/resources/features/ui/user_management/registrationPage.feature"}, glue = {"com.epam.gym.ui.stepdefinitions"},
        plugin = {"pretty", "json:build/reports/cucumber/report.json","io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"})
public class UiRegistration extends AbstractTestNGCucumberTests {
}
