package christmas.view;

import static christmas.constants.Menu.CHAMPAGNE;
import static christmas.constants.Message.MENU_FORMAT;
import static christmas.constants.Message.NONE;
import static christmas.constants.Message.PRESENT_HEADER;

import christmas.model.PresentDiscount;

public class OutputView {
    public void printPresent(PresentDiscount presentDiscount) {
        System.out.println(PRESENT_HEADER.getMessage());
        if (presentDiscount.getAmount() == 0) {
            System.out.println(NONE.getMessage());
            return;
        }
        System.out.printf(MENU_FORMAT.getMessage(), CHAMPAGNE.getName(), presentDiscount.getAmount());
    }
}
