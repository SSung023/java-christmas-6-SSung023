package christmas.service;

import static christmas.constants.event.EventRule.EVENT_THRESHOLD;
import static christmas.constants.event.EventType.CHRISTMAS;
import static christmas.constants.event.EventType.PRESENT;
import static christmas.constants.event.EventType.SPECIAL;
import static christmas.constants.event.EventType.WEEKDAY;
import static christmas.constants.event.EventType.WEEKEND;

import christmas.dto.UserOrder;
import christmas.model.EventResult;
import christmas.model.discount.ChristmasEvent;
import christmas.model.discount.PresentEvent;
import christmas.model.discount.SpecialEvent;
import christmas.model.discount.WeekdayEvent;
import christmas.model.discount.WeekendEvent;

public class DiscountService {

    public EventResult calculateEventResult(UserOrder userOrder) {
        EventResult eventResult = new EventResult();

        if (!canJoinEvent(userOrder.orderPrice())) {
            userOrder = new UserOrder(0, 0, 0, 0);
        }

        eventResult.addResult(PRESENT, PresentEvent.create(userOrder.orderPrice()));
        eventResult.addResult(CHRISTMAS, ChristmasEvent.create(userOrder.date()));
        eventResult.addResult(WEEKDAY, WeekdayEvent.create(userOrder));
        eventResult.addResult(WEEKEND, WeekendEvent.create(userOrder));
        eventResult.addResult(SPECIAL, SpecialEvent.create(userOrder.date()));
        return eventResult;
    }

    private boolean canJoinEvent(int orderPrice) {
        if (orderPrice < EVENT_THRESHOLD.getValue()) {
            return false;
        }
        return true;
    }

    public int getExpectedPrice(UserOrder userOrder, EventResult eventResult) {
        return userOrder.orderPrice() - eventResult.getTotalDiscountPrice();
    }
}
