Feature: CRUD operations with REST

  Scenario: GET all users with REST
    When I perform a "GET" request to "/users"
    Then I should receive all users
    And Status code "200" with a message "OK"


  Scenario: GET specific user with REST
    When I perform a "GET" request to "/users/3"
    Then I should receive a user with the specific id
    And Status code "200" with a message "OK"


  @rest
  Scenario: POST new user with REST and lombok
    When I use the following details with lombok
      | title | first_name | sir_name | email               | password | country  | city    | is_admin |
      | Mr.   | Ivan       | Ivanov   | banko1899@email.com | pass123  | Bulgaria | Plovdiv | 0        |
    And I perform a "POST" request to "/users" with lombok
    Then Status code "200" with a message "OK"


  Scenario: Delete user with REST
    Given A user is registered
    When I perform a "DELETE" request to "/users/"
    Then I should receive status code "200" with a message "OK"


  @rest
  Scenario: PUT user details with REST
    Given A user is registered
    When I use the following details to update the user:
      | title | first_name | sir_name | country | city  | email           |
      | Mr.   | Pesho      | Peshev   | BG      | Sofia | pesho@email.com |
    And I perform a "PUT" request to "/users/"
    Then I should receive status code "200" with a message "OK"

