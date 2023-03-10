Feature: View a specific due date by id

    As a student
    I want to see a specific due date assigned to a todo item
    so that I can view its details and description

    Background:
        Given the server is running
        And the following todo item exists in the system:
            | title      | complete | active | description |
            | 10/01/2023 | false    | true   | Due date 1  |
            | 11/01/2023 | false    | true   | Due date 2  |
            | 12/01/2023 | false    | true   | Due date 3  |


    Scenario Outline: View a specific due date (Normal Flow)

        When a student views a todo item with id
        Then the system displays a todo item with title "<title>", complete "<complete>", active "<active>", and description "<description>"

        Examples:
            | title      | complete | active | description |
            | 12/01/2023 | false    | true   | Due date 3  |


    Scenario Outline: Add an due date with valid input then view the due date (Alternate Flow)

        When a student adds a todo item with a title "<title>", a complete "<complete>", an active "<active>", and a description "<description>"
        And a student views a todo item with id
        Then the system displays a todo item with title "<title>", complete "<complete>", active "<active>", and description "<description>"
        Examples:
            | title      | complete | active | description |
            | 13/01/2023 | false    | true   | Due date 4  |

    Scenario Outline: View a due date with invalid id (Error Flow)

        When a student views a todo item with invalid id 0
        Then the student will get notified by an error message

        Examples:
            | id | title      | complete | active | description |
            | 0  | 13/01/2023 | false    | true   | Due date 4  |