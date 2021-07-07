#Author: rohit-dev.r-b@capgemini.com
Feature: Product Page in Saucedemo Webpage

		

  Scenario: Logging into Products Page
    Given user is on login page
    When user enters valid credentials
    And user clicks on login button
    Then user should be on products page
    

  Scenario: Sortbox in products page
    Given the user is on product page
    When user clicks on sort icon
    And select sort option as required
    Then products will be sorted
    

   

  Scenario: Adding items to cart
    Given the user is on product page
    When the user clicks add to cart under any product
    Then the item will be added to cart

  Scenario: Removing items from the cart
    Given the user is on product page
    When the user adds an item to the cart
    And  the user clicks on remove button
    Then the item will be removed from the cart
    

  Scenario: Specific product page
    Given the user is on product page
    When the user clicks on any product name
    Then the user should be on that specific product page
    When the user clicks on Back to products button
    Then the user should come back to products page
    

  Scenario: Resetting the app state
    Given the user is on product page
    When the user clicks on hamburger menu
    And clicks REST APP STATE
    Then the app is reset
    

  Scenario: Logout from the application
    Given the user is on product page
    When the user clicks on hamburger menu
    And clicks Log Out button
    Then the user is logged out of the application
    Then user closes the browser
   