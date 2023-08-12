

Feature: Error validation

@ErrorValidation
  Scenario: Error Validation
    Given I landed on eCommerce Page
    When logged in with username <username> and password <password>
    Then "Incorrect email or password." error message is displayed

    Examples: 
      | username  				 | password  |
      | boratest@gmail.com | P@ssword2 |
      | boratest@gmail.com | P@ssword3 |
      | boratest1@gmail.com | P@ssword1 |

 