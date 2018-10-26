import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import models.Contact;
import org.junit.Before;
import org.junit.Test;

public class MenuTest {

  private static final List<Contact> contacts = new ArrayList<>();
  private static final Contact derickContact = new Contact(
      0,
      "Derrik",
      "Park",
      "dwrer3454re",
      "r5fdt5er",
      "rt5rtyg",
      "aaa@gmail.com");
  private Menu menu;

  @Before
  public void setUp() {
    contacts.add(derickContact);
    this.menu = new Menu(contacts);
  }

  @Test
  public void isQuit() {
    assertTrue(menu.isQuit("quit:"));
    assertFalse(menu.isQuit("quit"));
  }

  @Test
  public void checkEmail() {
    assertFalse(menu.checkEmail("aaa@gmail.com"));
    assertTrue(menu.checkEmail("bbb@gmail.com"));
  }

  @Test
  public void checkPhones() {
    assertTrue(menu.checkPhones("23457809"));
    assertFalse(menu.checkPhones("2-3457809"));
  }
}