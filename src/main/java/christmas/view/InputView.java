package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.dto.SingleOrder;
import christmas.util.Parser;
import christmas.validator.InputValidator;
import java.util.List;

public class InputView {
    private final InputValidator inputValidator;

    public InputView(InputValidator inputValidator) {
        this.inputValidator = inputValidator;
    }

    public int askVisitDate() {
        String input = getInput();
        inputValidator.validateDate(input);
        return Parser.parseToDate(input);
    }

    public List<SingleOrder> askOrderMenu() {
        List<String> menus = Parser.parseToList(getInput(), ",");

        return menus.stream()
                .map(singleOrder -> {
                    List<String> list = Parser.parseToList(singleOrder, "-");
                    return new SingleOrder(list.get(0), Parser.parseToAmount(list.get(1)));
                })
                .toList();
    }

    private String getInput() {
        return Console.readLine().trim();
    }
}
