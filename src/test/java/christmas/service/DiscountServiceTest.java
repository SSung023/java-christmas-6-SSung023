package christmas.service;

import static christmas.constants.event.EventType.CHRISTMAS;
import static christmas.constants.event.EventType.PRESENT;
import static christmas.constants.event.EventType.SPECIAL;
import static christmas.constants.event.EventType.WEEKDAY;
import static christmas.constants.event.EventType.WEEKEND;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.dto.UserOrder;
import christmas.model.EventResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DiscountServiceTest {
    private final DiscountService discountService = new DiscountService();

    @Test
    @DisplayName("주문 정보를 전달하면, 그에 맞는 할인 정보를 반환한다.")
    public void should_returnDiscountInfo_when_passOrderInfo() {
        //given
        UserOrder userOrder = new UserOrder(142_000, 3, 2, 2);

        //when
        EventResult eventResult = discountService.calculateDiscountInfo(userOrder);

        //then
        assertThat(eventResult.getDiscountPriceByEvent(PRESENT)).isEqualTo(25_000);
        assertThat(eventResult.getDiscountPriceByEvent(CHRISTMAS)).isEqualTo(1_200);
        assertThat(eventResult.getDiscountPriceByEvent(WEEKDAY)).isEqualTo(4_046);
        assertThat(eventResult.getDiscountPriceByEvent(WEEKEND)).isEqualTo(0);
        assertThat(eventResult.getDiscountPriceByEvent(SPECIAL)).isEqualTo(1_000);

    }

    @Test
    @DisplayName("주문 정보를 통해, 총 할인 가격 정보를 반환받을 수 있다.")
    public void should_returnTotalDiscountPrice_when_passOrderInfo() {
        //given
        UserOrder userOrder = new UserOrder(142_000, 3, 2, 2);

        //when
        EventResult eventResult = discountService.calculateDiscountInfo(userOrder);
        int totalBenefitPrice = eventResult.getTotalBenefitPrice();

        //then
        assertThat(totalBenefitPrice).isEqualTo(31_246);
    }

    @Test
    @DisplayName("주문 시, 가격이 10,000원 미만이라면 할인을 받을 수 없다.")
    public void should_notDiscount_when_priceUnder10000() {
        //given
        UserOrder userOrder = new UserOrder(10, 3, 2, 2);

        //when
        EventResult eventResult = discountService.calculateDiscountInfo(userOrder);
        int discountedPrice = eventResult.getTotalBenefitPrice();

        //then
        assertThat(discountedPrice).isEqualTo(0);
    }
}