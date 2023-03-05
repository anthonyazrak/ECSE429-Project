Feature: View a specific todo item by id

    As a student
    I want to see all todo list items that are assigned
    to a specific due date

    Background:
        Given the server is running
        And the following todo items exist
            | id | title       | description                 | doneStatus |
            | 1  | "3/05/2023" | "finish COMP202 assignment" | false      |
            | 2  | "4/15/2023" | "finish COMP303 assignment" | false      |
            | 3  | "3/05/2023" | "finish COMP204 assignment" | false      |

    Scenario Outline: View a specific todo item (Normal FLow)

        When a student views a todo item with id "<id>"
        Then the student should see the todo item with id "<id>"

            | id | title       | description                 | doneStatus |
            | 1  | "3/05/2023" | "finish COMP202 assignment" | false      |


    Scenario Outline: Add a todo item with valid input then view the todo item (Alternative Flow)

        When a student adds a todo item with id "<id>", title "<title>", description "<description>", completed "<completed>"

        And a student views a todo item with id "<id>"
        Then the system displays a todo item with id "<id>", title "<title>", description "<description>", completed "<completed>"

        Examples:
            | id | title       | description                 | doneStatus |
            | 4  | "6/12/2023" | "finish ECSE429 assignment" | false      |

    Scenario Outline: View a todo item with invalid id (Error Flow)

        When a student views a todo item with invalid id "<id>"
        Then the system displays an error message

        Examples:
            | id | title       | description                 | doneStatus |
            | 0  | "6/12/2023" | "finish ECSE429 assignment" | false      |
