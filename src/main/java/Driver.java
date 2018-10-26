import java.util.ArrayList;
import java.util.List;
import models.Contact;

/**
 * Driver for contact app
 */
public class Driver {

  public static void main(String[] args) {
    List<Contact> contacts = new ArrayList<>();
    contacts.add(new Contact(
        contacts.size(),
        "Derrik",
        "Park",
        "123456789",
        "123456789",
        "South",
        "derrick@gmail.com"));
    contacts.add(new Contact(
        contacts.size(),
        "Shota",
        "Shimizu",
        "123456789",
        "123456789",
        "North",
        "shimisho@gmail.com"));

    new Menu(contacts).execute();
  }
}