package com.epam.gym.api.stepdefinitions;

import com.epam.gym.utils.LoginUser;
import com.epam.gym.utils.ConfigLoader;
import com.epam.gym.utils.RestAssuredCommon;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

public class LogWorkoutsSteps {

    private String endPoint;
    private String logWorkoutendpoint;
    private Response logWorkoutsResponse;
    private String bearerToken;


    @Given("The API endPoint is obtained from {string} and is set")
    public void theAPIEndPointIsObtainedFromAndIsSet(String baseUri) {
        endPoint = ConfigLoader.getProperty(baseUri);
        logWorkoutendpoint = endPoint + "/logWorkout";
    }

    @And("The bearer token is obtained from the LoginUser class")
    public void theBearerTokenIsObtainedFromTheLoginUserClass() {
        bearerToken = LoginUser.getBearerToken();
    }

    @When("the user sends a POST request with the above JSON payload")
    public void theUserSendsAPOSTRequestWithTheAboveJSONPayload(String jsonPayload) {
        if (bearerToken == null || bearerToken.isEmpty()) {
            throw new IllegalStateException("Bearer token is missing. Ensure the user is authenticated.");
        }
        logWorkoutsResponse=RestAssuredCommon.postApiCall(logWorkoutendpoint,bearerToken,jsonPayload);
    }

    @Then("the status code of the response is expected to be {int}")
    public void theStatusCodeOfTheResponseIsExpectedToBeStatusCode(int expectedStatusCode) {
        Assert.assertEquals(logWorkoutsResponse.getStatusCode(), expectedStatusCode, "Status code does not match!");
    }

    @And("the response message should be {string}")
    public void theResponseMessageShouldBeMessage(String expectedMessage) {
        Assert.assertTrue(logWorkoutsResponse.getBody().asString().contains(expectedMessage),
                "Response does not contain the expected message: " + expectedMessage);
    }
}
