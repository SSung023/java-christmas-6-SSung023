package christmas.constants;

public enum Day {
    SUNDAY(3),
    MONDAY(4),
    TUESDAY(5),
    WENDSDAY(6),
    THURSDAY(0),
    FRIDAY(1),
    SATURDAY(2);

    private final int index;

    Day(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
