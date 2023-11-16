package christmas.model;

import static christmas.constants.event.EventRule.MENU_DISCOUNT;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.dto.UserOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WeekendEventTest {

    @ParameterizedTest(name = "주문 날짜: {0}, 메인 주문 개수: {1}")
    @DisplayName("주말에 주문했고, 메인을 1개 이상 주문했다면 수량 * 2,023만큼 할인이 된다.")
    @CsvSource({
            "15, 1",
            "16, 2",
            "22, 1",
            "23, 1",
    })
    public void should_discountPerMain_when_orderValid(int date, int mainAmount) {
        //given
        UserOrder userOrder = new UserOrder(0, date, mainAmount, 0);
        WeekendEvent weekendEvent = WeekendEvent.create(userOrder);

        //when && then
        assertThat(weekendEvent.getDiscountPrice())
                .isEqualTo(MENU_DISCOUNT.getValue() * mainAmount);
    }

}