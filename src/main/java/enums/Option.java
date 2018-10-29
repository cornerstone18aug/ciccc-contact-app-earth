package enums;

/**
 * Option for command
 */
public enum Option {
  CREATE(1, "-- 1. Create a new contact ---"),
  LIST(2, "-- 2. List all the contacts --"),
  SHOW_DETAIL(3, "-- 3. Show detail from id ----"),
  HISTORY(4, "-- 4. Show past 3 commands ---"),
  FIND(5, "-- 5. Find a contact ---------"),
  EXIT(9, "---------- 9. Exit -----------");

  public final int optionNum;
  public final String menuMsg;

  Option(int optionNum, String menuMsg) {
    this.optionNum = optionNum;
    this.menuMsg = menuMsg;
  }

  /**
   * Find corresponding enums.Option from intVal
   *
   * @param optionInt input int value
   * @return corresponding enums.Option
   */
  public static Option find(int optionInt) {
    for (Option option : values()) {
      if (option.optionNum == optionInt) {
        return option;
      }
    }
    throw new IllegalArgumentException("optionInt is invalid value : " + optionInt);
  }
}