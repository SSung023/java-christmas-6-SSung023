package christmas.model.discount;

import static christmas.constants.event.EventRule.MENU_DISCOUNT;

import christmas.dto.UserOrder;
import christmas.util.DayAnalyzer;

public class WeekdayEvent implements Eventable<UserOrder> {
    private final int discountAmount;

    private WeekdayEvent(UserOrder condition) {
        if (canJoinEvent(condition)) {
            discountAmount = condition.dessertAmount();
            return;
        }
        discountAmount = 0;
    }

    public static WeekdayEvent create(UserOrder condition) {
        return new WeekdayEvent(condition);
    }

    @Override
    public boolean canJoinEvent(UserOrder condition) {
        return DayAnalyzer.isWeekday(condition.date());
    }

    @Override
    public int getDiscountPrice() {
        return discountAmount * MENU_DISCOUNT.getValue();
    }
}
