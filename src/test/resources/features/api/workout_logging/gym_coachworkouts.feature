Feature: Validate coach workout details API

  Background: The user is authenticated using the valid credentials
    Given The API endpoint is fetched from properties "kubeURI"
    Then The bearer token is fetched from login API user response

  Scenario Outline: Fetch all coach details with a valid bearer token
    When I send a GET request to coach details endpoint
    Then The expected status code should be <statusCode>
    And the response type must be "application/json"
    And each coach should have valid attributes:
      | attribute         |
      | id                |
      | email             |
      | name              |
      | expertise         |
      | profilePictureUrl |
      | ratings           |
      | summary           |
      | title             |
    And each coach should have non-empty client reviews
    And the ratings for each coach should be between 1 and 5

    Examples:
        | statusCode |
        | 200        |

  Scenario: Fetching user details with invalid token
    When The GET request is sent to fetch the coach workout details with an invalid token
    Then The expected status code should be 401

  Scenario: Fetching user details with missing token
    When The GET request is sent to fetch the coach workout details without a token
    Then The expected status code should be 401