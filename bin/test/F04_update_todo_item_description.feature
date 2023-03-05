Feature: Update a todo item title

    As a student
    I want to update the title of the todo item so that
    I can fix any types or make the title more representative of the task.

    Background:
        Given the server is running
        And the following todo items exist in the system:
            | id | title       | description                 | doneStatus |
            | 1  | "3/05/2023" | "finish COMP202 assignment" | false      |
            | 2  | "4/15/2023" | "finish COMP303 assignment" | false      |
            | 3  | "3/05/2023" | "finish COMP204 assignment" | false      |

    Scenario Outline: Update a todo item title (Normal Flow)

        When a student edits a todo item title with id "<id>" and title "<title>"
        Then the todo item with id "<id>" has title "<title>"

        Examples:
            | id | title       | description                                            | doneStatus |
            | 1  | "3/05/2023" | "finish the first 2 numbers of the COMP202 assignment" | false      |

    Scenario Outline: Add a todo item with valid input then edit the todo item title (Alternate Flow

        When a student adds a todo item with id "<id>" and title "<title>"

        And a student edits a todo item with id "<id>"
        Then the todo item with id "<id>" has title "<title>"

        Examples:
            | id | title       | description                 | doneStatus |
            | 4  | "7/02/2023" | "finish COMP206 assignment" | false      |

    Scenario Outline: Update a todo item title with invalid input (Error Flow)

        When a student edits a todo item title with invalid id "<id>" and title "<title>"
        Then the system reports an error message

        Examples:
            | id | title       | description                 | doneStatus |
            | 1  | "3/05/2023" | "finish COMP202 assignment" | false      |