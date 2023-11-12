package christmas.constants;

public enum EventRule {
    PRESENT_THRESHOLD(120_000),
    CHRISTMAS_EVENT_START(1),
    CHRISTMAS_EVENT_END(25),
    CHRISTMAS_INIT_PRICE(1_000),
    CHRISTMAS_EXTRA_DISCOUNT(100);


    private final int value;

    EventRule(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
