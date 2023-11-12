package christmas.model;

public interface Discountable {
    boolean canDiscount(int condition);

    int getDiscountPrice();
}
