package christmas.service;

import static christmas.constants.EventType.CHRISTMAS;
import static christmas.constants.EventType.PRESENT;
import static christmas.constants.EventType.SPECIAL;
import static christmas.constants.EventType.WEEKDAY;
import static christmas.constants.EventType.WEEKEND;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.constants.EventType;
import christmas.dto.UserOrder;
import christmas.model.Discountable;
import java.util.Map;
import java.util.Optional;
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
        Map<EventType, Discountable> discountInfo = discountService.calculateDiscountInfo(userOrder)
                .orElseThrow(IllegalArgumentException::new);

        //then
        assertThat(discountInfo.get(PRESENT).getDiscountPrice()).isEqualTo(25_000);
        assertThat(discountInfo.get(CHRISTMAS).getDiscountPrice()).isEqualTo(1_200);
        assertThat(discountInfo.get(WEEKDAY).getDiscountPrice()).isEqualTo(4_046);
        assertThat(discountInfo.get(WEEKEND).getDiscountPrice()).isEqualTo(0);
        assertThat(discountInfo.get(SPECIAL).getDiscountPrice()).isEqualTo(1_000);

    }

    @Test
    @DisplayName("주문 정보를 통해, 총 할인 가격 정보를 반환받을 수 있다.")
    public void should_returnTotalDiscountPrice_when_passOrderInfo() {
        //given
        UserOrder userOrder = new UserOrder(142_000, 3, 2, 2);

        //when
        discountService.calculateDiscountInfo(userOrder);
        int discountedPrice = discountService.getDiscountedPrice();

        //then
        assertThat(discountedPrice).isEqualTo(31_246);
    }

    @Test
    @DisplayName("주문 시, 가격이 10,000원 미만이라면 할인을 받을 수 없다.")
    public void should_notDiscount_when_priceUnder10000() {
        //given
        UserOrder userOrder = new UserOrder(10, 3, 2, 2);

        //when
        Optional<Map<EventType, Discountable>> optional = discountService.calculateDiscountInfo(
                userOrder);

        //then
        assertThat(optional).isEmpty();
    }
}