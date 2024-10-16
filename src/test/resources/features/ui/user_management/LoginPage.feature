Feature: Login to gym application

  Background:
    Given I am on the Login page

  Scenario Outline: Test login functionality with different credentials
    When I enter "<email>" and "<password>"
    And I click on the login button
    Then I should see the "<message>" in the "<locator>"

    Examples:
      | email              | password        | message                                              | locator     |
      | pooja.n@gmail.com  | Valid1$Password | Login successful                                     | toast       |
      | xyz@gmail.com      | Abhijit@2024    | Login failed: User not found.                        | toast       |
      | rajesh@gmail.com   | Abhi1@2         | Password cannot be less than 8 characters            | PassInline  |
      | radha@gmail.com    | abhijit@1       | Password must contain at least one uppercase letter  | PassInline  |
      | raghava@gmail..com | Abhijit1        | Password must contain at least one special character | PassInline  |
      |                    | Abhijit1        | Invalid email address                                | EmailInline |


  Scenario: Verify the Create New Account link is displayed
    Then I should see the Create New Account link