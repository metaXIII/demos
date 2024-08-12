package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {

  private String today;
  private String actualAnswer;

  @When("I ask whether it's friday")
  public void i_ask_whether_it_s_friday() {
    actualAnswer = IsItFriday.isItFriday(today);
  }

  @Then("I should be told {string}")
  public void i_should_be_told(final String expectedAnswer) {
    assertEquals(actualAnswer, expectedAnswer);
  }

  @Given("today is {string}")
  public void today_is_sunday(final String today) {
    this.today = today;
  }

  private record IsItFriday() {
    static String isItFriday(final String today) {
      return today.equalsIgnoreCase("friday") ? "YEEEEESSS 😊" : "Nope";
    }
  }
}
