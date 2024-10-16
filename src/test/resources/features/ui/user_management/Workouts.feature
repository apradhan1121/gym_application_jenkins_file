Feature: Comprehensive UI Testing for Workouts Page

  Background: Navigating to the Gym Management Application
    Given I am on the Login Page of the Gym Management Application
    And I enter email and password

  Scenario: Navigating and Validating Elements on Workouts Page
    Given I am on the Workouts page
    Then I should see Workouts button and click on it
    And I should see Coaches button in the top navbar and click on it

  Scenario: Validating Toggle Buttons Functionality on Workouts Page
    Given I am on the Workouts page
    Then I should see toggle Dark button and click it
    And I should see toggle light button and click it

  Scenario: Navigating to Update Profile from Workouts Page
    Given I am on the Workouts page
    When I click on the Edit account Profile button
    Then I should navigate to the update profile page
    And I should navigate back to the Workouts page

  Scenario: Validating the Presence of Scheduled Workout on Workouts Page
    Given I am on the Workouts page
    Then I should verify the presence of scheduled workout

































#Feature: UI Testing for Workouts Page
#  Background: Navigating to the Gym Management Application
#    Given I am on the Login Page of the Gym Management Application
#    And I enter email and password
#
#  Scenario: Validating the Workouts Page
#    Given I am on the Workouts page
#    Then I should see Workouts and Coaches button in the top navbar
#    And I should see toggle Dark button and click it
#    And I should see toggle light button and click it
#    And I click on the Edit account Profile button
#    Then I should navigate to the update profile page
#    And I should navigate back to the Workouts page
#    And I should verify the presence of scheduled workout
#
