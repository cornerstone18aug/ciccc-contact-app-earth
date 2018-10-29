import enums.Option;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import models.Command;

/**
 * InputCollector
 */
public class InputCollector {

  private Scanner scanner = new Scanner(System.in);
  private List<Command> commands = new ArrayList<>();
  private List<Command> inputs = new ArrayList<>();
  private static final int RETURN_HISTORY_SIZE = 3;
  private static final String BASE_PROMPT_MSG = "Introduce %s: ";
  private static final String AFTER_DOING_STH_MSG = "Input Enter to back to Menu.";

  /**
   * Get input field for prompt
   *
   * @param fieldName for print
   * @return inputString
   */
  public String inputFieldForPrompt(String fieldName) {
    System.out.println(String.format(BASE_PROMPT_MSG, fieldName));
    Command input = new Command(Option.CREATE, scanner.nextLine());
    inputs.add(input);

    return input.getInput();
  }

  /**
   * Get enter for prompt
   */
  public void enterForPrompt() {
    System.out.println(AFTER_DOING_STH_MSG);
    scanner.nextLine();
  }

  /**
   * Get input with prompt
   *
   * @param msg for print
   * @return inputString
   */
  public String inputForPrompt(Option option, String msg) {
    System.out.println(msg);
    String input = scanner.nextLine();

    if (input != null && !input.trim().equals("")) {
      Command command = new Command(option, input);
      inputs.add(command);
    }

    return input;
  }

  /**
   * Get ID with prompt
   *
   * @return id
   */
  public int inputId(Option option) {
    System.out.println("Enter the ID of the contact: ");

    String inputStr;
    while (true) {
      try {
        inputStr = String.valueOf(scanner.nextInt());
        scanner.nextLine();
        break;
      } catch (InputMismatchException imE) {
        System.out.println("Only number accepted");
        scanner = new Scanner(System.in);
      }
    }

    Command input = new Command(option, inputStr);
    inputs.add(input);

    return Integer.valueOf(input.getInput());
  }

  /**
   * get input and store it into history
   *
   * @return inputString
   */
  public Option inputCommand() {
    Option option;
    while (true) {
      try {
        option = Option.find(scanner.nextInt());
        scanner.nextLine();
        break;
      } catch (IllegalArgumentException | InputMismatchException e) {
        System.out.println("Please input one appropriate number.");
        scanner = new Scanner(System.in);
      }
    }

    Command command = new Command(option);
    commands.add(command);
    return option;
  }

  /**
   * isQuit
   *
   * @param input input
   * @return when String indicates quit, returns true
   */
  public boolean isQuit(String input) {
    return "quit:".equals(input);
  }

  /**
   * @return commands
   */
  public List<Command> getCommands() {
    // If last command was Show commands, omit
    Command removedComHistory = null;
    if (this.commands.size() > 0 &&
        this.commands.get(this.commands.size() - 1).getOption() == Option.CMD_HISTORY) {
      removedComHistory = this.commands.remove(this.commands.size() - 1);
    }

    List<Command> result = new ArrayList<>();
    if (this.commands.size() < RETURN_HISTORY_SIZE) {
      result.addAll(this.commands);
    } else {
      result.addAll(this.commands.subList(this.commands.size() - RETURN_HISTORY_SIZE, this.commands.size()));
    }

    // Re-add the removed command
    if (removedComHistory != null) {
      this.commands.add(removedComHistory);
    }

    return result;
  }

  /**
   * @return inputs
   */
  public List<Command> getInputs() {
    if (this.inputs.size() < RETURN_HISTORY_SIZE) {
      return this.inputs;
    } else {
      return this.inputs.subList(this.inputs.size() - RETURN_HISTORY_SIZE, this.inputs.size());
    }
  }


}

