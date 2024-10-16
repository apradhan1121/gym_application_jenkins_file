@Regression
Feature: Update User Details API Testing

  Background:
    Given the API endpoint is present at "baseURI"
    And the response contains a valid bearer token

  Scenario Outline: Update only the name
    When I use the bearer token to send a PUT request to update user details with the following JSON payload:
      """
      {
        "name": "<name>"
      }
      """
    Then the status code of the response must be <StatusCode>
    And the status message of the response should be <Message>

    Examples:
      | name        | StatusCode | Message                                                                                             |
      | Jane Doe    | 200        | "User details updated successfully."                                                                |
      |             | 400        | "Name is required and must contain at least one alphabetic character and cannot be purely numeric." |

  Scenario Outline: Update only the target
    When I use the bearer token to send a PUT request to update user details with the following JSON payload:
      """
      {
        "target": "<YourTarget>"
      }
      """
    Then the status code of the response must be <StatusCode>
    And the status message of the response should be <Message>

    Examples:
      | YourTarget  | StatusCode | Message                                                                                               |
      | Muscle Gain | 200        | "User details updated successfully."                                                                  |
      |             | 400        | "Target is required and must contain at least one alphabetic character and cannot be purely numeric." |

  Scenario Outline: Update only the preferable activity
    When I use the bearer token to send a PUT request to update user details with the following JSON payload:
      """
      {
        "preferableActivity": "<preferable_activity>"
      }
      """
    Then the status code of the response must be <StatusCode>
    And the status message of the response should be <Message>

    Examples:
      | preferable_activity | StatusCode | Message                                                                                                            |
      | Cardio              | 200        | "User details updated successfully."                                                                               |
      | Yoga                | 200        | "User details updated successfully."                                                                               |
      |                     | 400        | "Preferable activity is required and must contain at least one alphabetic character and cannot be purely numeric." |


  Scenario Outline: Missing required fields
    When I use the bearer token to send a PUT request to update user details with an empty JSON payload:
      """
      {}
      """
    Then the status code of the response must be <StatusCode>
    And the status message of the response should be <Message>

    Examples:
      | StatusCode | Message                   |
      | 400        | "No attributes provided." |


  Scenario Outline: Validate update user details API with various combinations of input parameters
    When I use the bearer token to send a PUT request to update user details with the following JSON payload:
      """
      {
        "name": "<name>",
        "target": "<YourTarget>",
        "preferableActivity": "<preferable_activity>"
      }
      """
    Then the status code of the response must be <StatusCode>
    And the status message of the response should be <Message>

    Examples:
      | name     | preferable_activity | YourTarget  | StatusCode | Message                                                                                                            |
      | John     | Yoga                | Weight Gain | 200        | "User details updated successfully."                                                                               |
      | Jane Doe | Cycling             | Endurance   | 200        | "User details updated successfully."                                                                               |
      | Pooja N  | Yoga                | Weight gain | 200        | "User details updated successfully."                                                                               |
      | Pooja N  | Fitness             |             | 400        | "Target is required and must contain at least one alphabetic character and cannot be purely numeric."              |
      | Pooja N  |                     | Weight Loss | 400        | "Preferable activity is required and must contain at least one alphabetic character and cannot be purely numeric." |
      | J Doe    |                     | Weight Loss | 400        | "Preferable activity is required and must contain at least one alphabetic character and cannot be purely numeric." |
      |          | Yoga                |             | 400        | "Name is required and must contain at least one alphabetic character and cannot be purely numeric."                |
      |          |                     | Weight Loss | 400        | "Name is required and must contain at least one alphabetic character and cannot be purely numeric."                |
      | 1234     |                     | Weight Loss | 400        | "Name is required and must contain at least one alphabetic character and cannot be purely numeric."                |
      | Pooja    | 1234                | Weight Loss | 400        | "Preferable activity is required and must contain at least one alphabetic character and cannot be purely numeric." |

