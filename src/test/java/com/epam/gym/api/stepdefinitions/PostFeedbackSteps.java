package com.epam.gym.api.stepdefinitions;

import com.epam.gym.utils.ConfigLoader;
import com.epam.gym.utils.LoginUser;
import com.epam.gym.utils.RestAssuredCommon;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import static io.restassured.RestAssured.*;
import java.time.LocalDate;

public class PostFeedbackSteps {

    private String token;
    String endPoint;
    private String bookEndpoint;
    private String feedbackEndpoint;
    private Response coachResponse;
    private Response bookingResponse;
    private Response feedbackResponse;
    private String workoutId;
    private Response response;

    @Given("The bookcoach api is present in the {string}")
    public void theBookcoachApiIsPresentInThe(String baseuri) {
        endPoint = ConfigLoader.getProperty(baseuri);
    }

    @And("the bearer token is fetched from the login response")
    public void theBearerTokenIsFetchedFromTheLoginResponse() {
        token = LoginUser.getBearerToken();
    }

    @When("I send a POST request to book the coach with {string}, {string}, and today's date")
    public void iSendAPOSTRequestWithPayload(String coachEmail, String time) {
        bookEndpoint = endPoint + "/book";
        // Get the current date
        String date = LocalDate.now().plusDays(289).toString();

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

    @Then("I get the response status code should be {int}")
    public void iGetTheResponseStatusCodeShouldBe(int expectedStatusCode) {
        Assert.assertEquals(bookingResponse.getStatusCode(),expectedStatusCode);
    }

    @Then("I capture the workoutId from the response")
    public void iCaptureTheWorkoutIdFromTheResponse() {
        // Assuming the workoutId is already captured in the POST request
    }

    @Then("the booking response should contain {string}")
    public void theBookingResponseShouldContain(String bookingMessage) {
        Assert.assertEquals(bookingResponse.jsonPath().getString("message"),bookingMessage);
    }

    @When("I submit feedback for the workout")
    public void iSubmitFeedbackForTheWorkout(String jsonPayload) {
        feedbackEndpoint = endPoint +"/postfeedback/"+ workoutId;
        feedbackResponse = RestAssuredCommon.patchApiCall(feedbackEndpoint, token, jsonPayload);
    }


    @Then("the feedback response status code is {int}")
    public void theFeedbackResponseStatusCodeIs(int feedbackStatusCode) {
        Assert.assertEquals(feedbackResponse.getStatusCode(),feedbackStatusCode);
    }

    @Then("the feedback response should contain {string}")
    public void theFeedbackResponseShouldContain(String feedbackMessage) {
        Assert.assertEquals(feedbackResponse.getBody().asString(),feedbackMessage);
    }
}
