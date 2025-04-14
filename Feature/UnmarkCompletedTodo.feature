Feature:As A visitor, I want to see Unmark a completed todo

Scenario: Unmark a completed todo
    Given I have a completed todo "Test Darshana"
    When I unmark "Test Darshana" as completed
    Then "Test Darshana" should appear as active
        Then I quit from page