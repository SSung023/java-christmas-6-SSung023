package christmas.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DayAnalyzerTest {
    @ParameterizedTest(name = "사용자가 입력한 날짜: {0}")
    @DisplayName("날짜를 매개변수로 전달받았을 때, 이를 통해 평일임을 확인할 수 있다.")
    @ValueSource(ints = {10, 11, 12, 13, 14})
    public void should_checkWeekday_when_passDate(int date) {
        assertThat(DayAnalyzer.isWeekday(date)).isTrue();
    }

    @ParameterizedTest(name = "사용자가 입력한 날짜: {0}")
    @DisplayName("날짜를 매개변수로 전달받았을 때, 이를 통해 평일이 아님을 확인할 수 있다.")
    @ValueSource(ints = {15, 16})
    public void should_checkNotWeekday_when_passDate(int date) {
        assertThat(DayAnalyzer.isWeekday(date)).isFalse();
    }

    @ParameterizedTest(name = "사용자가 입력한 날짜: {0}")
    @DisplayName("날짜를 매개변수로 전달받았을 때, 이를 통해 주말임을 확인할 수 있다.")
    @ValueSource(ints = {15, 16})
    public void should_checkWeekend_when_passDate(int date) {
        assertThat(DayAnalyzer.isWeekend(date)).isTrue();
    }

    @ParameterizedTest(name = "사용자가 입력한 날짜: {0}")
    @DisplayName("날짜를 매개변수로 전달받았을 때, 이를 통해 주말이 아님 확인할 수 있다.")
    @ValueSource(ints = {10, 11, 12, 13, 14})
    public void should_checkNotWeekend_when_passDate(int date) {
        assertThat(DayAnalyzer.isWeekend(date)).isFalse();
    }

    @ParameterizedTest(name = "사용자가 입력한 날짜: {0}")
    @DisplayName("날짜를 매개변수로 전달받았을 때, 이를 통해 특별 할인 대상일인지 확인할 수 있다.")
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    public void should_checkSpecialDay_when_passDate(int date) {
        assertThat(DayAnalyzer.isSpecialDay(date)).isTrue();
    }

    @ParameterizedTest(name = "사용자가 입력한 날짜: {0}")
    @DisplayName("날짜를 매개변수로 전달받았을 때, 이를 통해 특별 할인 대상일이 아닌지 확인할 수 있다.")
    @ValueSource(ints = {1, 2, 11, 12, 13, 14, 15, 16})
    public void should_checkNotSpacialDay_when_passDate(int date) {
        assertThat(DayAnalyzer.isSpecialDay(date)).isFalse();
    }

}