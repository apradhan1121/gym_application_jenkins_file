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

import static org.hamcrest.Matchers.notNullValue;

public class UserDetailsSteps {

    private String endPoint;
    private String userDetailsEndpoint;
    private Response userDetailsResponse;
    private String bearerToken;

    @Given("The APi endpoint is fetched from {string}")
    public void theAPiEndpointIsFetchedFrom(String baseUri) {
        endPoint = ConfigLoader.getProperty(baseUri);
        userDetailsEndpoint = endPoint + "/userdetails";
    }

    @When("The bearer token is present in the response")
    public void theBearerTokenIsPresentInTheResponse() {
        bearerToken = LoginUser.getBearerToken();
    }

    @And("The GET request is sent to fetch the details")
    public void theGETRequestIsSentToFetchTheDetails() {
        userDetailsResponse = RestAssuredCommon.getApiCall(userDetailsEndpoint,bearerToken);
    }

    @Then("The expected status code is {int}")
    public void theExpectedStatusCodeIsStatusCode(int expectedStatusCode) {
        Assert.assertEquals(expectedStatusCode,userDetailsResponse.getStatusCode(),"The status codes does not match!");
    }

    @And("the response content type should be {string}")
    public void theResponseContentTypeShouldBe(String expectedContentType) {
        Assert.assertEquals(userDetailsResponse.getContentType(), expectedContentType, "The content type does not match!");
    }

    @And("the response body should contain the following fields:")
    public void theResponseBodyShouldContainTheFollowingFields(DataTable dataTable) {
        for (String field : dataTable.asList()) {
            userDetailsResponse.then().body(field, notNullValue());
        }
    }

    @When("The GET request is sent to fetch the details with an invalid token")
    public void theGETRequestIsSentToFetchTheDetailsWithAnInvalidToken() {
        String invalidToken = "invalidToken";
        userDetailsResponse = RestAssuredCommon.getApiCall(userDetailsEndpoint, invalidToken);
    }

    @When("The GET request is sent to fetch the details without a token")
    public void theGETRequestIsSentToFetchTheDetailsWithoutAToken() {
        userDetailsResponse = RestAssuredCommon.getApiCall(userDetailsEndpoint, null);
    }
}
