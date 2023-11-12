package christmas.model;

import static christmas.constants.EventRule.MENU_DISCOUNT;

import christmas.dto.UserOrder;
import christmas.util.DayAnalyzer;

public class WeekdayDiscount implements Discountable<UserOrder> {
    private final int amount;

    private WeekdayDiscount(UserOrder condition) {
        if (canDiscount(condition)) {
            amount = condition.numberOfMenu();
            return;
        }
        amount = 0;
    }

    public static WeekdayDiscount create(UserOrder condition) {
        return new WeekdayDiscount(condition);
    }

    @Override
    public boolean canDiscount(UserOrder condition) {
        return DayAnalyzer.isWeekday(condition.date());
    }

    @Override
    public int getDiscountPrice() {
        return amount * MENU_DISCOUNT.getValue();
    }
}
