Feature: Add, Delete, and Edit recipe
  The owner can add, edit and delete recipes.
  The Coffeemaker will return True if added success.
  Scenario: The owner add mocha recipe to coffeemaker
    Given coffeemaker is set
    When The owner add mocha recipe to coffeemaker
    Then coffeemaker return true

  Scenario: The owner edit recipe 1
    Given coffeemaker is set
    When The owner edit all ingredients and price from recipe 1 to 5
    Then recipe 1 will return coffee 5, milk 5, chocolate 5, sugar 5 and price 5


  Scenario: The owner delete recipe1
    Given coffeemaker is set
    When The owner delete recipe 1 in coffeemaker
    Then coffeemaker return name of recipe 1
