import enums.Option;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;
import models.Command;

/**
 * InputCollector
 * - get input from keyboard
 * - stores history
 */
public class InputCollector {

  private Scanner scanner = new Scanner(System.in);
  private LinkedList<Command> commands = new LinkedList<>();
  private LinkedList<Command> inputs = new LinkedList<>();
  private static final int HISTORY_SIZE = 3;
  private static final String BASE_PROMPT_MSG = "Introduce %s: ";

  /**
   * Get input with prompt
   *
   * @param fieldName for print
   * @return inputString
   */
  public String inputForPrompt(String fieldName) {
    while (inputs.size() > HISTORY_SIZE) {
      inputs.remove();
    }
    System.out.println(String.format(BASE_PROMPT_MSG, fieldName));
    Command input = new Command(scanner.next());
    inputs.offer(input);

    return input.getInput();
  }

  /**
   * Get ID with prompt
   *
   * @return id
   */
  public int inputId() {
    while (inputs.size() > HISTORY_SIZE) {
      inputs.remove();
    }
    System.out.println("Enter the ID of the contact: ");

    String inputStr;
    while (true) {
      try {
        inputStr = String.valueOf(scanner.nextInt());
        break;
      } catch (InputMismatchException imE) {
        System.out.println("Only number accepted");
      }
    }

    Command input = new Command(inputStr);
    inputs.offer(input);

    return Integer.valueOf(input.getInput());
  }

  /**
   * get input and store it into history
   *
   * @return inputString
   */
  public Option inputCommand() {
    while (commands.size() > HISTORY_SIZE) {
      commands.remove();
    }

    Option option;
    while (true) {
      try {
        option = Option.find(scanner.nextInt());
        break;
      } catch (IllegalArgumentException iaE) {
        System.out.println("Please input one appropriate number.");
      }
    }

    Command command = new Command(option);
    commands.offer(command);
    return option;
  }

  /**
   * @return Copied commands
   */
  public LinkedList<Command> getCommands() {
    LinkedList<Command> copied = new LinkedList<>();
    Collections.copy(this.commands, copied);
    return copied;
  }
}

