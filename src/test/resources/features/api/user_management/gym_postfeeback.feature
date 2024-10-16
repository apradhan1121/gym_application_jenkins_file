Feature: Coach Booking and Feedback API Testing

  Background:
    Given The bookcoach api is present in the "baseURI"
    And the bearer token is fetched from the login response

  Scenario Outline: The user books a coach and submits feedback
    When I send a POST request to book the coach with "<coachEmail>", "<time>", and today's date
    Then I get the response status code should be <statuscode>
    And I capture the workoutId from the response
    And the booking response should contain "<bookingMessage>"

    When I submit feedback for the workout
    """
    {
          "feedback": <feedback>,
          "comments": "<comments>"
    }
    """

    Then the feedback response status code is <feedbackStatuscode>
    And the feedback response should contain "<feedbackMessage>"

    Examples:
      | coachEmail       | time             | statuscode | bookingMessage              | feedback | comments       | feedbackStatuscode | feedbackMessage              |
      | coach2@gmail.com | 10:35 - 11:35 AM | 200        | Workout booked successfully | 5        | Great workout! | 200                | Feedback posted successfully |
      | coach1@gmail.com | 11:35 - 12:35 AM | 200        | Workout booked successfully | 4        | Good workout   | 200                | Feedback posted successfully |
      | coach2@gmail.com | 00:35 - 01:35 AM | 200        | Workout booked successfully | 1        | Poor workout   | 200                | Feedback posted successfully |
      | coach1@gmail.com | 01:35 - 02:35 PM | 200        | Workout booked successfully | 1        | Poor workout   | 200                | Feedback posted successfully |
      | coach1@gmail.com | 09:35 - 10:35 PM | 200        | Workout booked successfully |          |                | 400                | Invalid request body         |
      | coach1@gmail.com | 11:35 - 12:35 PM | 200        | Workout booked successfully | 4        |                | 400                | Comments cannot be empty     |
