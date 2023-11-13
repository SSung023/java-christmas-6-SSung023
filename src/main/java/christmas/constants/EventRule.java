package christmas.constants;

public enum EventRule {
    EVENT_THRESHOLD(10_000),
    PRESENT_THRESHOLD(120_000),
    CHRISTMAS_EVENT_START(1),
    CHRISTMAS_EVENT_END(25),
    CHRISTMAS_INIT_PRICE(1_000),
    CHRISTMAS_EXTRA_DISCOUNT(100),
    MENU_DISCOUNT(2_023),
    SPECIAL_DISCOUNT(1_000);


    private final int value;

    EventRule(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
