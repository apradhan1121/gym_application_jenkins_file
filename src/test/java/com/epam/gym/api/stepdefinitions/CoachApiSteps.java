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

import java.util.List;
import java.util.Map;

public class CoachApiSteps {

    private String token;
    private Response response;
    private String coachesEndpoint;

    @Given("The bookcoach api is fetched from the {string}")
    public void theBookcoachApiIsFetchedFromThe(String baseUri) {
        String endPoint = ConfigLoader.getProperty(baseUri);
        coachesEndpoint = endPoint + "/coaches";
    }

    @And("the response has a bearer token")
    public void theResponseHasABearerToken() {
        token = LoginUser.getBearerToken();
    }
    @When("the user requests the list of coaches")
    public void theUserRequestsTheListOfCoaches() {
        response = RestAssuredCommon.getApiCall(coachesEndpoint, token);
    }

    @Then("the response status code should get {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode, "Unexpected status code");
    }

    @Then("the response should contain the following coac" +
            "hes:")
    public void theResponseShouldContainTheFollowingCoaches(List<Map<String, String>> expectedCoaches) {
        List<Map<String, String>> actualCoaches = response.jsonPath().getList("$");

        for (Map<String, String> expectedCoach : expectedCoaches) {
            boolean matchFound = actualCoaches.stream().anyMatch(actualCoach ->
                    actualCoach.get("Name").equals(expectedCoach.get("Name")) &&
                            actualCoach.get("email").equals(expectedCoach.get("email")) &&
                            actualCoach.get("Ratings").equals(expectedCoach.get("Ratings")) &&
                            actualCoach.get("Expertise").equals(expectedCoach.get("Expertise"))
            );
            Assert.assertTrue(matchFound, "Coach not found: " + expectedCoach);
        }
    }

    @Then("each coach should have a ProfilePictureUrl, Summary, and ClientReviews")
    public void eachCoachShouldHaveProfilePictureUrlSummaryAndClientReviews() {
        List<Map<String, Object>> coaches = response.jsonPath().getList("$");

        for (Map<String, Object> coach : coaches) {
            Assert.assertNotNull(coach.get("ProfilePictureUrl"), "ProfilePictureUrl should not be null");
            Assert.assertNotNull(coach.get("Summary"), "Summary should not be null");
            Assert.assertNotNull(coach.get("ClientReviews"), "ClientReviews should not be null");
        }
    }

}
