Feature: Update an assignment description

  As a student
  I want to be able to update my assignments description
  so that I can make changes in case the professor edits it.

  Background:
    Given the server is running
    And the following project exists in the system:
      | id  | title | complete | active | description  |
      | 100 | A1    | false    | true   | Assignment 1 |
      | 101 | A2    | false    | true   | Assignment 2 |
      | 102 | A3    | false    | true   | Assignment 3 |


  Scenario Outline: Update an assignment description (Normal Flow)

    When a student edits a project description with id "<id>" and description "<description>"
    Then the project with id "<id>" has description "<description>"

    Examples:
      | id  | title | complete | active | description      |
      | 100 | A1    | false    | true   | New Assignment 1 |


  Scenario Outline: Add an assignment with valid input then edit the assignment description (Alternate Flow)

    When a student adds a project with a title "<title>", a complete "<complete>", an active "<active>", and a description "<description>"
    And a student edits a project description with id and description "<description>"
    Then the project with id has description "<description>"

    Examples:
      | title | complete | active | description      |
      | A4    | false    | true   | New Assignment 4 |


  Scenario Outline: Update an assignment description with invalid id (Error Flow)

    When a student edits a project description with invalid id 0 and description "<description>"
    Then the student will get notified by an error message

    Examples:
      | id | title | complete | active | description  |
      | 0  | A3    | false    | true   | Assignment 3 |

