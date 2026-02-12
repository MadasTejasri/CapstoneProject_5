Feature: WordPress Capstone Test

  Scenario: Verify WordPress download and photo directory
    Given I launch WordPress
    Then I verify the home page title
    When I hover on Extend and click Get WordPress
    Then I verify heading on Get WordPress page
    When I navigate to Photo Directory
    And I search for "Peacock"
    Then I verify images are displayed
