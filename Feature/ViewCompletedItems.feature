Feature:As a visitor, I want to view completed Todo items

Scenario: Mark a todo as completed
    Given I have added a todo "Test Darshana"
    When I mark "Test Darshana" as completed
    Then "Test Darshana" should appear as completed
    Then I quit from page