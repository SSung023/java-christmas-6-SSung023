package christmas.model;

import static christmas.constants.EventRule.MENU_DISCOUNT;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.dto.UserOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WeekdayDiscountTest {
    @ParameterizedTest(name = "주문 날짜: {0}, 디저트 주문 개수: {1}")
    @DisplayName("평일에 주문했고, 디저트를 1개 이상 주문했다면 수량 * 2,023만큼 할인이 된다.")
    @CsvSource({
            "10, 1",
            "11, 2",
            "12, 1",
            "13, 1",
            "14, 1"
    })
    public void should_discount(int date, int amount) {
        //given
        UserOrder userOrder = new UserOrder(date, amount);
        WeekdayDiscount weekdayDiscount = WeekdayDiscount.create(userOrder);

        //when && then
        assertThat(weekdayDiscount.getDiscountPrice())
                .isEqualTo(MENU_DISCOUNT.getValue() * amount);
    }

    @ParameterizedTest(name = "주문 날짜: {0}, 디저트 주문 개수: {1}")
    @DisplayName("평일에 주문했으나 디저트를 주문하지 않았다면, 평일 할인 가격은 0원이다")
    @CsvSource({
            "10, 0",
            "11, 0",
            "12, 0",
            "13, 0",
            "14, 0"
    })
    public void should(int date, int amount) {
        //given
        UserOrder userOrder = new UserOrder(date, amount);
        WeekdayDiscount weekdayDiscount = WeekdayDiscount.create(userOrder);

        //when && then
        assertThat(weekdayDiscount.getDiscountPrice()).isEqualTo(0);
    }
}