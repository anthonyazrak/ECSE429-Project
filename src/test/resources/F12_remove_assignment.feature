Feature: Remove an assignment

  As a student
  I want to remove an assignment assigned to a project
  so that assignments I no longer have to do are not tracked by the application.

  Background:
    Given the server is running
    And the following projects exist in the system:
      | id  | title | complete | active | description  |
      | 100 | A1    | false    | true   | Assignment 1 |
      | 101 | A2    | false    | true   | Assignment 2 |
      | 102 | A3    | false    | true   | Assignment 3 |


  Scenario Outline: Remove a project representing an assignment with valid input (Normal Flow)

    When a student removes a project with id "<id>"
    Then a project with id "<id>" is removed

    Examples:
      | id  | title | complete | active | description  |
      | 101 | A2    | false    | true   | Assignment 2 |
      | 102 | A3    | false    | true   | Assignment 3 |


  Scenario Outline: Add a project with valid input then remove the project (Alternate Flow)

    When a student adds a project with an id "<id>", a title "<title>", a complete "<complete>", an active "<active>", and a description "<description>"

    And they remove a project with id "<id>"
    Then a project with id "<id>" is removed

    Examples:
      | id | title | complete | active | description |


  Scenario Outline: Remove a project with invalid id (Error Flow)

    When a student removes a project with invalid id "<id>"
    Then a project with id "<id>" is not removed
    And the system reports an error

    Examples:
      | id | title | complete | active | description  |
      | 0  | A4    | false    | true   | Assignment 4 |

