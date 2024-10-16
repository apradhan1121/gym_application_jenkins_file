Feature: Coach Booking, Feedback Submission, and Finished Workouts API Testing

  Background:
    Given The bookcoach api is present with the "baseURI"
    And then the bearer token is fetched from the login response

  Scenario Outline: The user books a coach, submits feedback, and checks finished workouts
    When I send a POST request to book the coach with "<coachEmail>", "<time>" and today's date
    Then I get response status code should be <statuscode>
    And I capture the workoutId from response
    And the booking response should contain the "<bookingMessage>"

    When I submit feedback for the given workout
    """
    {
          "feedback": <feedback>,
          "comments": "<comments>"
    }
    """

    Then the feedback response should have the status code as <feedbackStatuscode>
    And the feedback response should contain the "<feedbackMessage>"

    When I send a GET request to check finished workouts for the coach "<coachEmail>"
    Then I get the response status code for finished workouts should be 200
    And the finished workouts response should contain "<statusValue>" for the status

    Examples:
      | coachEmail       | time             | statuscode | bookingMessage              | feedback | comments       | feedbackStatuscode | feedbackMessage              | statusValue |
      | coach2@gmail.com | 10:35 - 11:35 AM | 200        | Workout booked successfully | 5        | Great workout! | 200                | Feedback posted successfully | finished    |
      | coach1@gmail.com | 11:35 - 12:35 AM | 200        | Workout booked successfully | 4        | Good workout   | 200                | Feedback posted successfully | finished    |
      | coach2@gmail.com | 00:35 - 01:35 AM | 200        | Workout booked successfully | 1        | Poor workout   | 200                | Feedback posted successfully | finished    |
      | coach1@gmail.com | 01:35 - 02:35 PM | 200        | Workout booked successfully | 1        | Poor workout   | 200                | Feedback posted successfully | finished    |
      | coach1@gmail.com | 09:35 - 10:35 PM | 200        | Workout booked successfully |          |                | 400                | Invalid request body         |             |
      | coach1@gmail.com | 11:35 - 12:35 PM | 200        | Workout booked successfully | 4        |                | 400                | Comments cannot be empty     | finished    |
