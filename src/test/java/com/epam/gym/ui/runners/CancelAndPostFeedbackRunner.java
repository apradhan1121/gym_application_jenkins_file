package com.epam.gym.ui.runners;


import com.epam.gym.utils.ConfigLoader;
import com.epam.gym.utils.RestAssuredCommon;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.restassured.response.Response;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/resources/features/ui/role_management/cancelWorkout.feature",
        glue = {"com.epam.gym.ui.stepdefinitions"},
        plugin = {"pretty", "json:build/reports/cucumber/report.json","io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}
)

public class CancelAndPostFeedbackRunner extends AbstractTestNGCucumberTests {

    /*private static String endPoint;
    private static String loginEndPoint;
    private static Response loginResponse;
    private static String bearerToken;
    private static String username;

    @Test
    public static String getBearerToken(){
        username = ConfigLoader.getProperty("email");
        String password = ConfigLoader.getProperty("password");
        String requestBody = "{\n" +
                "\"username\": \"" + username + "\",\n" +
                "\"password\": \"" + password + "\"\n" +
                "}";
        endPoint = ConfigLoader.getProperty("kubeURI");
        loginEndPoint = endPoint + "/user/login";
        System.out.println("Login endpoint" + loginEndPoint);
        loginResponse = RestAssuredCommon.postApiCall(loginEndPoint,requestBody);
        System.out.println("Login Response" + loginResponse.body().prettyPrint());
        bearerToken = loginResponse.jsonPath().getString("id_token");
        System.out.println(bearerToken);
        return bearerToken;
    }*/

}
