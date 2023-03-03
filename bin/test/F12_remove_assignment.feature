Feature: Remove an assignment

  As a student
  I want to remove an assignment assigned to a project
  so that assignments I no longer have to do are not tracked by the application.

  Background:
    Given the server is running
    And the following project exists in the system:
      | title | complete | active | description  |
      | A1    | false    | true   | Assignment 1 |


  Scenario Outline: Remove a project representing an assignment with valid input (Normal Flow)

    When a student removes a project with id "<id>"
    Then a project with id "<id>" is removed

    Examples:
      | title | complete | active | description |



  Scenario Outline: Add a project with valid input then remove the project (Alternate Flow)

    When a student adds a project with a title "<title>", a complete "<complete>", an active "<active>", and a description "<description>"
    And they remove a project with id "<id>"
    Then a project with id is removed

    Examples:
      | title | complete | active | description |


  Scenario Outline: Remove a project with invalid id (Error Flow)

    When a student removes a project with invalid id "<id>"
    Then the student will get notified by an error message

    Examples:
      | id | title | complete | active | description  |
      | 0  | A4    | false    | true   | Assignment 4 |

