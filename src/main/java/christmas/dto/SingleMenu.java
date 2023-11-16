package christmas.dto;

import christmas.util.Parser;
import java.util.List;

public record SingleMenu(String menu, int amount) {
    private static final String DELIMITER = "-";

    public static SingleMenu create(String singleOrder) {
        List<String> parsed = Parser.parseToMenu(singleOrder, DELIMITER);
        return new SingleMenu(parsed.get(0), Parser.parseToAmount(parsed.get(1)));
    }
}
