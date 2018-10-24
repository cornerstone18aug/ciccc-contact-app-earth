import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    Scanner user = new Scanner(System.in);

    /* This variable will have the value FALSE and if the user select exit it will be true and finish the app */
    private boolean exit = false;

    /* This variable will have the option from the user */
    private int opt;

    /* This is the method for the main menu */
    public void menu() {
        while (!exit) {
            /* This while will have the switch with the different options that the user can select */
            System.out.println(toString());
            try {
                System.out.print("Select an option: ");
                opt = user.nextInt();
                switch (opt) {
                    case 1:
                        addUser();
                    case 2:
                        listUsers();
                    case 3:
                        exit = true;
                        System.out.println("Goodbye see you soon!!!");
                        break;
                    default:
                        System.out.println("Only numbers between 1 and 3!!!");
                }
            } catch (InputMismatchException e) {
                System.out.println("You have to introduce a number!!!");
                user.next();
            }
        }

    }

    /* This method will make all the process of add an user to the list */
    private void addUser() {
        System.out.println("AddUser");
    }

    /* This method will show to the user all the contacts that he have */
    private void listUsers() {
        System.out.println("ListUser");
    }


    /* This is the main menu for the app that the user will see */
    @Override
    public String toString() {
        return "------------------------------\n" +
                "--------- Contact App --------\n" +
                "-- 1. Create a new contact ---\n" +
                "-- 2. List all the contacts --\n" +
                "---------- 3. Exit -----------\n" +
                "------------------------------";
    }
}
