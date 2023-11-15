package christmas.model.discount;

import static christmas.constants.event.EventRule.SPECIAL_DISCOUNT;

import christmas.util.DayAnalyzer;

public class SpecialEvent implements Eventable<Integer> {
    private final int discountPrice;

    private SpecialEvent(Integer date) {
        if (canJoinEvent(date)) {
            discountPrice = SPECIAL_DISCOUNT.getValue();
            return;
        }
        discountPrice = 0;
    }

    public static SpecialEvent create(Integer date) {
        return new SpecialEvent(date);
    }

    @Override
    public boolean canJoinEvent(Integer date) {
        return DayAnalyzer.isSpecialDay(date);
    }

    @Override
    public int getDiscountPrice() {
        return this.discountPrice;
    }
}
