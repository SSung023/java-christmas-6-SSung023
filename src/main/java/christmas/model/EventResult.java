package christmas.model;

import static christmas.constants.event.EventType.PRESENT;

import christmas.constants.event.EventType;
import christmas.model.discount.Eventable;
import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

public class EventResult {
    private final Map<EventType, Eventable> discountResult;

    public EventResult() {
        discountResult = new EnumMap<>(EventType.class);
    }

    public void addResult(EventType eventType, Eventable eventable) {
        discountResult.put(eventType, eventable);
    }

    public int getDiscountPriceByEvent(EventType eventType) {
        return discountResult.get(eventType).getDiscountPrice();
    }

    public int getTotalDiscountPrice() {
        return discountResult.entrySet()
                .stream()
                .filter(entry -> entry.getKey() != PRESENT)
                .mapToInt(value -> value.getValue().getDiscountPrice())
                .sum();
    }

    public int getTotalBenefitPrice() {
        return discountResult.values()
                .stream()
                .mapToInt(Eventable::getDiscountPrice)
                .sum();
    }

    public boolean isEventNotApplied() {
        return (int) getDiscountApplied().count() == 0;
    }

    public Stream<Entry<EventType, Eventable>> getDiscountApplied() {
        return discountResult.entrySet()
                .stream()
                .filter(entry -> entry.getValue().getDiscountPrice() != 0);
    }
}
