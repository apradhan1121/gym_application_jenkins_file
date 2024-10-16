Feature: Booking a workout session
  Scenario Outline: Validate date, month, and year selection for present or future dates
    Given the user navigates to the book workouts page
    When the user selects year, month, date, and time slot "<timeSlot>"
    And the user clicks on the book button
    Then the user should see the toast message as "<toastMessage>"
    Examples:
      | timeSlot         | toastMessage                 |
      | 03:30 - 04:30 PM | Workout booked successfully! |

  Scenario Outline: Validate date, month, and year selection for already booked dates
    Given the user navigates to the book workouts page
    When the user selects year, month, date, and time slot "<timeSlot>"
    And the user clicks on the book button
    Then the time slot button should be disabled for "<timeSlot>"
    Examples:
      | timeSlot         |
      | 03:30 - 04:30 PM |

  Scenario Outline: Validate date, month, and year selection for past dates
    Given the user navigates to the book workouts page
    When the user selects past year year, month, date, and time slot "<timeSlot>"
    And the user clicks on the book button
    Examples:
      | timeSlot         |
      | 03:30 - 04:30 PM |
