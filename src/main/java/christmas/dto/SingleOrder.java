package christmas.dto;

import christmas.util.Parser;
import java.util.List;

public record SingleOrder(String menu, int amount) {
    public static SingleOrder create(String singleOrder) {
        //TODO: delimiter를 상수로?
        List<String> parsed = Parser.parseToMenu(singleOrder, "-");
        return new SingleOrder(parsed.get(0), Parser.parseToAmount(parsed.get(1)));
    }
}
