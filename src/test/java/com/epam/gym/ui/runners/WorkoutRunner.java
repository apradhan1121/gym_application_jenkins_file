package com.epam.gym.ui.runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/ui/user_management/Workouts.feature",
        glue = {"com.epam.gym.ui.stepdefinitions"},
        plugin = {"pretty", "json:build/reports/cucumber/report.json","io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}
)
public class WorkoutRunner extends AbstractTestNGCucumberTests {

}
