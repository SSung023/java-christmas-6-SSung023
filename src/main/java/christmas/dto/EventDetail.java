package christmas.dto;

import christmas.constants.event.EventType;

public record EventDetail(EventType eventType, int discountPrice) {
}
