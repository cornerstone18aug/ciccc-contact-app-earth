/**
 * Created by administrator on 2018-10-24.
 */
public class Menu {

    private int opt;

    public String option(int opt) {
        return "";
    }


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
