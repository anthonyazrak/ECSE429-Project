Feature: Update the title of a category

    As a student
    I want to update the title of the category
    So that I can fix any typos or make the title more representative of my situation

    Background:
        Given the server is running


    Scenario Outline: Update the title of an existing category (Normal Flow)
        Given the following category exists in the system
            | title    | description |
            | ECSE XXX | XXXXXXX     |
        When a student updates the existing category with new title "<title>"
        Then the category will have updated title "<title>" and keep description "<description>"

        Examples:
            | title    | description |
            | ECSE 428 | XXXXXXX     |
            | ECSE 429 | XXXXXXX     |
            | ECSE 310 | XXXXXXX     |

    Scenario Outline: Create a category and then update the category's title (Alternate Flow)
        When a student adds a category with a title "<title>" and a description "<description>"
        And a student updates the existing category with new title "<newTitle>"
        Then the category will have updated title "<newTitle>" and keep description "<description>"

        Examples:
            | title    | description                   | newTitle |
            | ECSE 211 | Design Principles and Methods | ECSE 311 |

    Scenario Outline: Update a category empty title (Error Flow)
        Given the following category exists in the system
            | title    | description |
            | ECSE XXX | XXXXXXX     |
        When a student updates the existing category with new title "<title>"
        Then the student will get notified by an error message
        And the response status code will be 400

        Examples:
            | title |
            |       |