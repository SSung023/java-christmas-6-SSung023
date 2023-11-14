package christmas.constants.event;

public enum EventRule {
    EVENT_THRESHOLD(10_000),
    PRESENT_THRESHOLD(120_000),
    EVENT_START(1),
    EVENT_END(31),
    MAX_MENU_AMOUNT(20),
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
