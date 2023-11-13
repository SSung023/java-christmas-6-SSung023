package christmas.model;

import static christmas.constants.event.EventRule.SPECIAL_DISCOUNT;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.discount.SpecialDiscount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SpecialDiscountTest {

    @ParameterizedTest(name = "사용자가 입력한 날짜: {0}")
    @DisplayName("입력한 날짜가 일요일 혹은 25일인 경우, 특별 할인이 적용된다.")
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    public void should_checkSpecialDay_when_passDate(int date) {
        //given
        SpecialDiscount specialDiscount = SpecialDiscount.create(date);

        //when
        int discountPrice = specialDiscount.getDiscountPrice();

        //then
        assertThat(discountPrice).isEqualTo(SPECIAL_DISCOUNT.getValue());
    }

    @ParameterizedTest(name = "사용자가 입력한 날짜: {0}")
    @DisplayName("입력한 날짜가 일요일 혹은 25일이 아닌 경우, 특별 할인이 적용되지 않는다.")
    @ValueSource(ints = {1, 2, 11, 12, 13, 14, 15, 16})
    public void should_checkNotSpecialDay_when_passDate(int date) {
        //given
        SpecialDiscount specialDiscount = SpecialDiscount.create(date);

        //when
        int discountPrice = specialDiscount.getDiscountPrice();

        //then
        assertThat(discountPrice).isEqualTo(0);
    }
}