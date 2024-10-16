Feature: UI Testing for Resume Workout and Cancel Workout

  Background: Navigating to the Gym Management Application
    Given I am on the Login Page of the Gym Management Application
    And I enter email and password

  Scenario: Validating the Cancel Workout Page
    Given In the workout page, I click on the cancelResume workout button
    Then I should see Resume workout button
    And I click on Resume workout button

  Scenario: Validating the Cancel and Resume Workout Page
    Given In the workout page, I click on the cancel workout button
    When I click on Cancel element workout button
    Then I should see the confirmation message

  Scenario: Validating the postFeedback Workout page
    Given In the workout page, I click on the Finish workout button
    When I provide the rating
    Then I enter the feedback
    And I click on the submit button
    
  Scenario: Submitting the postfeedback without rating and proving feedback
    Given In the workout page, I click on Finish workout button
    When I provide the feedback without rating
    Then I click on submit button

  Scenario: Submitting the postfeedback without feedback and proving rating
    Given In the workout page, I click on Finish workout button
    When I provide the rating without feedback
    Then I click on submit button
