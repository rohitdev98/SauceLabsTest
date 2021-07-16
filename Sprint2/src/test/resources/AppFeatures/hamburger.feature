Feature: Hamburger menu options functionality

  Scenario: Resetting the app state
    Given the user is on the product page
    When the user clicks on hamburger menu
    And clicks REST APP STATE
    Then the app is reset

  Scenario: Logout from the application
    Given the user is on product page
    When the user clicks on hamburger menu
    And clicks Log Out button
    Then the user is logged out of the application
    Then user closes the browser
