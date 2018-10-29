package models;

/**
 * Contact
 */
public class Contact {

  private int id;
  private String firstName;
  private String lastName;
  private String numberHome;
  private String numberCellphone;
  private String direction;
  private String email;

  public Contact() {
  }

  public Contact(int id, String firstName, String lastName, String numberHome,
      String numberCellphone, String direction, String email) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.numberHome = numberHome;
    this.numberCellphone = numberCellphone;
    this.direction = direction;
    this.email = email;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getNumberHome() {
    return numberHome;
  }

  public void setNumberHome(String numberHome) {
    this.numberHome = numberHome;
  }

  public String getNumberCellphone() {
    return numberCellphone;
  }

  public void setNumberCellphone(String numberCellphone) {
    this.numberCellphone = numberCellphone;
  }

  public String getDirection() {
    return direction;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }

  /**
   * Check number phone
   *
   * @return when it's valid returns true
   */
  public boolean isValidNumberHome() {
    try {
      Integer.parseInt(this.numberHome);
      return true;
    } catch (NumberFormatException e) {
      System.out.println("Only you can introduce numbers!!!");
      return false;
    }
  }

  /**
   * Check number cell phone
   *
   * @return when it's valid returns true
   */
  public boolean isValidNumberCellphone() {
    try {
      Integer.parseInt(this.numberCellphone);
      return true;
    } catch (NumberFormatException e) {
      System.out.println("Only you can introduce numbers!!!");
      return false;
    }
  }
}
