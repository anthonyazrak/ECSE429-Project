Feature: View a specific category

    As a student
    I want to see a specific class I’ve assigned to a category
    So that I can see all the classes I am currently taking

    Background:
        Given the server is running

    Scenario Outline: View a specific class (Normal Flow)

        Given the following category exists in the system
            | title    | description |
            | ECSE XXX | XXXXXXX     |
        When a student views a category with id of the existing category
        Then a category with title "<title>" and description "<description>" will be displayed

        Examples:
            | title    | description |
            | ECSE XXX | XXXXXXX     |

    Scenario Outline: Create a category then view that category (Alternate Flow)
        When a student adds a category with a title "<title>" and a description "<description>"
        And a student views a category with id of the existing category
        Then a category with title "<title>" and description "<description>" will be displayed

        Examples:
            | title    | description                      |
            | ECSE 428 | Software Engineering in Practice |

    Scenario Outline: Attempt to view a category with an invalid id (Error Flow)
        When a student views a category with id 0
        Then the student will get notified by an error message
        And the response status code will be 404