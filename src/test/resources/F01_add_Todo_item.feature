Feature: Create a todo item

    As a student
    I want to assign each of my due dates to a todo item
    so that I can keep track of all my due dates

    Background:
        Given the server is running

    Scenario Outline: Add a todo item with valid input to represent a due date (Normal Flow)

        When a student adds a todo item with a title "<title>", a doneStatus "<doneStatus>", and a description "<description>"
        Then a todo item with a title "<title>", a doneStatus "<doneStatus>", and a description "<description>" is added

        Examples:
            | title      | doneStatus | description |
            | 10/01/2023 | false      | Due date 1  |
            | 11/01/2023 | false      | Due date 2  |
            | 12/01/2023 | false      | Due date 3  |

    Scenario Outline: Remove a todo item then add a todo item with valid input to represent a due date (Alternate Flow)

        Given the following todo item exists in the system:
            | title      | doneStatus | description |
            | 10/01/2023 | false      | Due date 1  |

        When a student removes a todo item with id
        And a student adds a todo item with a title "<title>", a doneStatus "<doneStatus>", and a description "<description>"
        Then a todo item with a title "<title>", a doneStatus "<doneStatus>", and a description "<description>" is added

        Examples:
            | title      | doneStatus | description |
            | 11/01/2023 | false      | Due date 2  |

    Scenario Outline: Add a todo item with invalid completed input to represent a due date (Error Flow)

        When a student adds a todo item with a title "<title>", an invalid doneStatus "<doneStatus>", and a description "<description>"
        Then the todo item will not be added
        And the student will get notified by an error message

        Examples:
            | title      | doneStatus | description       |
            | 13/01/2023 | bad        | true Assignment 1 |
