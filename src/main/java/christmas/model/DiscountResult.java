package christmas.model;

import christmas.constants.EventType;
import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

public class DiscountResult {
    private final Map<EventType, Discountable> discountResult;

    public DiscountResult() {
        discountResult = new EnumMap<>(EventType.class);
    }

    //TODO: add를 통해 계속 값을 추가하는것보다, 생성자를 통해 한 번에 받고 처리하는게 나을듯
    public void addResult(EventType eventType, Discountable discountable) {
        discountResult.put(eventType, discountable);
    }

    public int getDiscountPriceByEvent(EventType eventType) {
        int discountPrice = discountResult.get(eventType).getDiscountPrice();
        if (discountPrice == 0) {
            return 0;
        }
        return discountPrice;
    }

    public int getTotalDiscountPrice() {
        return discountResult.values()
                .stream()
                .mapToInt(Discountable::getDiscountPrice)
                .sum();
    }

    public boolean isEventNotApplied() {
        return (int) getDiscountApplied().count() == 0;
    }

    public Stream<Entry<EventType, Discountable>> getDiscountApplied() {
        return discountResult.entrySet()
                .stream()
                .filter(entry -> entry.getValue().getDiscountPrice() != 0);
    }
}
