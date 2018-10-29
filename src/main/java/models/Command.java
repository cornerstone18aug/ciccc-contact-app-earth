package models;

import enums.Option;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Command for history
 */
public class Command {
  private static final DateTimeFormatter FORMATTER =
      DateTimeFormatter.ofPattern("yyyy/MM/dd []H:mm:ss");
  private String input;
  private Option option;
  private LocalDateTime dateTime = LocalDateTime.now();

  public Command(Option option) {
    this.option = option;
  }

  public Command(Option option, String input) {
    this.option = option;
    this.input = input;
  }

  public String getInput() {
    return input;
  }

  public Option getOption() {
    return option;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(this.dateTime.format(FORMATTER)).append(" ");
    sb.append(option.name());

    if (this.input != null) {
      sb.append(" \"").append(input).append("\"");
    }

    return sb.toString();
  }
}
