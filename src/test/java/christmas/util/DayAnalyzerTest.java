package christmas.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DayAnalyzerTest {
    @ParameterizedTest
    @DisplayName("날짜를 매개변수로 전달받았을 때, 이를 통해 평일임을 확인할 수 있다.")
    @ValueSource(ints = {10, 11, 12, 13, 14})
    public void should_(int date) {
        assertThat(DayAnalyzer.isWeekday(date)).isTrue();
    }
}