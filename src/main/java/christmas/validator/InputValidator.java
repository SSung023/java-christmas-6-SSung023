package christmas.validator;

import static christmas.constants.event.EventRule.EVENT_END;
import static christmas.constants.event.EventRule.EVENT_START;
import static christmas.exception.ErrorCode.INVALID_DATE;
import static christmas.exception.ErrorCode.INVALID_MENU_ORDER;

import christmas.util.Parser;

public class InputValidator {
    private final String ORDER_REGEX = "\\p{L}+-\\d+";

    public void validateDate(String input) {
        int parsed = Parser.parseToDate(input);
        if (parsed < EVENT_START.getValue() || EVENT_END.getValue() < parsed) {
            throw new IllegalArgumentException(INVALID_DATE.getMessage());
        }
    }

    public void validateOrder(String input) {
        if (!input.matches(ORDER_REGEX)) {
            throw new IllegalArgumentException(INVALID_MENU_ORDER.getMessage());
        }
    }
}
