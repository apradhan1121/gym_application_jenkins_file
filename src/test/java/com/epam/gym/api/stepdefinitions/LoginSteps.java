package com.epam.gym.api.stepdefinitions;

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

public class LoginSteps {
    private String endPoint;
    private String loginEndpoint;
    private Response response;
    private static final Logger logger = LogManager.getLogger(LoginSteps.class);

    @Given("the API endpoint is available at {string}")
    public void theAPIEndpointIsAvailableAt(String baseUri) {
        endPoint=ConfigLoader.getProperty(baseUri);
        loginEndpoint =endPoint + "/signin";
    }

    @When("I send a POST request to the login API with the following JSON payload:")
    public void iSendAPOSTRequestToTheLoginAPIWithTheFollowingJSONPayload(String jsonPayload) {
        logger.info("Sending POST request to: " + loginEndpoint);
        logger.debug("Request Payload: " + jsonPayload);
        System.out.println(jsonPayload);
        response = RestAssuredCommon.postApiCall(loginEndpoint, jsonPayload);
        System.out.println(response.getBody().asString());
        System.out.println(response.getStatusCode());
    }

    @Then("the status code of the response should be {int}")
    public void theStatusCodeOfTheResponseShouldBeStatusCode(int expectedStatusCode) {
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode, "Status code does not match!");
    }

    @And("the status message of the response must be {string}")
    public void theStatusMessageOfTheResponseMustBeMessage(String expectedMessage) {
        Assert.assertTrue(response.getBody().asString().contains(expectedMessage),
                "Response does not contain the expected message: " + expectedMessage);
    }
}
