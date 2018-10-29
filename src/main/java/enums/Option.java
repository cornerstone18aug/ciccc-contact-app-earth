package enums;

/**
 * Option for command
 */
public enum Option {
  CREATE(1, "-- %s. Create a new contact ---"),
  LIST(2, "-- %s. List all the contacts --"),
  SHOW_DETAIL(3, "-- %s. Show detail from id ----"),
  CMD_HISTORY(4, "-- %s. Show past 3 commands ---"),
  INPUT_HISTORY(5, "-- %s. Show past 3 inputs -----"),
  FIND(6, "-- %s. Find from name or mail -"),
  EXIT(9, "---------- %s. Exit -----------");

  public final int optionNum;
  public final String menuMsg;

  Option(int optionNum, String menuMsg) {
    this.optionNum = optionNum;
    this.menuMsg = String.format(menuMsg, optionNum);
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