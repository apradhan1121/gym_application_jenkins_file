package com.epam.gym.api.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = {"src/test/resources/features/api/role_management/user_details.feature"},
        glue = {"com.epam.gym.api.stepdefinitions"},
        plugin = {"pretty", "json:build/reports/cucumber/report.json","io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}
)
public class ApiUserDetails extends AbstractTestNGCucumberTests{
}
