Feature: User Login API Testing

  @Regression
  Scenario Outline: Validate login API with various input parameters
    Given the API endpoint is available at "baseURI"
    When I send a POST request to the login API with the following JSON payload:
      """
      {
        "email": "<login_email>",
        "password": "<login_password>"
      }
      """
    Then the status code of the response should be <StatusCode>
    And the status message of the response must be <Message>

    Examples:
      | login_email          | login_password  | StatusCode | Message                           |
      | pooja.n@gmail.com    | Valid1$Password | 200        | "id_token"                        |
      | pooja.n@gmail.com    | wrongPassword   | 401        | "Incorrect username or password." |
      | invalid-email-format | Valid1$Password | 404        | "User not found."                 |
      |                      | Valid1$Password | 400        | "Email is required."              |
      | pooja.n@gmail.com    |                 | 400        | "Password is required"            |
      | pooja.n@gmail.com      | NoSpecialChar1  | 401        | "Incorrect username or password." |
      | Admin1@gmail.com     | Valid1$Password | 404        | "User not found."                 |
