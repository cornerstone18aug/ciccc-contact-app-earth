package models;

import enums.Option;
import java.time.LocalDateTime;

/**
 * Command for history
 */
public class Command {

  private String input;
  private Option option;
  private LocalDateTime dateTime = LocalDateTime.now();

  public Command(Option option) {
    this.option = option;
  }

  public Command(String input) {
    this.input = input;
  }

  public String getInput() {
    return input;
  }

  public Option getOption() {
    return option;
  }

  public LocalDateTime getDateTime() {
    return dateTime;
  }
}
