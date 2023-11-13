package christmas.model.discount;

import static christmas.constants.event.EventRule.CHRISTMAS_EVENT_END;
import static christmas.constants.event.EventRule.CHRISTMAS_EVENT_START;
import static christmas.constants.event.EventRule.CHRISTMAS_EXTRA_DISCOUNT;
import static christmas.constants.event.EventRule.CHRISTMAS_INIT_PRICE;

public class ChristmasDiscount implements Discountable<Integer> {
    private final int discountPrice;

    private ChristmasDiscount(int date) {
        if (canDiscount(date)) {
            this.discountPrice = calculateDiscountPrice(date);
            return;
        }
        this.discountPrice = 0;
    }

    public static ChristmasDiscount create(int date) {
        return new ChristmasDiscount(date);
    }

    private int calculateDiscountPrice(int date) {
        return CHRISTMAS_INIT_PRICE.getValue() + getAdditionalDiscount(date);
    }

    private int getAdditionalDiscount(int date) {
        return CHRISTMAS_EXTRA_DISCOUNT.getValue() * (date - CHRISTMAS_EVENT_START.getValue());
    }

    @Override
    public boolean canDiscount(Integer date) {
        if (date < CHRISTMAS_EVENT_START.getValue() || CHRISTMAS_EVENT_END.getValue() < date) {
            return false;
        }
        return true;
    }

    @Override
    public int getDiscountPrice() {
        return this.discountPrice;
    }
}
