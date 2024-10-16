package com.epam.gym.utils;

import io.restassured.response.Response;

public class LoginApiCoach {
    private static String endPoint;
    private static String loginEndPoint;
    private static Response loginResponse;
    private static String bearerToken;
    private static String username;

    public static String getBearerToken(){
        username = ConfigLoader.getProperty("coachEmail");
        String password = PasswordStore.getCurrentPassword()!=null?PasswordStore.getCurrentPassword():ConfigLoader.getProperty("coachPassword");
        String requestBody = "{\n" +
                "\"username\": \"" + username + "\",\n" +
                "\"password\": \"" + password + "\"\n" +
                "}";
        System.out.println("Login RequestBody is: " + requestBody);
        endPoint = ConfigLoader.getProperty("kubeURI");
        loginEndPoint = endPoint + "/user/login";
        System.out.println("Login endpoint" + loginEndPoint);
        loginResponse = RestAssuredCommon.postApiCall(loginEndPoint,requestBody);
        System.out.println("Login Response" + loginResponse.body().prettyPrint());
        bearerToken = loginResponse.jsonPath().getString("data.token");
        System.out.println(bearerToken);
        return bearerToken;
    }

}
