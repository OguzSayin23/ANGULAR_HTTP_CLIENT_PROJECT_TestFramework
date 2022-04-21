@wip
Feature: Heroes Page

  Background:
    Given User is on the Heroes Page
    And Heroes check box should be selected

  Scenario:Add a Hero (Positive scenario)
    When The user adds new hero
    Then verify new hero is added

  Scenario:Add a Hero (Negative scenario)
    When The user adds new hero name with "  "
    Then verify no new hero is added with "  " name

  Scenario Outline: Search a Hero
    When The user should search hero name "<heroes>"
    Then verify the hero is found by named "<heroes>"
    Examples:
      | heroes    |
      | Narco     |
      | Bombasto  |
      | Celeritas |

  Scenario: Deleting a Hero
    When The user adds new hero
    And verify new hero is added
    And The user deletes new hero
    Then verify new hero is deleted
