package christmas.constants;

import java.util.Arrays;
import java.util.Comparator;

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
        return Arrays.stream(values())
                .filter(badgeType -> benefitPrice >= badgeType.threshold)
                .min(Comparator.comparingInt(BadgeType::getThreshold))
                .orElseThrow();
    }
    
    public String getName() {
        return name;
    }

    public int getThreshold() {
        return threshold;
    }
}
