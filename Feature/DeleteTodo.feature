Feature:As A visitor, I want to delete

  Scenario: Delete a todo
    Given I have added a todo "Pay bills"
    When I delete the todo "Pay bills"
    Then "Pay bills" should not be visible in the list
        Then I quit from page