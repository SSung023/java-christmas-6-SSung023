package christmas.model;

import static christmas.constants.EventRule.PRESENT_THRESHOLD;
import static christmas.constants.Menu.CHAMPAGNE;

public class Present implements DiscountResult {
    private final int amount;

    private Present(int orderPrice) {
        if (orderPrice >= PRESENT_THRESHOLD.getValue()) {
            this.amount = 1;
            return;
        }
        this.amount = 0;
    }

    public static Present create(int orderPrice) {
        return new Present(orderPrice);
    }


    @Override
    public int getDiscountPrice() {
        return CHAMPAGNE.getPrice() * amount;
    }
}
