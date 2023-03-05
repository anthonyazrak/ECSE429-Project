Feature: Creat a todo item

    As a student
    I want to create a todo item for so that I can keep
    track of the due dates of all my assignments.

    Background:
        Given the server is running

    Scenario Outline: Create a todo item with valid input to represent a due date (Normal Flow)

        When a student adds a todo item with a title "<title>", description "<description>", and a status "<doneStatus>"
        Then a todo item with a title "<title>", description "<description>", and a status "<doneStatus>" is added

        Examples:
            | title       | description                 | doneStatus |
            | "3/05/2023" | "finish COMP202 assignment" | false      |
            | "4/06/2023" | "finish MATH323 assignment" | false      |

    Scenario Outline: Remove a todo item then add a todo item with valid input to represent a due date (Alternate Flow)

        Given the following todo item exists in the system:
            | id | title       | description                 | doneStatus |
            | 1  | "3/05/2023" | "finish COMP202 assignment" | false      |

        When a student removes a todo item with id 1
        And adds todo itemwith a title "<title>", description "<description>", and a status "<doneStatus>"
        Then a todo item with a title "<title>", description "<description>", and a status "<doneStatus>" is added


    Scenario Outline: Add a todo item with invalid title input to represent a due date (Error Flow)

        When a student adds a  with atodo item with a title "<title>", an invalid status "<doneStatus>", and a description "<description>"
        Then the todo item will not be added
        And the student will get notified by an error message
        And the response code will be 400

        Examples:
            | title       | description                 | doneStatus |
            | "3/05/2023" | "finish COMP202 assignment" | null       |
