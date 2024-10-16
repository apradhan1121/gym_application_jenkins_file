Feature: Booking Coach API Testing

  Background:
    Given The bookcoach api is loaded with the "baseURI"
      And the response consists of a bearer token

  Scenario Outline: The user wants to book a coach
    When I send a POST request to the book the coach with "<coachEmail>", "<time>", and today's date
    Then the response status code should be as <statuscode>
    And the response should "<message>"

    Examples:
      | coachEmail       | time             | statuscode | message                                                              |
      | coach2@gmail.com | 10:45 - 11:45 AM | 200        | Workout booked successfully                                          |
      | coach1@gmail.com | 02:45 - 03:45 PM | 200        | Workout booked successfully                                          |
      | coach1@gmail.com | 02:45 - 03:45 PM | 409        | The selected timeslot is already booked. Please choose another slot. |
      | coach2@gmail.com | 03:45 - 04:45 AM | 200        | Workout booked successfully                                          |
      | invalidemail.com | 06:45 - 07:45 AM | 409        | Coach not found or details not available.                            |
      | ram@gmail.com    | 07:45 - 08:45 AM | 409        | Coach not found or details not available.                            |
      |                  | 08:45 - 10:45 PM | 400        | Missing mandatory field: coachEmail                                  |
      | coach2@gmail.com |                  | 400        | Missing mandatory field: time                                        |
      | coach1@gmail.com | 14:45 - 15:45 AM | 400        | Invalid time format                                                  |