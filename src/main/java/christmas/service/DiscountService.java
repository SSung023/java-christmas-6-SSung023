package christmas.service;

import static christmas.constants.EventRule.EVENT_THRESHOLD;
import static christmas.constants.EventType.CHRISTMAS;
import static christmas.constants.EventType.PRESENT;
import static christmas.constants.EventType.SPECIAL;
import static christmas.constants.EventType.WEEKDAY;
import static christmas.constants.EventType.WEEKEND;

import christmas.constants.EventType;
import christmas.dto.UserOrder;
import christmas.model.ChristmasDiscount;
import christmas.model.Discountable;
import christmas.model.PresentDiscount;
import christmas.model.SpecialDiscount;
import christmas.model.WeekdayDiscount;
import christmas.model.WeekendDiscount;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

public class DiscountService {
    private final Map<EventType, Discountable> discountInfos;

    public DiscountService() {
        this.discountInfos = new EnumMap<>(EventType.class);
    }

    public Optional<Map<EventType, Discountable>> calculateDiscountInfo(UserOrder userOrder) {
        if (!canDiscount(userOrder.orderPrice())) {
            // TODO: null이 전달되므로 다른 방식으로 처리하기 - Optional? || 도메인 내부에서 알아서 처리할 수 있도록..?
            return Optional.empty();
        }

        discountInfos.put(PRESENT, PresentDiscount.create(userOrder.orderPrice()));
        discountInfos.put(CHRISTMAS, ChristmasDiscount.create(userOrder.date()));
        discountInfos.put(WEEKDAY, WeekdayDiscount.create(userOrder));
        discountInfos.put(WEEKEND, WeekendDiscount.create(userOrder));
        discountInfos.put(SPECIAL, SpecialDiscount.create(userOrder.date()));

        return Optional.of(Collections.unmodifiableMap(discountInfos));
    }

    private boolean canDiscount(int orderPrice) {
        if (orderPrice < EVENT_THRESHOLD.getValue()) {
            return false;
        }
        return true;
    }

    public int getDiscountedPrice() {
        return discountInfos.values()
                .stream()
                .mapToInt(Discountable::getDiscountPrice)
                .sum();
    }
}
