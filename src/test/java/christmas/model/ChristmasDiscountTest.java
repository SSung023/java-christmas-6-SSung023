package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class ChristmasDiscountTest {
    @ParameterizedTest(name = "사용자가 선택한 날짜: {0}")
    @DisplayName("날짜를 전달했을 때, 1에서 25일 사이라면 크리스마스 디데이 할인 대상에 포함된다")
    @ValueSource(ints = {1, 15, 25})
    public void should_discount_when_dateIsValid(int date) {
        //given
        ChristmasEvent christmasEvent = ChristmasEvent.create(date);

        //when
        boolean canDiscount = christmasEvent.canJoinEvent(date);

        //then
        assertThat(canDiscount).isTrue();
    }

    @ParameterizedTest(name = "사용자가 선택한 날짜: {0}")
    @DisplayName("날짜를 전달했을 때, 1에서 25일 사이가 아니라면, 크리스마스 디데이 할인 대상에 포함되지 않는다")
    @ValueSource(ints = {26, 30, 31})
    public void should_discount_when_dateIsInvalid(int date) {
        //given
        ChristmasEvent christmasEvent = ChristmasEvent.create(date);

        //when
        boolean canDiscount = christmasEvent.canJoinEvent(date);

        //then
        assertThat(canDiscount).isFalse();
    }

    @ParameterizedTest(name = "사용자가 선택한 날짜: {0}, 추가 할인 가격: {1}")
    @DisplayName("1일에는 1000원, 이후에는 25일에 가까워질수록 100원씩 추가 할인한다.")
    @CsvSource({
            "1, 1000",
            "2, 1100",
            "25, 3400"
    })
    public void should_discount_when_(int date, int result) {
        //given
        ChristmasEvent christmasEvent = ChristmasEvent.create(date);

        //when
        int discountPrice = christmasEvent.getDiscountPrice();

        //then
        assertThat(discountPrice).isEqualTo(result);
    }
}