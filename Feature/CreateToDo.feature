Feature:As a visitor, I want to create a Todo item

 Scenario: Add a new todo
    Given I am on the TodoMVC React page
    When I enter "Test Darshana" in the todo input
    And I press Enter
    Then I should see "Test Darshana" in the todo list
    Then I quit from page