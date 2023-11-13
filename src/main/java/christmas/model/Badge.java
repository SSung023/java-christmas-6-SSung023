package christmas.model;

import christmas.constants.event.BadgeType;

public class Badge {
    private final BadgeType badgeType;

    public Badge(int totalBenefitPrice) {
        this.badgeType = BadgeType.from(totalBenefitPrice);
    }

    public static Badge create(int totalBenefitPrice) {
        return new Badge(totalBenefitPrice);
    }
}
