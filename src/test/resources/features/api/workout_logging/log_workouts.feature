Feature: Log Workouts API Testing

  Background:
    Given The API endPoint is obtained from "baseURI" and is set
    And The bearer token is obtained from the LoginUser class

  Scenario Outline: Log workout with different details
    When the user sends a POST request with the above JSON payload
    """
    {
      "coach_id": "<coach_id>",
      "workout_type": "<workout_type>",
      "date": "<date>",
      "duration": <duration>,
      "notes": "<notes>",
      "submit_feedback": "<feedback>"
    }
    """
    Then the status code of the response is expected to be <StatusCode>
    And the response message should be <Message>

    Examples:
      #Valid data
      | coach_id | workout_type | date       | duration | notes                                   | feedback            | StatusCode | Message                       |
      | 1        | Cardio       | 2024-09-05 | 45       | Great session with increased intensity. | Excellent progress  | 200        | "Workout logged successfully" |
      | 2        | Strength     | 2024-09-06 | 60       | Focused on strength building exercises. | Good improvement    | 200        | "Workout logged successfully" |
      | a        | Strength     | 2024-09-06 | 60       | Focused on strength building exercises. | Good improvement    | 200        | "Workout logged successfully" |
      # Invalid coach_id
      |          | Yoga         | 2024-09-07 | 30       | Relaxing session.                       | Felt more flexible. | 400        | "Invalid workout details"     |
      | as       | Pilates      | 2024-09-08 | 40       | Focused on core strength.               | Better posture.     | 400        | "Invalid workout details"     |
      #Invalid workout_type
      | 3        |              | 2024-09-08 | 40       | Focused on core strength.               | BExcellent progress | 400        | "Invalid workout details"     |
      | 3        | 23           | 2024-09-08 | 40       | Focused on strength building exercises. | Better posture.     | 400        | "Invalid workout details"     |
      #Invalid date
      | 4        | Pilates      |            | 40       | Focused on core strength.               | Good improvement    | 400        | "Invalid workout details"     |
      #Invalid duration
      | 5        | Pilates      | 2024-09-08 |          | Focused on core strength.               | Better posture.     | 500        | "Internal server error"       |
      | 5        | Pilates      | 2024-09-08 | abc      | Focused on strength building exercises. | Better posture.     | 500        | "Internal server error"       |
      | 5        | Pilates      | 2024-09-08 | -30      | Focused on core strength.               | Good improvement    | 400        | "Invalid workout details"     |
      | 5        | Pilates      | 2024-09-08 | 0        | Relaxing session.                       | Better posture.     | 400        | "Invalid workout details"     |
      #Invalid notes
      | 6        | Pilates      | 2024-09-08 | 40       |                                         | Excellent progress  | 400        | "Invalid workout details"     |
      #Invalid feedback
      | 6        | Pilates      | 2024-09-08 | 40       | Focused on strength building exercises. |                     | 400        | "Invalid workout details"     |