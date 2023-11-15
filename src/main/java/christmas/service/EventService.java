package christmas.service;

import static christmas.constants.event.EventRule.EVENT_THRESHOLD;
import static christmas.constants.event.EventType.CHRISTMAS;
import static christmas.constants.event.EventType.PRESENT;
import static christmas.constants.event.EventType.SPECIAL;
import static christmas.constants.event.EventType.WEEKDAY;
import static christmas.constants.event.EventType.WEEKEND;

import christmas.constants.event.EventType;
import christmas.dto.EventDetail;
import christmas.dto.UserOrder;
import christmas.model.ChristmasEvent;
import christmas.model.Eventable;
import christmas.model.PresentEvent;
import christmas.model.SpecialEvent;
import christmas.model.WeekdayEvent;
import christmas.model.WeekendEvent;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class EventService {
    private final Map<EventType, Eventable> eventResult;
    private final int INVALID_VALUE = 0;

    public EventService() {
        eventResult = new EnumMap<>(EventType.class);
    }

    public void applyEvent(UserOrder userOrder) {
        if (!canJoinEvent(userOrder.orderPrice())) {
            userOrder = new UserOrder(INVALID_VALUE, INVALID_VALUE, INVALID_VALUE, INVALID_VALUE);
        }

        eventResult.put(PRESENT, PresentEvent.create(userOrder.orderPrice()));
        eventResult.put(CHRISTMAS, ChristmasEvent.create(userOrder.date()));
        eventResult.put(WEEKDAY, WeekdayEvent.create(userOrder));
        eventResult.put(WEEKEND, WeekendEvent.create(userOrder));
        eventResult.put(SPECIAL, SpecialEvent.create(userOrder.date()));
    }

    private boolean canJoinEvent(int orderPrice) {
        if (orderPrice < EVENT_THRESHOLD.getValue()) {
            return false;
        }
        return true;
    }

    public int getDiscountPriceByEvent(EventType eventType) {
        return eventResult.get(eventType).getDiscountPrice();
    }

    public int getExpectedPrice(UserOrder userOrder) {
        return userOrder.orderPrice() - getTotalDiscountPrice();
    }

    public int getTotalDiscountPrice() {
        return eventResult.entrySet()
                .stream()
                .filter(entry -> entry.getKey() != PRESENT)
                .mapToInt(value -> value.getValue().getDiscountPrice())
                .sum();
    }

    public int getTotalBenefitPrice() {
        return eventResult.values()
                .stream()
                .mapToInt(Eventable::getDiscountPrice)
                .sum();
    }
    
    public List<EventDetail> convertToEventDetails() {
        return eventResult.entrySet()
                .stream()
                .filter(entry -> entry.getValue().getDiscountPrice() != 0)
                .map(entry -> new EventDetail(entry.getKey(), entry.getValue().getDiscountPrice()))
                .toList();
    }
}
