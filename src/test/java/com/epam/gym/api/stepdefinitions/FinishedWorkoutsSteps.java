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

public class FinishedWorkoutsSteps {

    private String token;
    String endPoint;
    private String bookEndpoint;
    private String feedbackEndpoint;
    private Response coachResponse;
    private Response bookingResponse;
    private Response feedbackResponse;
    private String workoutId;
    private Response response;
    private String finishedWorkoutsEndpoint;
    private Response finishedWorkoutsResponse;

    @Given("The bookcoach api is present with the {string}")
    public void theBookcoachApiIsPresentInThe(String baseuri) {
        endPoint = ConfigLoader.getProperty(baseuri);
    }

    @And("then the bearer token is fetched from the login response")
    public void theBearerTokenIsFetchedFromTheLoginResponse() {
        token = LoginUser.getBearerToken();
    }

    @When("I send a POST request to book the coach with {string}, {string} and today's date")
    public void iSendAPOSTRequestWithPayload(String coachEmail, String time) {
        bookEndpoint = endPoint + "/book";
        // Get the current date
        String date = LocalDate.now().plusDays(294).toString();

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

    @Then("I get response status code should be {int}")
    public void iGetTheResponseStatusCodeShouldBe(int expectedStatusCode) {
        Assert.assertEquals(bookingResponse.getStatusCode(), expectedStatusCode);
    }

    @Then("I capture the workoutId from response")
    public void iCaptureTheWorkoutIdFromTheResponse() {
        // WorkoutId already captured in the POST request
    }

    @Then("the booking response should contain the {string}")
    public void theBookingResponseShouldContain(String bookingMessage) {
        Assert.assertEquals(bookingResponse.jsonPath().getString("message"), bookingMessage);
    }

    @When("I submit feedback for the given workout")
    public void iSubmitFeedbackForTheWorkout(String jsonPayload) {
        feedbackEndpoint = endPoint + "/postfeedback/" + workoutId;
        feedbackResponse = RestAssuredCommon.patchApiCall(feedbackEndpoint, token, jsonPayload);
    }

    @Then("the feedback response should have the status code as {int}")
    public void theFeedbackResponseStatusCodeIs(int feedbackStatusCode) {
        Assert.assertEquals(feedbackResponse.getStatusCode(), feedbackStatusCode);
    }

    @Then("the feedback response should contain the {string}")
    public void theFeedbackResponseShouldContain(String feedbackMessage) {
        Assert.assertEquals(feedbackResponse.getBody().asString(), feedbackMessage);
    }

    @When("I send a GET request to check finished workouts for the coach {string}")
    public void iSendAGETRequestToCheckFinishedWorkouts(String coachEmail) {
        finishedWorkoutsEndpoint = endPoint + "/finishedworkouts/" + coachEmail;
        finishedWorkoutsResponse = RestAssuredCommon.getApiCall(finishedWorkoutsEndpoint, token);
        System.out.println("finishedWorkoutsResponse is:" +  finishedWorkoutsResponse.prettyPrint());
    }

    @Then("I get the response status code for finished workouts should be {int}")
    public void iGetTheResponseStatusCodeForFinishedWorkoutsShouldBe(int expectedStatusCode) {
        Assert.assertEquals(finishedWorkoutsResponse.getStatusCode(), expectedStatusCode);
    }

    @Then("the finished workouts response should contain {string} for the status")
    public void theFinishedWorkoutsResponseShouldContain(String statusValue) {
        String actualStatusValue = finishedWorkoutsResponse.jsonPath().getString("statusValue");
        System.out.println(actualStatusValue);
        Assert.assertTrue(actualStatusValue.contains(statusValue));
    }
}
