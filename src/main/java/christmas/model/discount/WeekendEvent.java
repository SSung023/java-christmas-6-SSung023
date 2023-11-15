package christmas.model.discount;

import static christmas.constants.event.EventRule.MENU_DISCOUNT;

import christmas.dto.UserOrder;
import christmas.util.DayAnalyzer;

public class WeekendEvent implements Eventable<UserOrder> {
    private final int discountAmount;

    private WeekendEvent(UserOrder condition) {
        if (canJoinEvent(condition)) {
            this.discountAmount = condition.mainAmount();
            return;
        }
        this.discountAmount = 0;
    }

    public static WeekendEvent create(UserOrder condition) {
        return new WeekendEvent(condition);
    }

    @Override
    public boolean canJoinEvent(UserOrder condition) {
        return DayAnalyzer.isWeekend(condition.date());
    }

    @Override
    public int getDiscountPrice() {
        return discountAmount * MENU_DISCOUNT.getValue();
    }
}
