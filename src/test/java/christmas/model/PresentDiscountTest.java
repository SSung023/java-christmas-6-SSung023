package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PresentDiscountTest {

    @ParameterizedTest(name = "총 주문 금액: {0}")
    @DisplayName("총주문금액이 주어졌을 때, 120,000원 이상이라면 할인 금액은 25000원이다")
    @ValueSource(ints = {120_000, 120_100})
    public void should_discountPriceIs25000_when_orderPriceOver120000(int orderPrice) {
        //given
        PresentDiscount presentDiscount = PresentDiscount.create(orderPrice);

        //when
        int discountPrice = presentDiscount.getDiscountPrice();

        //then
        assertThat(discountPrice).isEqualTo(25_000);
    }

    @ParameterizedTest(name = "총 주문 금액: {0}")
    @DisplayName("총주문금액이 주어졌을 때, 120,000원 미만이라면 할인 금액은 0원이다")
    @ValueSource(ints = {119_999, 0})
    public void should_discountPriceIs0_when_orderPriceUnder120000(int orderPrice) {
        //given
        PresentDiscount presentDiscount = PresentDiscount.create(orderPrice);

        //when
        int discountPrice = presentDiscount.getDiscountPrice();

        //then
        assertThat(discountPrice).isEqualTo(0);
    }

}