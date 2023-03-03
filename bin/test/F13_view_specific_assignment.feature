Feature: View a specific assignment by id

  As a student
  I want to see a specific assignment assigned to a project
  so that I can view its details and description

  Background:
    Given the server is running
    And the following projects exist in the system:
      | id  | title | complete | active | description    |
      | 100 | "A1"  | false    | true   | "Assignment 1" |
      | 101 | "A2"  | false    | true   | "Assignment 2" |
      | 102 | "A3"  | false    | true   | "Assignment 3" |


  Scenario Outline: View a specific assignment (Normal Flow)

    When a student views a project with id "<id>"
    Then the system displays a project with id "<id>", title "<title>", complete "<complete>", active "<active>", and description "<description>"

    Examples:
      | id  | title | complete | active | description    |
      | 100 | "A1"  | false    | true   | "Assignment 1" |


  Scenario Outline: Add an assignment with valid input then view the assignment (Alternate Flow)

    When a student adds a project with id "<id>", title "<title>", complete "<complete>", active "<active>", and description "<description>"


    And a student views a project with id "<id>"
    Then the system displays a project with id "<id>", title "<title>", complete "<complete>", active "<active>", and description "<description>"
    Examples:
      | id  | title | complete | active | description    |
      | 103 | "A4"  | false    | true   | "Assignment 4" |

  Scenario Outline: View an assignment with invalid id (Error Flow)

    When a student views a project with invalid id "<id>"
    Then the system displays an error message

    Examples:
      | id | title | complete | active | description    |
      | 0  | "A4"  | false    | true   | "Assignment 4" |

