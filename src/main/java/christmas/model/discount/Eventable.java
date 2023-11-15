package christmas.model.discount;

public interface Eventable<T> {
    boolean canJoinEvent(T condition);

    int getDiscountPrice();
}
