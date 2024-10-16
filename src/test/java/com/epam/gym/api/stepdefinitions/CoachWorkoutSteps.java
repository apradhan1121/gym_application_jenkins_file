package com.epam.gym.api.stepdefinitions;

import com.epam.gym.utils.ConfigLoader;
import com.epam.gym.utils.LoginApiUser;
import com.epam.gym.utils.RestAssuredCommon;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.notNullValue;

public class CoachWorkoutSteps {

    private String endPoint;
    private String coachWorkoutEndpoint;
    private Response coachWorkoutResponse;
    private String bearerToken;

    @Given("The API endpoint is fetched from properties {string}")
    public void theAPIEndpointIsFetchedFromProperties(String kubeURI) {
        endPoint = ConfigLoader.getProperty(kubeURI);
        coachWorkoutEndpoint = endPoint + "/coaches";

    }

    @Then("The bearer token is fetched from login API user response")
    public void theBearerTokenIsPresentInTheResponseBody() {
        bearerToken = LoginApiUser.getBearerToken();
        System.out.println(bearerToken);
    }

    @When("I send a GET request to coach details endpoint")
    public void iSendAGETRequestTo() {
        coachWorkoutResponse = RestAssuredCommon.getApiCall(coachWorkoutEndpoint, bearerToken);
        System.out.println(coachWorkoutResponse.getBody().asString());
    }

    @Then("The expected status code should be {int}")
    public void theExpectedStatusCodeShouldBe(int expectedStatusCode) {
        Assert.assertEquals(coachWorkoutResponse.getStatusCode(), expectedStatusCode, "The status codes do not match!");
    }

    @And("the response type must be {string}")
    public void theResponseShouldContainAListOfCoaches(String expectedContentType) {
        Assert.assertEquals(coachWorkoutResponse.getContentType(), expectedContentType, "The content type does not match!");
    }

    @And("each coach should have valid attributes:")
    public void eachCoachShouldHaveValidAttributes(DataTable dataTable) {
        for (String field : dataTable.asList()) {
            coachWorkoutResponse.then().body(field, notNullValue());
        }
    }

    @And("each coach should have non-empty client reviews")
    public void eachCoachShouldHaveNonEmptyClientReviews() {
        coachWorkoutResponse.then().body("clientReviews", everyItem(not(empty())));
    }

    @And("the ratings for each coach should be between {int} and {int}")
    public void theRatingsForEachCoachShouldBeBetweenAnd(int min, int max) {
        coachWorkoutResponse.then().body("ratings", everyItem(allOf(greaterThanOrEqualTo((float) min), lessThanOrEqualTo((float) max))));
    }

    @When("The GET request is sent to fetch the coach workout details with an invalid token")
    public void theGETRequestIsSentToFetchTheCoachWorkoutDetailsWithAnInvalidToken() {
        String invalidToken = "invalidToken";
        coachWorkoutResponse = RestAssuredCommon.getApiCall(coachWorkoutEndpoint, invalidToken);

    }

    @When("The GET request is sent to fetch the coach workout details without a token")
    public void theGETRequestIsSentToFetchTheCoachWorkoutDetailsWithoutAToken() {
        coachWorkoutResponse = RestAssuredCommon.getApiCall(coachWorkoutEndpoint, null);

    }
}
