Feature: Is it friday ? Everybody want to know if it is friday

  Scenario Outline: Days are not Friday with examples
    Given today is "<day>"
    When I ask whether it's friday
    Then I should be told "Nope"

    Examples:
      | day       |
      | Monday    |
      | Tuesday   |
      | Wednesday |
      | Thursday  |
      | Saturday  |
      | Sunday    |

  Scenario Outline: Friday is Friday with examples
    Given today is "<day>"
    When I ask whether it's friday
    Then I should be told "YEEEEESSS 😊"

    Examples:
      | day    |
      | friday |
