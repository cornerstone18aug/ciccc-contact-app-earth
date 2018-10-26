import enums.Option;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import models.Contact;

/**
 * Menu
 */
public class Menu {

  private List<Contact> contacts;
  private InputCollector ic = new InputCollector();
  private final String MENU_STR =
      "\n\n====== CICCC Contact App =====\n" +
          "  by Enrique, Alejandro, Masa \n" +
          "==============================\n" +
          "%s\n" +
          "==============================\n" +
          "Select an option: ";
  private final String AFTER_DOING_STH_MSG = "Input Enter to back to Menu.";

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
  public void execute() {
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
          if (!addContact()) {
            System.out.println("Register canceled.");
          }
          break;
        case LIST:
          listContacts();
          break;
        case SHOW_DETAIL:
          showDetails(ic.inputId());
          break;
        case HISTORY:
          showHistory();
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
  public boolean addContact() {
    Contact con = new Contact();

    System.out.println("Your are going to add a new contact!!!\n"
        + "To quit, input \"quit:\"\n");

    /* We ask for the email before and know if is anyone with that email */
    con.setEmail(ic.inputFieldForPrompt("unique email"));
    if (isQuit(con.getEmail())) {
      return false;
    }

    while (!this.checkEmail(con.getEmail())) {
      con.setEmail(ic.inputFieldForPrompt("unique email"));
      if (isQuit(con.getEmail())) {
        return false;
      }
    }

    /* We ask for the first name before and know if is anyone with that email */
    con.setFirstName(ic.inputFieldForPrompt("first name"));
    if (isQuit(con.getFirstName())) {
      return false;
    }

    /* We ask for the last name before and know if is anyone with that email */
    con.setLastName(ic.inputFieldForPrompt("last name"));
    if (isQuit(con.getLastName())) {
      return false;
    }

    /* We ask for the number home before and know if is anyone with that email */
    con.setNumberHome(ic.inputFieldForPrompt("number home"));
    if (isQuit(con.getNumberHome())) {
      return false;
    }

    while (!this.checkPhones(con.getNumberHome())) {
      con.setNumberHome(ic.inputFieldForPrompt("number home"));
      if (isQuit(con.getNumberHome())) {
        return false;
      }
    }

    /* We ask for the number cell phone before and know if is anyone with that email */
    con.setNumberCellphone(ic.inputFieldForPrompt("number cell phone"));
    if (isQuit(con.getNumberCellphone())) {
      return false;
    }

    while (!checkPhones(con.getNumberCellphone())) {
      con.setNumberCellphone(ic.inputFieldForPrompt("number cell phone"));
      if (isQuit(con.getNumberCellphone())) {
        return false;
      }
    }

    /* We ask for the direction home before and know if is anyone with that email */
    con.setDirection(ic.inputFieldForPrompt("direction"));
    if (isQuit(con.getDirection())) {
      return false;
    }

    // We are incrementing the id of the contact
    con.setId(contacts.size());

    // We add the information of the contact
    contacts.add(con);

    System.out.println("ID : " + con.getId() +  "Registered!");
    this.showDetails(con.getId());

    return true;
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
   * This method will show to the user all the contacts that he have
   */
  private void listContacts() {
    System.out.println("==============================");
    for (Contact con : this.contacts) {
      System.out.printf("#%s <%s>\n", con.getId(), con.getFirstName());
    }
    System.out.println("==============================");

    String answer = ic.inputForPrompt("Do you want to see more information of any contact? y/n");
    /* We check if the answer is YES or NO and depend of the answer we show the details of the contact */
    switch (answer.toLowerCase()) {
      case "y":
        showDetails(ic.inputId());
        break;
      default:
        break;
    }
  }

  /**
   * Check email unique
   *
   * @param email email
   * @return when it's valid returns true
   */
  public boolean checkEmail(String email) {
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
  public boolean checkPhones(String phone) {
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
  private void showDetails(int id) {
    if (id < 0 || this.contacts.size() - 1 < id) {
      System.out.println("ID not found.");
      return;
    }
    Contact con = contacts.get(id);
    /**
     * With the id we know which contact we have to search
     *  so we use the id following the details of the contact like the name, email, etc.
     */
    /* We show the details of the contact */
    System.out.println(
        "==================================" +
            "\nID: " + con.getId() +
            "\nFirst Name: " + con.getFirstName() +
            "\nLast Name: " + con.getLastName() +
            "\nEmail: " + con.getEmail() +
            "\nHome Phone: " + con.getNumberHome() +
            "\nMobile: " + con.getNumberCellphone() +
            "\nDirection: " + con.getDirection() +
            "\n=================================="
    );
    ic.inputForPrompt(AFTER_DOING_STH_MSG);
  }

  private void showHistory() {
    System.out.println("==============================");
    ic.getCommands().forEach(cmd -> {
      System.out.print(cmd.getDateTime().toString() + " : ");
      System.out.println(cmd.getOption().name());
    });
    System.out.println("==============================");
    ic.inputForPrompt(AFTER_DOING_STH_MSG);
  }

}
