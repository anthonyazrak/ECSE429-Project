Feature: Update a due date doneStatus

    As a student
    I want to be able to mark my due dates as complete
    so that I can keep track of when I finish them.

    Background:
        Given the server is running
        And the following todo item exists in the system:
            | id | title      | doneStatus | description |
            | 1  | 10/01/2023 | false      | Due date 1  |
            | 2  | 11/01/2023 | false      | Due date 2  |
            | 3  | 12/01/2023 | false      | Due date 3  |


    Scenario Outline: Update a due date doneStatus (Normal Flow)

        When a student edits a todo item status with id "<id>" and doneStatus "<doneStatus>"
        Then the todo item with id "<id>" has doneStatus "<doneStatus>"

        Examples:
            | id | title      | doneStatus | description |
            | 1  | 12/01/2023 | true       | Due date 1  |


    Scenario Outline: Add an due date with valid input then edit the due date doneStatus (Alternate Flow)

        When a student adds a todo item with a title "<title>", a doneStatus "<doneStatus>", and a description "<description>"
        And a student edits a todo item status with id and doneStatus "<doneStatus>"
        Then the todo item with id has doneStatus "<doneStatus>"
        Examples:
            | title      | doneStatus | description |
            | 13/01/2023 | false      | Due date 4  |


    Scenario Outline: Update a due date description with doneStatus (Error Flow)

        When a student edits a todo item doneStatus with id "<id>" and invalid doneStatus "<doneStatus>"
        Then the student will get notified by an error message

        Examples:
            | id | title      | doneStatus | description |
            | 3  | 12/01/2023 | false      | Due date 3  |
