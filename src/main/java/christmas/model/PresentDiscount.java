package christmas.model;

import static christmas.constants.EventRule.PRESENT_THRESHOLD;
import static christmas.constants.Menu.CHAMPAGNE;

public class PresentDiscount implements Discountable {
    private final int amount;

    private PresentDiscount(int orderPrice) {
        if (canDiscount(orderPrice)) {
            this.amount = 1;
            return;
        }
        this.amount = 0;
    }

    public static PresentDiscount create(int orderPrice) {
        return new PresentDiscount(orderPrice);
    }


    @Override
    public boolean canDiscount(int orderPrice) {
        if (orderPrice < PRESENT_THRESHOLD.getValue()) {
            return false;
        }
        return true;
    }

    @Override
    public int getDiscountPrice() {
        return CHAMPAGNE.getPrice() * amount;
    }
}
