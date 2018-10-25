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
      "\n------------------------------\n" +
      "--------- Contact App --------\n" +
      "-- 1. Create a new contact ---\n" +
      "-- 2. List all the contacts --\n" +
      "---------- 3. Exit -----------\n" +
      "------------------------------";


  /**
   * Constructor
   * @param contacts
   */
  Menu(List<Contact> contacts) {
    this.contacts = contacts;
  }

  /* This is the method for the main menu */
  void menu() {
    while (!exit) {
      /* This while will have the switch with the different options that the user can select */
      System.out.println(this.MENU_STR);
      try {
        System.out.print("Select an option: ");
        opt = user.nextInt();
        switch (opt) {
          case 1:
            addUser();
            break;
          case 2:
            listUsers();
            break;
          case 3:
            exit = true;
            System.out.println("Goodbye see you soon!!!");
            break;
          default:
            System.out.println("Only numbers between 1 and 3!!!");
        }
      } catch (InputMismatchException e) {
        System.out.println("You have to introduce a number!!!");
      }
    }

  }

  /* This method will make all the process of add an user to the list */
  private void addUser() {
    Contact con = new Contact();
    System.out.println("Your are going to add a new contact!!!\n");

    /* We ask for the email before and know if is anyone with that email */
    System.out.print("Introduce the email of the contact: ");
    con.setEmail(user.next());

    while (!this.checkEmail(con.getEmail())) {
      System.out.print("Introduce the email of the contact: ");
      con.setEmail(user.next());
    }

    /* We ask for the first name before and know if is anyone with that email */
    System.out.print("Introduce the first name of the contact: ");
    con.setFirstName(user.next());

    /* We ask for the last name before and know if is anyone with that email */
    System.out.print("Introduce the last name of the contact: ");
    con.setLastName(user.next());

    /* We ask for the number home before and know if is anyone with that email */
    System.out.print("Introduce the number home of the contact: ");
    con.setNumberHome(user.next());

    while (!this.checkPhones(con.getNumberHome())) {
      System.out.print("Introduce the number home of the contact: ");
      con.setNumberHome(user.next());
    }

    /* We ask for the number cell phone before and know if is anyone with that email */
    System.out.print("Introduce the number cell phone home of the contact: ");
    con.setNumberCellphone(user.next());

    while (!checkPhones(con.getNumberCellphone())) {
      System.out.print("Introduce the number cell phone home of the contact: ");
      con.setNumberCellphone(user.next());
    }

    /* We ask for the direction home before and know if is anyone with that email */
    System.out.println("Introduce the direction home of the contact: ");
    con.setDirection(user.next());

    // We are incrementing the id of the contact
    con.setId(contacts.size());

    // We add the information of the contact
    contacts.add(con);
    System.out.printf("ID: %s, First Name : %s registered!", con.getId(), con.getFirstName());
  }

  /* This method will show to the user all the contacts that he have */
  private void listUsers() {
    for (Contact con : this.contacts) {
      System.out.printf("#%s <%s>", con.getId(), con.getFirstName());
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
   * @return  when it's valid returns true
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

}
