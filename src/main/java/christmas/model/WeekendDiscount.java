package christmas.model;

import static christmas.constants.EventRule.MENU_DISCOUNT;

import christmas.dto.UserOrder;
import christmas.util.DayAnalyzer;

public class WeekendDiscount implements Discountable<UserOrder> {
    private final int amount;

    private WeekendDiscount(UserOrder condition) {
        if (canDiscount(condition)) {
            this.amount = condition.numberOfMenu();
            return;
        }
        this.amount = 0;
    }

    public static WeekendDiscount create(UserOrder condition) {
        return new WeekendDiscount(condition);
    }

    @Override
    public boolean canDiscount(UserOrder condition) {
        return DayAnalyzer.isWeekend(condition.date());
    }

    @Override
    public int getDiscountPrice() {
        return amount * MENU_DISCOUNT.getValue();
    }
}
