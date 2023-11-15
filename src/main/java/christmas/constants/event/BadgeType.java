package christmas.constants.event;

public enum BadgeType {
    NONE("없음", 0),
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private final String name;
    private final int threshold;

    BadgeType(String name, int threshold) {
        this.name = name;
        this.threshold = threshold;
    }

    public static BadgeType from(int benefitPrice) {
        BadgeType result = NONE;
        for (BadgeType badgeType : values()) {
            if (benefitPrice >= badgeType.threshold) {
                result = badgeType;
            }
        }
        return result;
    }
    
    public String getName() {
        return name;
    }
}
