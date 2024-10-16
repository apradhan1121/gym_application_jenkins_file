@Regression
Feature: Coach API Testing

  Background:
    Given The bookcoach api is fetched from the "baseURI"
    And the response has a bearer token

  Scenario: Retrieve the list of coaches
    When the user requests the list of coaches
    Then the response status code should get 200
    And the response should contain the following coaches:
      | Name   | email            | Ratings | Expertise |
      | Halena | coach2@gmail.com | 4       | yoga      |
      | John   | coach1@gmail.com | 4       | fitness   |
    And each coach should have a ProfilePictureUrl, Summary, and ClientReviews
