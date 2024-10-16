Feature: Validate Gym Management Registration Page


  Scenario Outline: Validate registration

    When I enter "<name>", "<email>" and "<password>" in their fields
    And I select "<target>" from the target dropdown
    And I select "<activity>" from the preferable activity dropdown
    And I click the register button
    Then I should see the toast message as "<toastMessage>"
    Examples:
      | email                   | password    | name  | target      | activity | toastMessage                 |
      | validemail.4@test.com   | Password12@ | Pooja | weight loss | crossfit | Created Account Successfully |
      | valid.email167@test.com | Password12@ | Pooja | weight loss | crossfit | Email is already registered. |


  Scenario Outline: Validate registration with improper name
    When I enter "<name>", "<email>" and "<password>" in their fields
    And I select "<target>" from the target dropdown
    And I select "<activity>" from the preferable activity dropdown
    And I click the register button
    Then I should see the error message at the name field as "<errorMessage>"
    Examples:
      | email                  | password    | name      | target      | activity | errorMessage                             |
      | valid.email13@test.com | Password12@ |           | muscle gain | yoga     | Name is required                         |
      | valid.email14@test.com | Password12@ | J         | weight loss | crossfit | Name should be more than 2 characters    |
      | valid.email15@test.com | Password12@ | 12345     | muscle gain | cardio   | Name can only contain letters and spaces |
      | valid.email16@test.com | Password12@ | @John Doe | weight loss | cardio   | Name can only contain letters and spaces |
      | valid.email17@test.com | Password12@ | John#     | muscle gain | yoga     | Name can only contain letters and spaces |


  Scenario Outline: Validate registration with invalid email inputs

    When I enter "<name>", "<email>" and "<password>" in their fields
    And I select "<target>" from the target dropdown
    And I select "<activity>" from the preferable activity dropdown
    And I click the register button
    Then I should see the error message at the email field as "<errorMessage>"
    Examples:
      | email              | password    | name        | target      | activity | errorMessage          |
      | invalid.email@test | Password12@ | Jane Smith  | muscle gain | yoga     | Invalid email address |
      | testemail.com      | Password12@ | Alex Turner | weight loss | crossfit | Invalid email address |
      | @noemail.com       | Password12@ | Pooja N     | muscle gain | cardio   | Invalid email address |
      | noCom@noemail      | Password12@ | Pooja N     | muscle gain | cardio   | Invalid email address |
      | noemail@.com       | Password12@ | Pooja N     | muscle gain | cardio   | Invalid email address |
      | noDot@noemailcom   | Password12@ | Pooja N     | muscle gain | cardio   | Invalid email address |
      | pooja @email.com   | Password12@ | Chris Evans | muscle gain | cardio   | Invalid email address |
      |                    | Password12@ | Chris Evans | muscle gain | cardio   | Email is required     |


  Scenario Outline: Validate registration with invalid password

    When I enter "<name>", "<email>" and "<password>" in their fields
    And I select "<target>" from the target dropdown
    And I select "<activity>" from the preferable activity dropdown
    And I click the register button
    Then I should see the error message at the password field as "<errorMessage>"
    Examples:
      | email                  | password    | name         | target      | activity | errorMessage                                         |
      | valid.email13@test.com | password12@ | Jane Smith   | muscle gain | yoga     | Password must contain at least one uppercase letter  |
      | valid.email14@test.com | Password@   | Alex Turner  | weight loss | crossfit | Password must contain at least one number            |
      | valid.email15@test.com | Password12  | Chris Evans  | muscle gain | cardio   | Password must contain at least one special character |
      | valid.email16@test.com | Pas1@       | Lily Collins | weight loss | cardio   | Password must be at least 8 characters long          |
      | valid.email13@test.com | PASSWORD12@ | Jane Smith   | muscle gain | yoga     | Password must contain at least one lowercase letter  |
      | valid.email16@test.com |             | Lily Collins | weight loss | cardio   | Password is required                                 |


  Scenario Outline: Validate registration with invalid activity dropdown selection

    When I enter "<name>", "<email>" and "<password>" in their fields
    And I select "<target>" from the target dropdown
    And I click the register button
    Then I should see the error message at the activity dropdown as "<activityErrorMessage>"
    Examples:
      | email                  | password    | name        | target      | activityErrorMessage |
      | valid.email14@test.com | Password12@ | Alex Turner | weight loss | Activity is required |


  Scenario Outline: Validate registration with invalid target dropdown selection

    When I enter "<name>", "<email>" and "<password>" in their fields
    And I select "<activity>" from the preferable activity dropdown
    And I click the register button
    Then I should see the error message at the target dropdown as "<targetErrorMessage>"
    Examples:
      | email                  | password    | name        | activity | targetErrorMessage |
      | valid.email14@test.com | Password12@ | Alex Turner | Cardio   | Target is required |


  Scenario Outline: Validate registration with invalid target dropdown selection

    When I enter "<name>", "<email>" and "<password>" in their fields
    And I click the register button
    Then I should see the error message at the target dropdown as "<targetErrorMessage>"
    And I should see the error message at the activity dropdown as "<activityErrorMessage>"
    Examples:
      | email                  | password    | name        | targetErrorMessage | activityErrorMessage |
      | valid.email14@test.com | Password12@ | Alex Turner | Target is required | Activity is required |

  Scenario: Validate the presence of login button
    When I click the login button
    Then I should directly go to the login page









