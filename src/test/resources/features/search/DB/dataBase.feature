@DB
Feature: Tests with DataBase

  Scenario: Successfully retrieve all Users info
    When I get all existing Users information
    Then all the users are displayed

  @DeleteUser
  Scenario: Successfully create a new user
    When I create a new user with the following information:
      | id | is_admin | firstName | sirName | title | country  | city    | email         | password |
      | 0  | 0        | Datcho     | Basov    | Mr.   | Bulgaria | Plovdiv | aabb2@email.com | pass123  |
    Then I can receive the user using his email address

  @DeleteUser
    Scenario: Successfully update a user
      Given A user is registered through the DataBase
      When I update the user's first and last name with: "Mimi", "Mimcheva"
      Then I should receive the user's updated information

    Scenario: Delete user through the DataBase
      Given A user is registered through the DataBase
      When I Delete the user
      Then I should not be able to select the user

