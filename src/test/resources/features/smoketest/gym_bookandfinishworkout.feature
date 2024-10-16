Feature: Overall Flow of the Gym Management Application for smoke testing

  Scenario Outline: User logs in, books a workout, and provides feedback
    Given the user is on the login page
    When the user logs in with valid credentials
    And the user is redirected to the dashboard
    When the user navigates to the coaches page and selects a "<coach>"
    And the user selects a workout for the date and time "<timeSlot>"
    And the user clicks on the book workout button
    Then the workout is booked successfully with a toast message "<booking_confirmation_message>"

    When the user navigates to the workouts page
    And the user clicks the finish workout button
    And the user provides a rating <rating> and feedback "<feedback>"
    Then the workout rating and feedback is successfully submitted with message "<feedback_confirmation_message>"
    And Logout the page

    Examples:
      | workout_type | coach | timeSlot         | booking_confirmation_message | rating | feedback                                   | feedback_confirmation_message   |
      | Yoga         | Henry | 08:00 - 09:00 AM | Workout booked successfully! | 5      | Great workout! Felt relaxed and energized. | Feedback submitted successfully |
#      | Rock Climbing     | Rohit | 09:30 - 10:30 AM | Workout booked successfully! | 4      | Challenging, but enjoyed the climb!        | Feedback submitted successfully |
#      | Personal Training | James  | 11:00 - 12:00 PM | Workout booked successfully! | 3      | Trainer was good, but session was intense  | Feedback submitted successfully |

