package christmas.validator;

import static christmas.constants.ErrorCode.INVALID_DATE;
import static christmas.constants.ErrorCode.INVALID_MENU_ORDER;
import static christmas.constants.event.EventRule.EVENT_END;
import static christmas.constants.event.EventRule.EVENT_START;

import christmas.util.Parser;

public class InputValidator {
    public void validateDate(String input) {
        int parsed = Parser.parseToDate(input);
        if (parsed < EVENT_START.getValue() || EVENT_END.getValue() < parsed) {
            throw new IllegalArgumentException(INVALID_DATE.getMessage());
        }
    }

    public void validateOrder(String input) {
        //TODO: regex를 상수로?
        String pattern = "\\p{L}+-\\d+";
        if (!input.matches(pattern)) {
            throw new IllegalArgumentException(INVALID_MENU_ORDER.getMessage());
        }
    }
}
