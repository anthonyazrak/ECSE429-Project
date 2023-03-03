Feature: View a specific assignment by id

  As a student
  I want to see a specific assignment assigned to a project
  so that I can view its details and description

  Background:
    Given the server is running
    And the following project exists in the system:
      | title | complete | active | description  |
      | A1    | false    | true   | Assignment 1 |
      | A2    | false    | true   | Assignment 2 |
      | A3    | false    | true   | Assignment 3 |


  Scenario Outline: View a specific assignment (Normal Flow)

    When a student views a project with id
    Then the system displays a project with title "<title>", complete "<complete>", active "<active>", and description "<description>"

    Examples:
      | title | complete | active | description  |
      | A3    | false    | true   | Assignment 3 |


  Scenario Outline: Add an assignment with valid input then view the assignment (Alternate Flow)

    When a student adds a project with a title "<title>", a complete "<complete>", an active "<active>", and a description "<description>"
    And a student views a project with id
    Then the system displays a project with title "<title>", complete "<complete>", active "<active>", and description "<description>"
    Examples:
      | title | complete | active | description  |
      | A4    | false    | true   | Assignment 4 |

  Scenario Outline: View an assignment with invalid id (Error Flow)

    When a student views a project with invalid id 0
    Then the student will get notified by an error message

    Examples:
      | id | title | complete | active | description  |
      | 0  | A4    | false    | true   | Assignment 4 |

