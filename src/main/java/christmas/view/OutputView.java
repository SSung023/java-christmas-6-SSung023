package christmas.view;

import static christmas.constants.Menu.CHAMPAGNE;
import static christmas.constants.Message.DISCOUNT_HEADER;
import static christmas.constants.Message.MENU_FORMAT;
import static christmas.constants.Message.NONE;
import static christmas.constants.Message.PRESENT_HEADER;

import christmas.model.DiscountResult;
import christmas.model.PresentDiscount;
import java.text.DecimalFormat;

public class OutputView {
    private final DecimalFormat decimalFormat;

    public OutputView() {
        decimalFormat = new DecimalFormat("#,###");
    }

    public void printPresent(PresentDiscount presentDiscount) {
        System.out.println(PRESENT_HEADER.getMessage());
        if (presentDiscount.getAmount() == 0) {
            System.out.println(NONE.getMessage());
            return;
        }
        System.out.printf(MENU_FORMAT.getMessage(), CHAMPAGNE.getName(), presentDiscount.getAmount());
    }

    //TODO: DiscountResult에서 entry를 받아오는게 아쉽다...
    public void printDiscountDetails(DiscountResult discountResult) {
        StringBuilder stringBuilder = new StringBuilder(DISCOUNT_HEADER.getMessage());

        if (discountResult.isEventNotApplied()) {
            System.out.println(NONE.getMessage());
            return;
        }

        discountResult.getDiscountApplied()
                .forEach(entry -> {
                    int priceByEvent = discountResult.getDiscountPriceByEvent(entry.getKey());
                    stringBuilder.append(
                            String.format("%s: -%s원%n", entry.getKey().getDescription(),
                                    decimalFormat.format(priceByEvent))
                    );
                });
        System.out.println(stringBuilder);
    }
}