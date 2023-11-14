package christmas.util;

import static christmas.constants.ErrorCode.INVALID_DATE;
import static christmas.constants.ErrorCode.INVALID_MENU_ORDER;

import java.util.List;

public class Parser {
    public static int parseToDate(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_DATE.getMessage());
        }
    }

    public static int parseToAmount(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_MENU_ORDER.getMessage());
        }
    }

    public static List<String> parseToList(String input, String delimiter) {
        return List.of(input.split(delimiter));
    }
}
