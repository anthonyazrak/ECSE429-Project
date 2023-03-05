Feature: Update an assignment complete status

    As a student
    I want to be able to mark my todo items as complete
    so that I can keep track of when I finish them.

    Background:
        Given the server is running
        And the following todo items exist in the system:
            | id | title       | description                 | doneStatus |
            | 1  | "3/05/2023" | "finish COMP202 assignment" | false      |
            | 2  | "4/15/2023" | "finish COMP303 assignment" | false      |
            | 3  | "3/05/2023" | "finish COMP204 assignment" | false      |
            | 4  | "4/15/2023" | "finish COMP304 assignment" | false      |


    Scenario Outline: Update a todo item complete status (Normal Flow)

        When a student edits a todo item description with id "<id>" and status "<doneStatus>"
        Then the project with id "<id>" has status "<doneStatus>"

        Examples:
            | id | title       | description                     | doneStatus |
            | 1  | "3/05/2023" | "finish COMP202 assignment now" | false      |


    Scenario Outline: Add a todo item with valid input then edit the todo item complete status (Alternate Flow)

        When a student adds a todo item with id "<id>", title "<title>", status "<doneStatus>", and description "<description>"

        And a student edits a todo item status "<doneStatus>" with id "<id>"
        Then the todo item with id "<id>" has status "<doneStatus>"
        Examples:
            | id | title       | description                 | doneStatus |
            | 4  | "4/10/2023" | "finish COMP304 assignment" | false      |


    Scenario Outline: Update an todo item description with complete status (Error Flow)

        When a student edits a todo item description with id "<id>" and invalid status "<doneStatus>"
        Then the system reports an error message

        Examples:
            | id | title       | description                 | doneStatus |
            | 2  | "4/15/2023" | "finish COMP303 assignment" | null       |

