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

  Scenario: Specific product page
    Given the user is on product page
    When the user clicks on any product name
    Then the user should be on that specific product page
    When the user clicks on Back to products button
    Then the user should come back to products page
    And user closes the browser
