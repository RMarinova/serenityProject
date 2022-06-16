@web
Feature: Log in with admin

  Scenario: Log in with admin account
    Given  The LogInPage is opened
    When  The username "admin@automation.com" is filled in
    And the password "pass123" is filled in
    And the login button is clicked
    Then User is logged successfully

  Scenario Outline: Log in mandatory fields
    Given  The LogInPage is opened
    When The following details are filled in: '<userName>', '<password>'
    And the login button is clicked
    Then The error message is received

    Examples:
      | userName             | password |
      |                      | pass123  |
      | admin@automation.com |          |