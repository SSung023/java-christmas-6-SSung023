package christmas.constants;

public enum EventRule {
    PRESENT_THRESHOLD(120_000);


    private final int value;

    EventRule(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
