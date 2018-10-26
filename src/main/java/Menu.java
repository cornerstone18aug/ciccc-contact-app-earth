import enums.Option;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import models.Contact;

/**
 * Menu
 */
public class Menu {

  private List<Contact> contacts;
  private InputCollector ic = new InputCollector();
  private Scanner user = new Scanner(System.in);
  /* This variable will have the value FALSE and if the user select exit it will be true and finish the app */
  private boolean exit = false;
  /* This variable will have the option from the user */
  private Option opt;
  private final String MENU_STR =
      "\n\n====== CICCC Contact App =====\n" +
          "  by Enrique, Alejandro, Masa \n" +
          "==============================\n" +
          "%s\n" +
          "==============================\n" +
          "Select an option: ";

  /**
   * Constructor
   *
   * @param contacts contact list
   */
  Menu(List<Contact> contacts) {
    this.contacts = contacts;
  }

  /**
   * This is the method for the main menu
   */
  void execute() {
    // Label for exit
    exit:
    while (true) {
      /* This while will have the switch with the different options that the user can select */
      System.out.println(String.format(this.MENU_STR,
          Arrays.stream(Option.values())
              .map(op -> op.menuMsg)
              .collect(Collectors.joining("\n"))));
      switch (ic.inputCommand()) {
        case CREATE:
          if (!addUser()) {
            System.out.println("Register canceled.");
          }
          break;
        case LIST:
          listUsers();
          break;
        case SHOW_DETAIL:
          showDetails(ic.inputId());
          break;
        case HISTORY:
          ic.getCommands().forEach(cmd -> {
            System.out.println(cmd.getDateTime().toString());
            System.out.println(cmd.getOption().name());
          });
          break;
        case EXIT:
          System.out.println("Goodbye see you soon!!!");
          break exit;
        default:
          throw new IllegalStateException("Unexpected enums.Option detected! ");
      }
    }
  }

  /**
   * This method will make all the process of add an user to the list
   *
   * @return registered or not
   */
  boolean addUser() {
    Contact con = new Contact();

    System.out.println("Your are going to add a new contact!!!\n"
        + "To quit, input \"quit:\"\n");

    /* We ask for the email before and know if is anyone with that email */
    con.setEmail(ic.inputForPrompt("unique email"));
    if (isQuit(con.getEmail())) {
      return false;
    }

    while (!this.checkEmail(con.getEmail())) {
      con.setEmail(ic.inputForPrompt("unique email"));
      if (isQuit(con.getEmail())) {
        return false;
      }
    }

    /* We ask for the first name before and know if is anyone with that email */
    con.setFirstName(ic.inputForPrompt("first name"));
    if (isQuit(con.getFirstName())) {
      return false;
    }

    /* We ask for the last name before and know if is anyone with that email */
    con.setLastName(ic.inputForPrompt("last name"));
    if (isQuit(con.getLastName())) {
      return false;
    }

    /* We ask for the number home before and know if is anyone with that email */
    con.setNumberHome(ic.inputForPrompt("number home"));
    if (isQuit(con.getNumberHome())) {
      return false;
    }

    while (!this.checkPhones(con.getNumberHome())) {
      con.setNumberHome(ic.inputForPrompt("number home"));
      if (isQuit(con.getNumberHome())) {
        return false;
      }
    }

    /* We ask for the number cell phone before and know if is anyone with that email */
    con.setNumberCellphone(ic.inputForPrompt("number cell phone"));
    if (isQuit(con.getNumberCellphone())) {
      return false;
    }

    while (!checkPhones(con.getNumberCellphone())) {
      con.setNumberCellphone(ic.inputForPrompt("number cell phone"));
      if (isQuit(con.getNumberCellphone())) {
        return false;
      }
    }

    /* We ask for the direction home before and know if is anyone with that email */
    con.setDirection(ic.inputForPrompt("direction"));
    if (isQuit(con.getDirection())) {
      return false;
    }

    // We are incrementing the id of the contact
    con.setId(contacts.size());

    // We add the information of the contact
    contacts.add(con);
    System.out.printf("ID: %s, First Name : %s registered!", con.getId(), con.getFirstName());

    return true;
  }

  /**
   * isQuit
   *
   * @param input input
   * @return when String indicates quit, returns true
   */
  private boolean isQuit(String input) {
    return "quit:".equals(input);
  }

  /**
   * This method will show to the user all the contacts that he have
   */
  private void listUsers() {
    for (Contact con : this.contacts) {
      System.out.printf("#%s <%s>\n", con.getId(), con.getFirstName());
    }

    System.out.println("Do you want to see more information of any contact? y/n\n");
    String answer = user.next().toLowerCase();

    /* We check if the answer is YES or NO and depend of the answer we show the details of the contact */
    switch (answer) {
      case "yes":
      case "y":
        int id = ic.inputId();
        showDetails(id);
        break;
      case "no":
      case "n":
        System.out.println("Redirecting to the menu...");
        break;
      default:
        System.out.println("That is not an answer!!! \n" + "Redirecting to the menu...");
        break;
    }
  }

  /**
   * Check email unique
   *
   * @param email email
   * @return when it's valid returns true
   */
  private boolean checkEmail(String email) {
    for (Contact con : this.contacts) {
      if (con.getEmail().equals(email)) {
        System.out.println("There is one contact with that email, introduce another one!!!");
        return false;
      }
    }
    return true;
  }

  /**
   * Check phone number
   *
   * @param phone phone
   * @return when it's valid returns true
   */
  private boolean checkPhones(String phone) {
    try {
      Integer.parseInt(phone);
      return true;
    } catch (NumberFormatException e) {
      System.out.println("Only you can introduce numbers in the phone!!!");
      return false;
    }
  }

  /**
   * showDetails
   *
   * @param id input
   */
  void showDetails(int id) {
    Contact con = contacts.get(id);
        /* With the id we know which contact we have to search so we use the id following the details of the contact
          like the name, email, etc. */
    if (con == null) {
      System.out.println("ID not found.");
    } else {
      /* We show the details of the contact */
      System.out.println(
          "ID: " + con.getId() + ", First Name: " + con.getFirstName()
              + ", Last Name: " + con.getLastName() +
              ", Email: " + con.getEmail() + ", Home Phone: " + con
              .getNumberHome() + ", Mobile: " + con.getNumberCellphone() +
              ", Direction: " + con.getDirection()
      );
    }
  }

}
