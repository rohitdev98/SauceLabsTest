Feature: Add / Remove items to cart button functionality

  Scenario: Adding items to cart
    Given the user is on the product page
    When the user clicks add to cart under any product
    Then the item will be added to cart

  Scenario: Removing items from the cart
    Given the user is on product page
    When the user adds an item to the cart
    And the user clicks on remove button
    Then the item will be removed from the cart
    Then user closes the browser
