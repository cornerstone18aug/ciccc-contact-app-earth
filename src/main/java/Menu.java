import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Menu
 */
public class Menu {

  private List<Contact> contacts;
  private Scanner user = new Scanner(System.in);
  /* This variable will have the value FALSE and if the user select exit it will be true and finish the app */
  private boolean exit = false;
  /* This variable will have the option from the user */
  private int opt;
  private final String MENU_STR =
      "\n\n------ CICCC Contact App -----\n" +
      "  by Enrique, Alejandro, Masa \n" +
      "------------ MENU ------------\n" +
      "-- 1. Create a new contact ---\n" +
      "-- 2. List all the contacts --\n" +
      "---------- 3. Exit -----------\n" +
      "------------------------------\n" +
      "Select an option: ";
  private static final String BASE_PROMPT_MSG = "Introduce %s: ";


  /**
   * Constructor
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
      System.out.println(this.MENU_STR);
      try {
        opt = user.nextInt();
        switch (opt) {
          case 1:
            if (!addUser()) {
              System.out.println("Register canceled.");
            }
            break;
          case 2:
            listUsers();
            break;
          case 3:
            break exit;
          default:
            System.out.println("Only numbers between 1 and 3!!!");
        }
      } catch (InputMismatchException e) {
        System.out.println("You have to introduce a number!!!");
      }
    }
    System.out.println("Goodbye see you soon!!!");
  }

  /**
   * This method will make all the process of add an user to the list
   * @return registered or not
   */
  boolean addUser() {
    Contact con = new Contact();

    System.out.println("Your are going to add a new contact!!!\n"
        + "To quit, input \"quit:\"\n");

    /* We ask for the email before and know if is anyone with that email */
    this.printPrompt("unique email");
    con.setEmail(user.next());
    if (isQuit(con.getEmail())) {
      return false;
    }

    while (!this.checkEmail(con.getEmail())) {
      this.printPrompt("unique email");
      con.setEmail(user.next());
      if (isQuit(con.getEmail())) {
        return false;
      }
    }

    /* We ask for the first name before and know if is anyone with that email */
    this.printPrompt("first name");
    con.setFirstName(user.next());
    if (isQuit(con.getFirstName())) {
      return false;
    }

    /* We ask for the last name before and know if is anyone with that email */
    this.printPrompt("last name");
    con.setLastName(user.next());
    if (isQuit(con.getLastName())) {
      return false;
    }

    /* We ask for the number home before and know if is anyone with that email */
    this.printPrompt("number home");
    con.setNumberHome(user.next());
    if (isQuit(con.getNumberHome())) {
      return false;
    }

    while (!this.checkPhones(con.getNumberHome())) {
      this.printPrompt("number home");
      con.setNumberHome(user.next());
      if (isQuit(con.getNumberHome())) {
        return false;
      }
    }

    /* We ask for the number cell phone before and know if is anyone with that email */
    this.printPrompt("number cell phone");
    con.setNumberCellphone(user.next());
    if (isQuit(con.getNumberCellphone())) {
      return false;
    }

    while (!checkPhones(con.getNumberCellphone())) {
      this.printPrompt("number cell phone");
      con.setNumberCellphone(user.next());
      if (isQuit(con.getNumberCellphone())) {
        return false;
      }
    }

    /* We ask for the direction home before and know if is anyone with that email */
    this.printPrompt("direction");
    con.setDirection(user.next());
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
   * Print prompt
   * @param fieldName for print
   */
  private void printPrompt(String fieldName) {
    System.out.println(String.format(BASE_PROMPT_MSG, fieldName));
  }

  /**
   * isQuit
   * @param input input
   * @return when String indicates quit, returns true
   */
  boolean isQuit(String input) {
    return "quit:".equals(input);
  }

  /**
   * This method will show to the user all the contacts that he have
   */
  void listUsers() {
    for (Contact con : this.contacts) {
      System.out.printf("#%s <%s>", con.getId(), con.getFirstName());
    }
  }

  /**
   * Check email unique
   * @param email email
   * @return when it's valid returns true
   */
  boolean checkEmail(String email) {
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
   * @param phone phone
   * @return when it's valid returns true
   */
  boolean checkPhones(String phone) {
    try {
      Integer.parseInt(phone);
      return true;
    } catch (NumberFormatException e) {
      System.out.println("Only you can introduce numbers in the phone!!!");
      return false;
    }
  }

}
