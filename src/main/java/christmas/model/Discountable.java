package christmas.model;

public interface Discountable<T> {
    boolean canDiscount(T condition);

    int getDiscountPrice();
}
