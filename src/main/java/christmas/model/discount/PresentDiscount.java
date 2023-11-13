package christmas.model.discount;

import static christmas.constants.event.EventRule.PRESENT_THRESHOLD;
import static christmas.constants.menu.Menu.CHAMPAGNE;

public class PresentDiscount implements Discountable<Integer> {
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
    public boolean canDiscount(Integer orderPrice) {
        if (orderPrice < PRESENT_THRESHOLD.getValue()) {
            return false;
        }
        return true;
    }

    @Override
    public int getDiscountPrice() {
        return CHAMPAGNE.getPrice() * amount;
    }

    public int getAmount() {
        return amount;
    }
}
