@web
Feature: User registration

  Scenario: User registration
    Given  The LogInPage is opened
    And The register button is clicked
    When The following details are filled in with
      | title | firstName | sirName | email  | password | country  | city    |
      | Mrs.  | Testi     | Testev  | random | pass123  | Bulgaria | Plovdiv |
    And I agree with the terms of service is clicked
    And The register button is clicked on the register page
    Then The user is registered successfully

  Scenario Outline: Multiple user registrations
    Given  The LogInPage is opened
    And The register button is clicked
    When The following details are filled in with: '<title>', '<firstName>', '<sirName>', '<email>', '<password>', '<country>', '<city>'
    And I agree with the terms of service is clicked
    And The register button is clicked on the register page
    Then The user is registered successfully.
    Examples:
      | title | firstName | sirName | email  | password | country  | city    |
      | Mrs.  | Tesy      | Testev  | random | pass123  | Bulgaria | Plovdiv |
      | Mrs.  | Tes       | Testev  | random | pass123  | Bulgaria | Plovdiv |

  Scenario Outline: Registration mandatory fields
    Given  The LogInPage is opened
    And The register button is clicked
    When The following details are filled in with '<title>', '<firstName>', '<sirName>', '<email>', '<password>', '<country>', '<city>'
    And I agree with the terms of service is clicked
    And The register button is clicked on the register page
    Then The registration is not successful
    Examples:
      | title | firstName | sirName | email               | password | country  | city  |
      | Mr.   |           | Testev  | test.test@email.com | pass1234 | Bulgaria | Varna |
      | Mr.   | Teso      |         | test.test@email.com | pass1234 | Bulgaria | Varna |
      | Mr.   | Teso      | Testev  |                     | pass1234 | Bulgaria | Varna |
      | Mr.   | Teso      | Testev  | test.test@email.com |          | Bulgaria | Varna |
      | Mr.   | Teso      | Testev  | test.test@email.com | pass1234 |          | Varna |
      | Mr.   | Teso      | Testev  | test.test@email.com | pass1234 | Bulgaria |       |

 Scenario: Mandatory terms of service
   Given  The LogInPage is opened
   And The register button is clicked
   When The following details are filled in with the following details but the terms of service are not clicked
     | title | firstName | sirName | email  | password | country  | city    |
     | Mrs.  | Testi     | Testev  | random | pass123  | Bulgaria | Plovdiv |
   And The register button is clicked on the register page
   Then An error message is displayed
   And The registration is not successful

   Scenario: User registration with lombok
     Given  The LogInPage is opened
     And The register button is clicked
     When The following details are filled in using lombok
       | title | firstName | sirName | email  | password | country  | city    |
       | Mrs.  | Testi     | Testev  | random | pass123  | Bulgaria | Plovdiv |
     And I agree with the terms of service is clicked
     And The register button is clicked on the register page
     Then The user is registered successfully with lombok