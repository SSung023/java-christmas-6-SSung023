package christmas.model;

import static christmas.constants.event.EventRule.PRESENT_THRESHOLD;
import static christmas.constants.menu.Menu.CHAMPAGNE;

public class PresentEvent implements Eventable<Integer> {
    private final int amount;

    private PresentEvent(int orderPrice) {
        if (canJoinEvent(orderPrice)) {
            this.amount = 1;
            return;
        }
        this.amount = 0;
    }

    public static PresentEvent create(int orderPrice) {
        return new PresentEvent(orderPrice);
    }

    @Override
    public boolean canJoinEvent(Integer orderPrice) {
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
