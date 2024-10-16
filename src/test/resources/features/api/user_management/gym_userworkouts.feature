Feature: Fetch User Workouts

  Background:
    Given the base API URL is fetched from "baseURI"
    And the bearer token is collected from loginUser

  Scenario Outline: Validate response when workouts are found for the user
    Given the api for workouts endpoint is set
    When I send a POST request with "<coachEmail>", "<time>" and today's date
    Then the response status code is <statusCode>
    And the booking response contains this message "<message>"
    When I send a GET request to the user workouts endPoint
    Then the response code should be 200
    And the response body should contain the following:
      | feedback               |
      | dateValue              |
      | coachName              |
      | comments               |
      | statusValue            |
      | coachEmail             |
      | description            |
      | userEmail              |
      | CoachProfilePictureUrl |
      | id                     |
      | timeValue              |
      | expertise              |
    Examples:
      | coachEmail       | time             | statusCode | message                                              |
      | coach2@gmail.com | 11:50 - 12:50 AM | 200        | Workout booked successfully                          |
      | coach1@gmail.com | 03:55 - 4:55 AM  | 400        | Invalid time format. Please use HH:MM - HH:MM AM/PM. |
      | coach@gmail.com  | 03:55 - 04:55 AM | 409        | Coach not found or details not available.            |
      | coach1@gmail.com |                  | 400        | Missing mandatory field: time                        |
      |                  | 03:55 - 04:55 AM | 400        | Missing mandatory field: coachEmail                  |




