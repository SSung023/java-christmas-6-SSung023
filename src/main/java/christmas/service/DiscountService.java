package christmas.service;

import static christmas.constants.EventRule.EVENT_THRESHOLD;
import static christmas.constants.EventType.CHRISTMAS;
import static christmas.constants.EventType.PRESENT;
import static christmas.constants.EventType.SPECIAL;
import static christmas.constants.EventType.WEEKDAY;
import static christmas.constants.EventType.WEEKEND;

import christmas.dto.UserOrder;
import christmas.model.ChristmasDiscount;
import christmas.model.DiscountResult;
import christmas.model.PresentDiscount;
import christmas.model.SpecialDiscount;
import christmas.model.WeekdayDiscount;
import christmas.model.WeekendDiscount;

public class DiscountService {


    public DiscountResult calculateDiscountInfo(UserOrder userOrder) {
        DiscountResult discountResult = new DiscountResult();

        if (canDiscount(userOrder.orderPrice())) {
            discountResult.addResult(PRESENT, PresentDiscount.create(userOrder.orderPrice()));
            discountResult.addResult(CHRISTMAS, ChristmasDiscount.create(userOrder.date()));
            discountResult.addResult(WEEKDAY, WeekdayDiscount.create(userOrder));
            discountResult.addResult(WEEKEND, WeekendDiscount.create(userOrder));
            discountResult.addResult(SPECIAL, SpecialDiscount.create(userOrder.date()));
        }

        return discountResult;
    }

    private boolean canDiscount(int orderPrice) {
        if (orderPrice < EVENT_THRESHOLD.getValue()) {
            return false;
        }
        return true;
    }

    public int getDiscountedPrice(DiscountResult discountResult) {
        return discountResult.getTotalDiscountPrice();
    }
}
