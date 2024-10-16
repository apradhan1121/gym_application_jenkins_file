
Feature: Validate updatePassword API with various input parameters

  Background: The user is authenticated using the valid credentials
    Given The API endpoint is fetched from properties file "kubeURI"
    Then The bearer token is present in the response body of updatePasswordSteps

    Scenario Outline: Validate updatePassword API with various input parameters
        When I send a PUT request to updatePassword api with the following JSON payload:
        """
        {
            "oldPassword": "<oldPassword>",
            "newPassword": "<newPassword>",
            "confirmPassword": "<confirmPassword>"
        }
        """


        Then the status code of the update response should be <StatusCode>
        And the status message of the update response must be <Message>

    Examples:

      | oldPassword  | newPassword  | confirmPassword | StatusCode | Message                                                                                                                                              |
      | Coach@2024   | Coach@2024@1 | Coach@2024@1    | 200        | "Password updated successfully"                                                                                                                      |
      | Coach@2024@1 | Coach@2024   |                 | 400        | "Confirm password can't be blank"                                                                                                                    |
      | Coach@2024@1 | 123          | 123             | 400        | "Password must be at least 8 characters long and include at least one uppercase letter, one lowercase letter, one number, and one special character" |
      | Coach@2024@1 | Coach@2024@1 | Coach@123       | 400        | "New password and confirm password do not match"                                                                                                     |
      | Coach@2024@1 |              | Coach@2024@1    | 400        | "Password must be at least 8 characters long and include at least one uppercase letter, one lowercase letter, one number, and one special character" |
      | Coach@2024@1 | COACH@2024   | COACH@2024      | 400        | "Password must be at least 8 characters long and include at least one uppercase letter, one lowercase letter, one number, and one special character" |
