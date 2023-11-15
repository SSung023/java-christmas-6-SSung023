package christmas.model;

public interface Eventable<T> {
    boolean canJoinEvent(T condition);

    int getDiscountPrice();
}
