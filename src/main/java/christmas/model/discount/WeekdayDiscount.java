package christmas.model.discount;

import static christmas.constants.EventRule.MENU_DISCOUNT;

import christmas.dto.UserOrder;
import christmas.util.DayAnalyzer;

public class WeekdayDiscount implements Discountable<UserOrder> {
    private final int discountAmount;

    private WeekdayDiscount(UserOrder condition) {
        if (canDiscount(condition)) {
            discountAmount = condition.dessertAmount();
            return;
        }
        discountAmount = 0;
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
        return discountAmount * MENU_DISCOUNT.getValue();
    }
}
