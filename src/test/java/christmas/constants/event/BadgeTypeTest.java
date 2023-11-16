package christmas.constants.event;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BadgeTypeTest {

    @ParameterizedTest
    @DisplayName("총 혜택 금액을 전달했을 때, 그에 맞는 이벤트 배지를 반환한다.")
    @CsvSource({
            "4999, NONE",
            "5000, STAR",
            "10000, TREE",
            "20000, SANTA"
    })
    public void should_returnBadge_when_passTotalDiscountPrice(int price, BadgeType badge) {
        BadgeType badgeType = BadgeType.from(price);
        Assertions.assertThat(badgeType).isEqualTo(badge);
    }

}