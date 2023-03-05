Feature: Update the description of a category

    As a student
    I want to update the description of the category
    So that I can fix any typos or make the description more representative of my situation

    Background:
        Given the server is running


    Scenario Outline: Update the description of an existing category (Normal Flow)
        Given the following category exists in the system
            | title    | description |
            | ECSE XXX | XXXXXXX     |
        When a student updates the existing category with new description "<description>"
        Then the category will have updated description "<description>" and keep title "<title>"

        Examples:
            | title    | description                      |
            | ECSE XXX | Design Principles and Methods    |
            | ECSE XXX | Software Engineering in Practice |
            | ECSE XXX | Model-Based Programming          |

    Scenario Outline: Create a category and then update the category's description (Alternate Flow)
        When a student adds a category with a title "<title>" and a description "<description>"
        And a student updates the existing category with new description "<newDescription>"
        Then the category will have updated description "<newDescription>" and keep title "<title>"

        Examples:
            | title    | description                      | newDescription                |
            | ECSE 211 | Software Engineering in Practice | Design Principles and Methods |

    Scenario Outline: Update a category invalid id (Error Flow)
        When a student updates category with id 0 with description "<description>"
        Then the student will get notified by an error message
        And the response status code will be 404

        Examples:
            | description                      |
            | Description for invalid category |