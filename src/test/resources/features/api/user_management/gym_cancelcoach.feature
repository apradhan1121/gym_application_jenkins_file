Feature: Book and Cancel Coach Session with User Login

  Background:
    Given The bookcoach api is received from the "baseURI"
    And the response consists of the bearer token

  Scenario Outline: The user books a coach and then cancels the session
    When I send a POST requests to book the coach with "<coachEmail>", "<time>", and today's date
    Then I get the booking response status code as <statusCode>
    And I capture the sessionId from the response
    And the booking response should contain the message "<bookingMessage>"

    When I submit the cancel request using the captured sessionId
    Then I get response status code as <cancelStatusCode>
    And I verify the response contains the specified cancel message "<cancelMessage>"

    Examples:
      | coachEmail       | time             | statusCode | bookingMessage              | cancelStatusCode | cancelMessage                 |
      | coach2@gmail.com | 01:15 - 02:15 AM | 200        | Workout booked successfully | 200              | Workout canceled successfully |
      | coach1@gmail.com | 02:15 - 03:15 AM | 200        | Workout booked successfully | 200              | Workout canceled successfully |
      | coach2@gmail.com | 03:15 - 04:15 AM | 200        | Workout booked successfully | 200              | Workout canceled successfully |
      | coach1@gmail.com | 04:15 - 05:15 AM | 200        | Workout booked successfully | 200              | Workout canceled successfully |
      | coach1@gmail.com | 05:15 - 06:15 AM | 200        | Workout booked successfully | 200              | Workout canceled successfully |
      | coach1@gmail.com | 06:15 - 07:15 AM | 200        | Workout booked successfully | 200              | Workout canceled successfully |
      | coach1@gmail.com | 07:15 - 08:15 AM | 200        | Workout booked successfully | 200              | Workout canceled successfully |
      | coach1@gmail.com | 08:15 - 09:15 AM | 200        | Workout booked successfully | 200              | Workout canceled successfully |
