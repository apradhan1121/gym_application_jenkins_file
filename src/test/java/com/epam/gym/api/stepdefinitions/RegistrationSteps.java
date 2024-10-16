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

//import static com.mysql.cj.conf.PropertyKey.logger;

public class RegistrationSteps {

    private String endPoint;
    private Response response;
    private static final Logger logger = LogManager.getLogger(RegistrationSteps.class);
    private String registrationEndPoint;

    @Given("the API endpoint is loaded from {string}")
    public void theApiEndpointIsLoadedFrom(String propertyKey) {
        endPoint = ConfigLoader.getProperty(propertyKey);
        registrationEndPoint = endPoint + "/signup";
    }

    @When("I send a POST request with the following JSON:")
    public void iSendAPostRequestWithTheFollowingJson(String jsonPayload) {
        logger.info("Sending POST request to: " + registrationEndPoint);
        logger.debug("Request Payload: " + jsonPayload);
        System.out.println(jsonPayload);
        response = RestAssuredCommon.postApiCall(registrationEndPoint, jsonPayload);
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        System.out.println("Response is:"+ response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode, "Status code does not match!");
    }


    @And("the response should contain {string}")
    public void theResponseShouldContain(String expectedMessage) {
        System.out.println(response.getBody().asString());
        Assert.assertTrue(response.getBody().asString().contains(expectedMessage),
                "Response does not contain the expected message: " + expectedMessage);
    }
    
}
