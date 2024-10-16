package com.epam.gym.utils;

import io.restassured.response.Response;

public class LoginApiUser {
    private static String endPoint;
    private static String loginEndPoint;
    private static Response loginResponse;
    private static String bearerToken;
    private static String username;

    public static String getBearerToken(){
        username = ConfigLoader.getProperty("coachEmail");
        String password = ConfigLoader.getProperty("coachPassword");
        String requestBody = "{\n" +
                "\"username\": \"" + username + "\",\n" +
                "\"password\": \"" + password + "\"\n" +
                "}";
        endPoint = ConfigLoader.getProperty("kubeURI");
        loginEndPoint = endPoint + "/user/login";
        System.out.println("Login endpoint: " + loginEndPoint);
        loginResponse = RestAssuredCommon.postApiCall(loginEndPoint,requestBody);
        System.out.println("Login Response: " + loginResponse.body().prettyPrint());
        bearerToken = loginResponse.jsonPath().getString("data.token");
        return bearerToken;
    }



}
