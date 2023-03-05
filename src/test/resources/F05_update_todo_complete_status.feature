Feature: Update a due date complete status

    As a student
    I want to be able to mark my due dates as complete
    so that I can keep track of when I finish them.

    Background:
        Given the server is running
        And the following todo item exists in the system:
            | id   | title      | complete | active | description |
            | 1000 | 10/01/2023 | false    | true   | Due date 1  |
            | 1001 | 11/01/2023 | false    | true   | Due date 2  |
            | 1002 | 12/01/2023 | false    | true   | Due date 3  |


    Scenario Outline: Update a due date complete status (Normal Flow)

        When a student edits a todo item status with id "<id>" and complete status "<complete>"
        Then the todo item with id "<id>" has complete status "<complete>"

        Examples:
            | id   | title      | complete | active | description |
            | 1000 | 10/01/2023 | false    | true   | Due date 1  |


    Scenario Outline: Add an due date with valid input then edit the due date complete status (Alternate Flow)

        When a student adds a todo item with a title "<title>", a complete "<complete>", an active "<active>", and a description "<description>"
        And a student edits a todo item status with id and complete status "<complete>"
        Then the todo item with id has complete status "<complete>"
        Examples:
            | title      | complete | active | description |
            | 13/01/2023 | false    | true   | Due date 4  |


    Scenario Outline: Update a due date description with complete status (Error Flow)

        When a student edits a todo item complete status with id "<id>" and invalid complete status "<complete>"
        Then the student will get notified by an error message

        Examples:
            | id   | title      | complete | active | description |
            | 1002 | 12/01/2023 | false    | true   | Due date 3  |
