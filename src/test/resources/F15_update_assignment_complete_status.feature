Feature: Update an assignment complete status

  As a student
  I want to be able to mark my assignments complete
  so that I can keep track of when I finish them.

  Background:
    Given the server is running
    And the following project exists in the system:
      | id  | title | complete | active | description  |
      | 100 | A1    | false    | true   | Assignment 1 |
      | 101 | A2    | false    | true   | Assignment 2 |
      | 102 | A3    | false    | true   | Assignment 3 |


  Scenario Outline: Update an assignment complete status (Normal Flow)

    When a student edits a project status with id "<id>" and complete status "<complete>"
    Then the project with id "<id>" has complete status "<complete>"

    Examples:
      | id  | title | complete | active | description      |
      | 100 | A1    | false    | true   | New Assignment 1 |


  Scenario Outline: Add an assignment with valid input then edit the assignment complete status (Alternate Flow)

    When a student adds a project with a title "<title>", a complete "<complete>", an active "<active>", and a description "<description>"
    And a student edits a project status with id and complete status "<complete>"
    Then the project with id has complete status "<complete>"
    Examples:
      | title | complete | active | description      |
      | A4    | false    | true   | New Assignment 4 |


  Scenario Outline: Update an assignment description with complete status (Error Flow)

    When a student edits a project complete status with id "<id>" and invalid complete status "<complete>"
    Then the student will get notified by an error message

    Examples:
      | id  | title | complete | active | description  |
      | 102 | A3    | false    | true   | Assignment 3 |

