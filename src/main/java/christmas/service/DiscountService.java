package christmas.service;

import static christmas.constants.event.EventRule.EVENT_THRESHOLD;
import static christmas.constants.event.EventType.CHRISTMAS;
import static christmas.constants.event.EventType.PRESENT;
import static christmas.constants.event.EventType.SPECIAL;
import static christmas.constants.event.EventType.WEEKDAY;
import static christmas.constants.event.EventType.WEEKEND;

import christmas.dto.UserOrder;
import christmas.model.DiscountResult;
import christmas.model.discount.ChristmasDiscount;
import christmas.model.discount.PresentDiscount;
import christmas.model.discount.SpecialDiscount;
import christmas.model.discount.WeekdayDiscount;
import christmas.model.discount.WeekendDiscount;

public class DiscountService {


    public DiscountResult calculateDiscountInfo(UserOrder userOrder) {
        DiscountResult discountResult = new DiscountResult();

        if (!canDiscount(userOrder.orderPrice())) {
            userOrder = new UserOrder(0, 0, 0, 0);
        }

        discountResult.addResult(PRESENT, PresentDiscount.create(userOrder.orderPrice()));
        discountResult.addResult(CHRISTMAS, ChristmasDiscount.create(userOrder.date()));
        discountResult.addResult(WEEKDAY, WeekdayDiscount.create(userOrder));
        discountResult.addResult(WEEKEND, WeekendDiscount.create(userOrder));
        discountResult.addResult(SPECIAL, SpecialDiscount.create(userOrder.date()));
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

    public int getExpectedPrice(UserOrder userOrder, DiscountResult discountResult) {
        return userOrder.orderPrice() - discountResult.getTotalBenefitPrice();
    }
}
