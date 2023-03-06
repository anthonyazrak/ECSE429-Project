Feature: Update an due date description

    As a student
    I want to be able to update my due date description
    so that I can make changes in case the professor edits it.

    Background:
        Given the server is running
        And the following todo item exists in the system:
            | id   | title      | complete | active | description |
            | 1000 | 10/01/2023 | false    | true   | Due date 1  |
            | 1001 | 11/01/2023 | false    | true   | Due date 2  |
            | 1002 | 12/01/2023 | false    | true   | Due date 3  |


    Scenario Outline: Update an due date description (Normal Flow)

        When a student edits a todo item description with id "<id>" and description "<description>"
        Then the todo item with id "<id>" has description "<description>"

        Examples:
            | id   | title      | complete | active | description |
            | 1000 | 10/01/2023 | false    | true   | Due date 1  |


    Scenario Outline: Add an due date with valid input then edit the due date description (Alternate Flow)

        When a student adds a todo item with a title "<title>", a complete "<complete>", an active "<active>", and a description "<description>"
        And a student edits a todo item description with id and description "<description>"
        Then the todo item with id has description "<description>"

        Examples:
            | title      | complete | active | description |
            | 13/01/2023 | false    | true   | Due date 4  |


    Scenario Outline: Update an due date description with invalid id (Error Flow)

        When a student edits a todo item description with invalid id 0 and description "<description>"
        Then the student will get notified by an error message

        Examples:
            | id | title      | complete | active | description |
            | 0  | 12/01/2023 | false    | true   | Due date 3  |