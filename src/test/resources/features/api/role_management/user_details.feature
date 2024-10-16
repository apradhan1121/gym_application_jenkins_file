@Regression
Feature: Fetching the user details

  Background: The user is authenticated using the valid credentials
    Given The APi endpoint is fetched from "baseURI"
    Then The bearer token is present in the response

  Scenario: Fetching user details with invalid token
    When The GET request is sent to fetch the details with an invalid token
    Then The expected status code is 401

  Scenario: Fetching user details with missing token
    When The GET request is sent to fetch the details without a token
    Then The expected status code is 401

  Scenario Outline: Fetching the details of the user
    When The bearer token is present in the response
    And The GET request is sent to fetch the details
    Then The expected status code is <StatusCode>
    And the response content type should be "application/json"
    And the response body should contain the following fields:
      | preferableActivity |
      | role               |
      | name               |
      | email              |
      | target             |
    Examples:
      | StatusCode |
      | 200        |
