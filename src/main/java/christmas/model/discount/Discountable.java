package christmas.model.discount;

public interface Discountable<T> {
    boolean canDiscount(T condition);

    int getDiscountPrice();
}
