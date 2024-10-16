package com.epam.gym.utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class RestAssuredCommon {

    public static Response postApiCall(String endpoint, String jsonPayload) {
        return given().contentType(ContentType.JSON)
                .body(jsonPayload)
                .when()
                .post(endpoint);
    }

    public static Response postApiCall(String endpoint,String bearerToken, String jsonPayload){
        return given()
                .contentType("application/json").header("Authorization","Bearer " + bearerToken)
                .body(jsonPayload)
                .when()
                .post(endpoint);
    }
    public static Response putApiCall(String endpoint, String bearerToken,String jsonPayload) {
        return given()
                .contentType("application/json").header("Authorization", "Bearer " + bearerToken)
                .body(jsonPayload)
                .when()
                .put(endpoint);
    }

    public static Response patchApiCall(String endpoint, String bearerToken,String jsonPayload) {
        return given()
                .contentType("application/json").header("Authorization", "Bearer " + bearerToken)
                .body(jsonPayload)
                .when()
                .patch(endpoint);
    }

    public static Response getApiCall(String endpoint, String bearerToken) {
        return given()
                .contentType("application/json").header("Authorization", "Bearer " + bearerToken)
                .when()
                .get(endpoint);
    }





}
