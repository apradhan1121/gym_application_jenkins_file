package com.epam.gym.utils;

import io.restassured.response.Response;

public class LoginUser {
    private static String endPoint;
    private static String loginEndPoint;
    private static Response loginResponse;
    private static String bearerToken;
    private static String email;
    public static String getBearerToken(){
        email = ConfigLoader.getProperty("email");
        String password = ConfigLoader.getProperty("password");
        String requestBody = "{\n" +
                "\"email\": \"" + email + "\",\n" +
                "\"password\": \"" + password + "\"\n" +
                "}";
        endPoint = ConfigLoader.getProperty("baseURI");
        loginEndPoint = endPoint + "/signin";
        System.out.println("Login endpoint" + loginEndPoint);
        loginResponse = RestAssuredCommon.postApiCall(loginEndPoint,requestBody);
        System.out.println("Login Response" + loginResponse.body().prettyPrint());
        bearerToken = loginResponse.jsonPath().getString("id_token");
        System.out.println(bearerToken);
    return bearerToken;
    }

    public static String getEmail() {
        return email;
    }
}
