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

import static org.hamcrest.Matchers.notNullValue;

public class UserWorkoutSteps {

    private String endPoint;
    private String userWorkoutEndpoint;
    private String bookEndpoint;
    private Response bookingResponse;
    private Response userWorkoutResponse;
    private String bearerToken;

    @Given("the base API URL is fetched from {string}")
    public void theBaseAPIURLIsFetchedFrom(String baseUri) {
        endPoint = ConfigLoader.getProperty(baseUri);
    }

    @And("the bearer token is collected from loginUser")
    public void theBearerTokenIsCollectedFromLoginUser() {
        bearerToken = LoginUser.getBearerToken();
    }

    @Given("the api for workouts endpoint is set")
    public void theApiForWorkoutsEndpointIsSet() {
        bookEndpoint = endPoint + "/book";
        userWorkoutEndpoint = endPoint + "/workoutsbyuser/" + LoginUser.getEmail();
    }

    @When("I send a POST request with {string}, {string} and today's date")
    public void iSendAPOSTRequestToBookTheCoachWithAndTodaySDate(String coachEmail, String time) {
        String date = LocalDate.now().plusDays(246).toString();

        String jsonPayload = "{"
                + "\"coachEmail\": \"" + coachEmail + "\","
                + "\"date\": \"" + date + "\","
                + "\"time\": \"" + time + "\""
                + "}";

        bookingResponse = RestAssuredCommon.postApiCall(bookEndpoint, bearerToken, jsonPayload);
        System.out.println(bookingResponse.prettyPrint());

    }

    @Then("the response status code is {int}")
    public void iGetResponseStatusCodeShouldBeStatusCode(int expectedStatusCode) {
        Assert.assertEquals(bookingResponse.getStatusCode(), expectedStatusCode);
    }

    @And("the booking response contains this message {string}")
    public void theBookingResponseContainsThisMessage(String bookingMessage) {
        Assert.assertEquals(bookingResponse.jsonPath().getString("message"), bookingMessage);
    }

    @When("I send a GET request to the user workouts endPoint")
    public void iSendAGETRequestToTheUserWorkoutsEndPoint() {
        userWorkoutResponse = RestAssuredCommon.getApiCall(userWorkoutEndpoint, bearerToken);
        System.out.println(userWorkoutResponse.prettyPrint());
    }

    @And("the response body should contain the following:")
    public void theResponseBodyShouldContainTheFollowing(DataTable dataTable) {
        for (String field : dataTable.asList()) {
            userWorkoutResponse.then().body(field, notNullValue());
        }
    }

    @Then("the response code should be {int}")
    public void theResponseCodeShouldBe(int expectedStatusCode) {
        Assert.assertEquals(expectedStatusCode,userWorkoutResponse.getStatusCode(),"The status codes does not match!");
    }
}
