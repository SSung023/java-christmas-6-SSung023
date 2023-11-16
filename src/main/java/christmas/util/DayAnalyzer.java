package christmas.util;

import static christmas.constants.Day.FRIDAY;
import static christmas.constants.Day.SATURDAY;
import static christmas.constants.Day.SUNDAY;
import static christmas.constants.Day.THURSDAY;

import christmas.constants.Day;
import java.util.Arrays;

public class DayAnalyzer {
    private static final int DAY_OF_WEEK = 7;
    private static final int CHRISTMAS_DAY = 25;

    private static Day getDay(int date) {
        return Arrays.stream(Day.values())
                .filter(day -> day.getIndex() == date % DAY_OF_WEEK)
                .findFirst()
                .orElseThrow();
    }

    public static boolean isWeekday(int date) {
        Day targetDay = getDay(date);
        if (targetDay.compareTo(THURSDAY) <= 0 && targetDay.compareTo(SUNDAY) >= 0) {
            return true;
        }
        return false;
    }

    public static boolean isWeekend(int date) {
        Day targetDay = getDay(date);
        if (targetDay == FRIDAY || targetDay == SATURDAY) {
            return true;
        }
        return false;
    }

    public static boolean isSpecialDay(int date) {
        Day targetDay = getDay(date);
        if (targetDay == SUNDAY || date == CHRISTMAS_DAY) {
            return true;
        }
        return false;
    }
}
