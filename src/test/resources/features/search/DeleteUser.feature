@web
Feature: Deletion of the user

  Scenario: Delete user
    Given  The LogInPage is opened
    And The register button is clicked
    And A user is registered with the following details
      | title | firstName | sirName | email             | password | country  | city    |
      | Mr.   | Marin     | Marinov | marin02.m@email.com | pass123  | Bulgaria | Plovdiv |
    And I logout from the the account
    When I login with the following admin details: "admin@automation.com" and "pass123"
    And I click on the Users on the admin page
    And I click on the delete button next to the user
    Then The user should be deleted