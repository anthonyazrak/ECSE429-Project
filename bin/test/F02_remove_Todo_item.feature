Feature: Remove a todo item

    As a student
    I want to remove a todo entry so that completed
    tasks are not tracked by the application.

    Background:
        Given the server is running
        And the following todo items exist:
            | id | title       | description                 | doneStatus |
            | 1  | "3/05/2023" | "finish COMP202 assignment" | false      |
            | 2  | "3/05/2023" | "finish COMP203 assignment" | false      |

    Scenario Outline: Remove a todo item representing a due date with valid input (Normal Flow)

        When a student removes a todo item with id "<id>"
        Then a todo item with id "<id>" is removed

        Examples:
            | id | title       | description                 | doneStatus |
            | 2  | "3/05/2023" | "finish COMP203 assignment" | false      |


    Scenario Outline: Add a todo item with valid input then remove the todo item (Alternative Flow)

        When a student adds a todo item with an id of "<id>" and description of "<description>" and completed of "<completed>"

        And a student removes a todo item with id "<id>"
        Then a todo item with id "<id>" is removed

        Examples:
            | id | title | description | doneStatus |

    Scenario Outline: Remove a project with invalid id (Error Flow)

        When a student removes a todo item with invalid id "<id>"
        Then a todo item with id "<id>" is not removed
        And the system reports an error

            | id | title       | description                 | doneStatus |
            | 1  | "3/05/2023" | "finish COMP202 assignment" | false      |