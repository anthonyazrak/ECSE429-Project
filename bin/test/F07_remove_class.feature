Feature: Delete a category

    As a student
    I want to remove a class assigned to a category
    So that classes I no longer take are not tracked by the application

    Background:
        Given the server is running

    Scenario Outline: Delete a category with the title and description of a school class (Normal Flow)
        Given the following category exists in the system
            | title    | description |
            | ECSE XXX | XXXXXXX     |
        When a student deletes a category with id of the existing category
        Then a category with title "<title>" and description "<description>" will be deleted

        Examples:
            | title    | description |
            | ECSE XXX | XXXXXXX     |

    Scenario Outline: Create a category then delete that category (Alternate Flow)
        When a student adds a category with a title "<title>" and a description "<description>"
        And a student deletes a category with id of the existing category
        Then a category with title "<title>" and description "<description>" will be deleted

        Examples:
            | title    | description                      |
            | ECSE 428 | Software Engineering in Practice |

    Scenario Outline: Attempt to delete a category with an invalid id (Error Flow)
        When a student deletes a category with id 0
        Then the student will get notified by an error message
        And the response status code will be 404