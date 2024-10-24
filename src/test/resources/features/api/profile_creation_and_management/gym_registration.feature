Feature: User Registration API Testing

  Scenario Outline: Test registration API with various input parameters
    Given the API endpoint is loaded from "baseURI"
    When I send a POST request with the following JSON:
      """
      {
        "email": "<Email>",
        "password": "<Password>",
        "name": "<Name>",
        "target": "<YourTarget>",
        "preferableActivity": "<PreferableActivity>"
      }
      """
    Then the response status code should be <StatusCode>
    And the response should contain <Message>
    Examples:
      | Name     | Email                           | Password   | YourTarget  | PreferableActivity | StatusCode | Message                                                                                                            |

    # Valid Input
      | John Doe | valid2233112222222114@gmail.com | Passw0rd!  | Weight Loss | Yoga               | 200        | "User created successfully"                                                                                        |

    # Invalid Email Formats
      | John Doe | invfalidemail2                  | Passw0rd!  | Weight Loss | Yoga               | 400        | "Invalid email format"                                                                                             |
      | John Doe | @gmail.com                      | Passw0rd!  | Weight Loss | Yoga               | 400        | "Invalid email format"                                                                                             |
      | John Doe | vali1d221@gmail                 | Passw0rd!  | Weight Loss | Yoga               | 400        | "Invalid email format"                                                                                             |
      | John Doe | valid113@gmail.c                | Passw0rd!  | Weight Loss | Yoga               | 400        | "Invalid email format"                                                                                             |

    # Password Validation
      | John Doe | arya32221222@gmail.com          | Passw0rd!  | Weight Loss | Yoga               | 200        | "User created successfully"                                                                                        |
      | John Doe | raj3214@gmail.com               | passw0rd!  | Weight Loss | Yoga               | 400        | "Error signing up user: Password did not conform with password policy: Password must have uppercase characters"    |
      | John Doe | rajni1234@gmail.com             | PASSWORD1! | Weight Loss | Yoga               | 400        | "Error signing up user: Password did not conform with password policy: Password must have lowercase characters"    |
      | John Doe | sai11221@gmail.com              | Passw0rd   | Weight Loss | Yoga               | 400        | "Error signing up user: Password did not conform with password policy: Password must have symbol characters"       |
      | John Doe | biswas111212@gmail.com          | P@ssw!     | Weight Loss | Yoga               | 400        | "Password must be at least 8 characters long"                                                                      |

    # Missing Required Fields
      |          | v1al2i112d@gmail.com            | Passw0rd!  | Weight Loss | Yoga               | 400        | "Name is required"                                                                                                 |
      | John Doe | a12@gmail.com                   | Passw0rd!  |             | Yoga               | 400        | "Target is required and must contain at least one alphabetic character and cannot be purely numeric."              |
      | John Doe | valiwedd13@gmail.com            | Passw0rd!  | Weight Loss |                    | 400        | "Preferable activity is required and must contain at least one alphabetic character and cannot be purely numeric." |

  @Regression
  Scenario Outline: Test registration API with various input parameters
    Given the API endpoint is loaded from "baseURI"
    When I send a POST request with the following JSON:
      """
      {
        "email": "<Email>",
        "password": "<Password>",
        "name": "<Name>",
        "target": "<YourTarget>",
        "preferableActivity": "<PreferableActivity>"
      }
      """
    Then the response status code should be <StatusCode>
    And the response should contain <Message>
    Examples:
      | Name     | Email                    | Password  | YourTarget  | PreferableActivity | StatusCode | Message                                                                                                            |

    # Valid Input
      | John Doe | validemail2891@gmail.com | Passw0rd! | Weight Loss | Yoga               | 200        | "User created successfully"                                                                                        |
      | John Doe | invfalidemail2           | Passw0rd! | Weight Loss | Yoga               | 400        | "Invalid email format"                                                                                             |
      | John Doe | @gmail.com               | Passw0rd! | Weight Loss | Yoga               | 400        | "Invalid email format"                                                                                             |
      | John Doe | raj3214@gmail.com        | passw0rd! | Weight Loss | Yoga               | 400        | "Error signing up user: Password did not conform with password policy: Password must have uppercase characters"    |

    # Missing Required Fields
      |          | v1al2i112d@gmail.com     | Passw0rd! | Weight Loss | Yoga               | 400        | "Name is required"                                                                                                 |
      | John Doe | a12@gmail.com            | Passw0rd! |             | Yoga               | 400        | "Target is required and must contain at least one alphabetic character and cannot be purely numeric."              |
      | John Doe | valiwedd13@gmail.com     | Passw0rd! | Weight Loss |                    | 400        | "Preferable activity is required and must contain at least one alphabetic character and cannot be purely numeric." |

