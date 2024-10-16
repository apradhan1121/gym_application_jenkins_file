package com.epam.gym.api.stepdefinitions;

import com.epam.gym.utils.ConfigLoader;
import com.epam.gym.utils.LoginUser;
import com.epam.gym.utils.RestAssuredCommon;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

import java.time.LocalDate;

public class CancelCoachSteps {
    private String bookEndpoint;
    private String endPoint;
    private String sessionId;
    private String cancelEndpoint;
    private Response bookingResponse;
    private Response cancelResponse;
    private String token;
    private String workoutId;

    @Given("The bookcoach api is received from the {string}")
    public void theBookcoachApiIsReceivedFromThe(String baseURI) {
        endPoint = ConfigLoader.getProperty(baseURI);
    }

    @And("the response consists of the bearer token")
    public void theResponseConsistsOfTheBearerToken() {
        token = LoginUser.getBearerToken();
    }

    // Step to send POST request to book the coach
    @When("I send a POST requests to book the coach with {string}, {string}, and today's date")
    public void iSendAPOSTRequestsWithPayload(String coachEmail, String time) {
        bookEndpoint = endPoint + "/book";
        // Get the current date
        String date = LocalDate.now().plusDays(437).toString();

        // Define the JSON payload dynamically
        String jsonPayload = "{"
                + "\"coachEmail\": \"" + coachEmail + "\","
                + "\"date\": \"" + date + "\","
                + "\"time\": \"" + time + "\""
                + "}";

        // Send the POST request
        bookingResponse = RestAssuredCommon.postApiCall(bookEndpoint, token, jsonPayload);
        // Extract the workoutId from the response
        workoutId = bookingResponse.jsonPath().getString("workoutId");
    }

    // Step to verify the booking response status code
    @Then("I get the booking response status code as {int}")
    public void iGetTheBookingResponseStatusCodes(int statusCode) {
        Assert.assertEquals(bookingResponse.getStatusCode(), statusCode, "Booking status code did not match");
    }

    // Step to capture sessionId (workoutId) from the booking response
    @Then("I capture the sessionId from the response")
    public void iCaptureTheSessionIdFromTheResponse() {
        sessionId = bookingResponse.jsonPath().getString("workoutId");
        Assert.assertNotNull(sessionId, "Session ID should not be null");
    }

    // Step to verify booking message
    @Then("the booking response should contain the message {string}")
    public void theBookingResponseShouldConatinTheMessage(String expectedMessage) {
        String actualMessage = bookingResponse.getBody().asString();
        Assert.assertTrue(actualMessage.contains(expectedMessage), "Booking message did not match");
    }

    // Step to submit the cancel request using the captured sessionId
    @When("I submit the cancel request using the captured sessionId")
    public void iSubmitTheCancelRequestUsingTheCapturedSessionId() {
        cancelEndpoint = endPoint + "/cancel/" + sessionId;
        cancelResponse = RestAssuredCommon.patchApiCall(cancelEndpoint, token, "");
    }

    // Step to verify the cancel response status code
    @Then("I get response status code as {int}")
    public void iGetResponseStatusCodeAs(int statusCode) {
        Assert.assertEquals(cancelResponse.getStatusCode(), statusCode, "Cancel status code did not match");
    }

    // Step to verify the cancel response message
    @Then("I verify the response contains the specified cancel message {string}")
    public void iVerifyTheResponseContainsTheSpecifiedCancelMessage(String expectedMessage) {
        String actualMessage = cancelResponse.getBody().asString();
        Assert.assertTrue(actualMessage.contains(expectedMessage), "Cancel response message did not match");
    }

}
