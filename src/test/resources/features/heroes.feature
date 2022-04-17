@wip
Feature: Heroes Page

  Background:
    Given User is on the Heroes Page
    And Heroes check box should be selected

  Scenario:Add a Hero
    When The user adds new hero
    Then verify new hero is added

  Scenario Outline: Search a Hero
    And The user should search hero name "<heroes>"
    Then verify the hero is found by named "<heroes>"
    Examples:
      | heroes    |
      | Narco     |
      | Bombasto  |
      | Celeritas |



  Scenario: Deleting a Hero
    When The user adds new hero
    And verify new hero is added
    Then The user deletes new hero
    And verify new hero is deleted
