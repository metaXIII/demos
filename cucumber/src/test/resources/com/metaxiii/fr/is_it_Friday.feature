Feature: Is it friday ?

  Scenario: Other is not Friday
    Given today is "Other"
    When I ask whether it's friday
    Then I should be told "Nope"

  Scenario: Monday is not Friday
    Given today is "monday"
    When I ask whether it's friday
    Then I should be told "Nope"

  Scenario: Tuesday is not Friday
    Given today is "tuesday"
    When I ask whether it's friday
    Then I should be told "Nope"

  Scenario: Wednesday is not Friday
    Given today is "wednesday"
    When I ask whether it's friday
    Then I should be told "Nope"

  Scenario: Thursday is not Friday
    Given today is "thursday"
    When I ask whether it's friday
    Then I should be told "Nope"

  Scenario: Friday is Friday
    Given today is "friday"
    When I ask whether it's friday
    Then I should be told "YEEEEESSS 😊"

  Scenario: Saturday is not Friday
    Given today is "saturday"
    When I ask whether it's friday
    Then I should be told "Nope"

  Scenario: Sunday is not Friday
    Given today is "sunday"
    When I ask whether it's friday
    Then I should be told "Nope"
