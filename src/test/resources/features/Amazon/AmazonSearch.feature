Feature: Amazon Search

  Verify all search amazon functionalities.

  @search_one
  Scenario: Amazon search results verification
    Given go to amazon.com
    When enter search term "Selenium book"
    And click Search button
    Then verify that result with "Selenium Testing Tools Cookbook" is displayed anywhere in the results
    When enter search term "java OCA book"
    And click Search button
    Then verify that a result with "Selenium Testing Tools Cookbook" is not displayed anywhere in the results
