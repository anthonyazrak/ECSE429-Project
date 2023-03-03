Feature: Assign an assignment to a project

  As a student
  I want to assign each of my assignments to a project
  so that I can keep track of all my assignments

  Background:
    Given the server is running

  Scenario Outline: Add a project with valid input to represent an assignment (Normal Flow)

    When a student adds a project with a title "<title>", a complete "<complete>", an active "<active>", and a description "<description>"
    Then a project with a title "<title>", a complete "<complete>", an active "<active>", and a description "<description>" is added

    Examples:
      | title | complete | active | description  |
      | A1    | false    | true   | Assignment 1 |
      | A2    | false    | true   | Assignment 2 |
      | A3    | false    | true   | Assignment 3 |

  Scenario Outline: Remove a project then add a project with valid input to represent an assignment (Alternate Flow)

    Given the following project exists in the system:
      | title | complete | active | description  |
      | A1    | false    | true   | Assignment 1 |

    When a student removes a project with id
    And a student adds a project with a title "<title>", a complete "<complete>", an active "<active>", and a description "<description>"
    Then a project with a title "<title>", a complete "<complete>", an active "<active>", and a description "<description>" is added

    Examples:
      | title | complete | active | description  |
      | A2    | false    | true   | Assignment 2 |

  Scenario Outline: Add a project with invalid completed input to represent an assignment (Error Flow)

    When a student adds a project with a title "<title>", an invalid complete "<complete>", an active "<active>", and a description "<description>"
    Then the project will not be added
    And the student will get notified by an error message

    Examples:
      | title | complete | active | description  |
      | G5    | bad      | true   | Assignment 1 |

