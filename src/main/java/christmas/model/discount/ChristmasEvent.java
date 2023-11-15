package christmas.model.discount;

import static christmas.constants.event.EventRule.CHRISTMAS_EVENT_END;
import static christmas.constants.event.EventRule.CHRISTMAS_EXTRA_DISCOUNT;
import static christmas.constants.event.EventRule.CHRISTMAS_INIT_PRICE;
import static christmas.constants.event.EventRule.EVENT_START;

public class ChristmasEvent implements Eventable<Integer> {
    private final int discountPrice;

    private ChristmasEvent(int date) {
        if (canJoinEvent(date)) {
            this.discountPrice = calculateDiscountPrice(date);
            return;
        }
        this.discountPrice = 0;
    }

    public static ChristmasEvent create(int date) {
        return new ChristmasEvent(date);
    }

    private int calculateDiscountPrice(int date) {
        return CHRISTMAS_INIT_PRICE.getValue() + getAdditionalDiscount(date);
    }

    private int getAdditionalDiscount(int date) {
        return CHRISTMAS_EXTRA_DISCOUNT.getValue() * (date - EVENT_START.getValue());
    }

    @Override
    public boolean canJoinEvent(Integer date) {
        if (date < EVENT_START.getValue() || CHRISTMAS_EVENT_END.getValue() < date) {
            return false;
        }
        return true;
    }

    @Override
    public int getDiscountPrice() {
        return this.discountPrice;
    }
}
