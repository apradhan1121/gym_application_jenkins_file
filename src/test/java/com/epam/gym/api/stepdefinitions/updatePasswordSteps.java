package com.epam.gym.api.stepdefinitions;

import com.epam.gym.utils.ConfigLoader;
import com.epam.gym.utils.LoginApiCoach;
import com.epam.gym.utils.PasswordStore;
import com.epam.gym.utils.RestAssuredCommon;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

public class updatePasswordSteps {
    String endPoint;
    String updatePasswordEndpoint;
    Response updateResponse;
    String bearerToken;
    String globalJsonPayload;

    @Given("The API endpoint is fetched from properties file {string}")
    public void theAPIEndpointIsFetchedFromPropertiesFile(String KubeURI) {
        endPoint = ConfigLoader.getProperty(KubeURI);
        updatePasswordEndpoint = endPoint + "/user/changePassword";
    }

    @Then("The bearer token is present in the response body of updatePasswordSteps")
    public void theBearerTokenIsPresentInTheResponseBodyOfUpdatePasswordSteps() {
        bearerToken = LoginApiCoach.getBearerToken();
        System.out.println(bearerToken);
    }
    @When("I send a PUT request to updatePassword api with the following JSON payload:")
    public void iSendAPUTRequestToWithTheFollowingJSONPayload(String jsonPayload) {


        String oldPassword = PasswordStore.getCurrentPassword() != null ?
                PasswordStore.getCurrentPassword() :
                ConfigLoader.getProperty("coachPassword");

        jsonPayload = jsonPayload.replace("<oldPassword>", oldPassword);

        globalJsonPayload = jsonPayload;
        System.out.println("update Password endpoint: " + updatePasswordEndpoint);
        System.out.println(jsonPayload);
        updateResponse = RestAssuredCommon.putApiCall(updatePasswordEndpoint,bearerToken, jsonPayload);
        System.out.println("Update Response is:" + updateResponse.getBody().asString());

    }

    @Then("the status code of the update response should be {int}")
    public void theStatusCodeOfTheUpdateResponseShouldBe(int statusCode) {
        Assert.assertEquals(updateResponse.getStatusCode(),statusCode);
    }

    @And("the status message of the update response must be {string}")
    public void theStatusMessageOfTheUpdateResponseMustBe(String message) {
        String updateResponseMessage = updateResponse.getBody().jsonPath().getString("message");
        Assert.assertEquals(updateResponseMessage,message);
        System.out.println("Update Response Message is: " + updateResponseMessage);
        if(updateResponseMessage.equals("Password updated successfully")){
            String newPassword = extractNewPasswordFromJsonPayload();
            System.out.println("New Password is: " + newPassword);
            PasswordStore.setCurrentPassword(newPassword);
        }
    }

    public String extractNewPasswordFromJsonPayload(){
        JsonObject jsonObject = JsonParser.parseString(globalJsonPayload).getAsJsonObject();
        return jsonObject.get("newPassword").getAsString();
    }



}
