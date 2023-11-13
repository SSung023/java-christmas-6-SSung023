package christmas.model;

import static christmas.constants.EventRule.SPECIAL_DISCOUNT;

import christmas.util.DayAnalyzer;

public class SpecialDiscount implements Discountable<Integer> {
    private final int discountPrice;

    private SpecialDiscount(Integer date) {
        if (canDiscount(date)) {
            discountPrice = SPECIAL_DISCOUNT.getValue();
            return;
        }
        discountPrice = 0;
    }

    public static SpecialDiscount create(Integer date) {
        return new SpecialDiscount(date);
    }

    @Override
    public boolean canDiscount(Integer date) {
        return DayAnalyzer.isSpecialDay(date);
    }

    @Override
    public int getDiscountPrice() {
        return this.discountPrice;
    }
}
