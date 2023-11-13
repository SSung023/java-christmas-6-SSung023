package christmas.util;

import static christmas.constants.ErrorCode.NOT_INTEGER;

public class Parser {
    public static int parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_INTEGER.getMessage());
        }
    }
}
