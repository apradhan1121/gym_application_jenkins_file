package com.epam.gym.api.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@Test
@CucumberOptions(features = {"src/test/resources/features/api/role_management/gym_coaches.feature"},
                glue = {"com.epam.gym.api.stepdefinitions"},
        plugin = {"pretty", "json:build/reports/cucumber/report.json","io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}
)
public class ApiCoach extends AbstractTestNGCucumberTests {

}
