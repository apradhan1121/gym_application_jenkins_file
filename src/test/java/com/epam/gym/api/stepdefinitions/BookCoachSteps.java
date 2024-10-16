package com.epam.gym.api.stepdefinitions;

import com.epam.gym.utils.ConfigLoader;
import com.epam.gym.utils.LoginUser;
import com.epam.gym.utils.RestAssuredCommon;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class BookCoachSteps {

    private String token;
    private String bookEndpoint;
    private Response coachResponse;
    private Response bookingResponse;

    @Given("The bookcoach api is loaded with the {string}")
    public void theBookCoachApiIsLoadedWithTheBaseURI(String baseURI) {
        bookEndpoint = ConfigLoader.getProperty(baseURI) + "/book";
    }


    @And("the response consists of a bearer token")
    public void theResponseConsistsOfABearerToken() {
        token = LoginUser.getBearerToken();
    }

    @When("I send a POST request to the book the coach with {string}, {string}, and today's date")
    public void iSendAPOSTRequestsWithPayload(String coachEmail, String time) {
        // Get the current date
        String date = LocalDate.now().plusDays(219).toString();

        // Define the JSON payload dynamically
        String jsonPayload = "{"
                + "\"coachEmail\": \"" + coachEmail + "\","
                + "\"date\": \"" + date + "\","
                + "\"time\": \"" + time + "\""
                + "}";

        // Send the POST request
        bookingResponse = RestAssuredCommon.postApiCall(bookEndpoint, token, jsonPayload);
        // Extract the workoutId from the response
        String workoutId = bookingResponse.jsonPath().getString("workoutId");
    }

    @Then("the response status code should be as {int}")
    public void theResponseStatusCodeShouldBeAs(int statuscode) {
        // Verify the response status code
        Assert.assertEquals(bookingResponse.getStatusCode(), statuscode, "Status code does not match!");
    }

    @And("the response should {string}")
    public void theResponseShouldContainAs(String message) {
        Assert.assertTrue(bookingResponse.getBody().asString().contains(message),
                "Response does not contain the expected message: " + message);
    }

}
