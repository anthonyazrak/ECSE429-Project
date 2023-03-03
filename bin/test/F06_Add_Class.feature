Feature: Assign a class to a category

    As a student
    I want to assign each of my classes to a category
    so that I can keep track of all my classes

    Background:
        Given the server is running


    Scenario Outline: Create a category with the title and description of a school class (Normal Flow)
        When a student adds a category with a title "<title>" and a description "<description>"
        Then a category with title "<title>" and description "<description>" will be added

        Examples:
            | title    | description                      |
            | ECSE 428 | Software Engineering in Practice |
            | ECSE 429 | Software Validation              |
            | ECSE 310 | Thermodynamics in Computing      |

    Scenario Outline: Delete a category and then create a category (Alternate Flow)
        Given the following category exists in the system
            | title    | description |
            | ECSE XXX | XXXXXXX     |
        When a student deletes a category with id of the existing category
        And a student adds a category with a title "<title>" and a description "<description>"
        Then a category with title "<title>" and description "<description>" will be added

        Examples:
            | title    | description                   |
            | ECSE 211 | Design Principles and Methods |

    Scenario Outline: Create a category empty title (Error Flow)
        When a student adds a category with a title "<title>" and a description "<description>"
        Then the student will get notified by an error message
        And the response status code will be 400

        Examples:
            | title | description |
            |       |             |