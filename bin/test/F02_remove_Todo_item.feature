Feature: Remove an due date

    As a student
    I want to remove a due date assigned to a todo item
    so that due dates I no longer have to do are not tracked by the application.

    Background:
        Given the server is running
        And the following todo item exists in the system:
            | title      | doneStatus | description |
            | 10/01/2023 | false      | Due date 1  |


    Scenario Outline: Remove a todo item representing a due date with valid input (Normal Flow)

        When a student removes a todo item with id "<id>"
        Then a todo item with id "<id>" is removed

        Examples:
            | title | doneStatus | description |



    Scenario Outline: Add a todo item with valid input then remove the todo item (Alternate Flow)

        When a student adds a todo item with a title "<title>", a doneStatus "<doneStatus>", and a description "<description>"
        And they remove a todo item with id "<id>"
        Then a todo item with id is removed

        Examples:
            | title | doneStatus | description |


    Scenario Outline: Remove a todo item with invalid id (Error Flow)

        When a student removes a todo item with invalid id "<id>"
        Then the student will get notified by an error message

        Examples:
            | id | title      | doneStatus | description |
            | 0  | 13/01/2023 | false      | Due date 4  |