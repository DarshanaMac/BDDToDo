Feature:As a visitor, I want to create a Todo item

 Scenario: Filter active todos
    Given I am on the TodoMVC React page
    And I have todos "TASK A" and "TASK B" marked as active
    When I click on the "Active" filter
    Then I should see "TASK A" and "TASK B"
      Then I quit from page