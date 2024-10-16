package com.epam.gym.api.stepdefinitions;

import com.epam.gym.utils.LoginUser;
import com.epam.gym.utils.ConfigLoader;
import com.epam.gym.utils.RestAssuredCommon;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.util.Map;

public class UpdateSteps {
    private String endPoint;
    private String loginEndpoint;
    private String updateEndPoint;
    private Response loginResponse;
    private Response updateResponse;
    private String bearerToken;
    private static final Logger logger = LogManager.getLogger(UpdateSteps.class);


    @Given("the API endpoint is present at {string}")
    public void theAPIEndpointIsPresentAt(String BaseUri) {
        endPoint= ConfigLoader.getProperty(BaseUri);
        updateEndPoint = endPoint + "/update";
    }

    @And("the response contains a valid bearer token")
    public void theResponseContainsAValidBearerToken() {
        bearerToken = LoginUser.getBearerToken();
    }

    @When("I use the bearer token to send a PUT request to update user details with the following JSON payload:")
    public void iUseTheBearerTokenToSendAPUTRequestToUpdateUserDetailsWithTheFollowingJSONPayload(String jsonPayload) {
        if (bearerToken == null || bearerToken.isEmpty()) {
            throw new IllegalStateException("Bearer token is missing. Ensure the user is authenticated.");
        }
        updateResponse=RestAssuredCommon.putApiCall(updateEndPoint,bearerToken,jsonPayload);

    }

    @When("I use the bearer token to send a PUT request to update user details with an empty JSON payload:")
    public void iUseTheBearerTokenToSendAPUTRequestToUpdateUserDetailsWithAnEmptyJSONPayload(String jsonPayload) {
        if (bearerToken == null || bearerToken.isEmpty()) {
            throw new IllegalStateException("Bearer token is missing. Ensure the user is authenticated.");
        }
        updateResponse=RestAssuredCommon.putApiCall(updateEndPoint,bearerToken,jsonPayload);
    }

    @Then("the status code of the response must be {int}")
    public void theStatusCodeOfTheResponseMustBeStatusCode(int expectedStatusCode) {
        Assert.assertEquals(updateResponse.getStatusCode(), expectedStatusCode, "Status code does not match!");
    }


    @And("the status message of the response should be {string}")
    public void theStatusMessageOfTheResponseShouldBeMessage(String expectedMessage) {
        Assert.assertTrue(updateResponse.getBody().asString().contains(expectedMessage),
                "Response does not contain the expected message: " + expectedMessage);
    }

}
