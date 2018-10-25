import java.util.ArrayList;
import java.util.List;

/**
 * Driver for contact app
 */
public class Driver {

  public static void main(String[] args) {

    List<Contact> contacts = new ArrayList<>();
    contacts
        .add(new Contact(
            contacts.size(),
            "Derrik",
            "Park",
            "dwrer3454re",
            "r5fdt5er",
            "rt5rtyg",
            "aaa@gmail.com"));

    new Menu(contacts).execute();
  }
}