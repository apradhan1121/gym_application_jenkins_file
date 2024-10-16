package com.epam.gym.api.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/test/resources/features/api/workout_logging/log_workouts.feature"},
        glue = {"com.epam.gym.api.stepdefinitions"},
        plugin = {"pretty", "json:build/reports/cucumber/report.json","io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}
)
public class ApiLogWorkouts extends AbstractTestNGCucumberTests {
}
