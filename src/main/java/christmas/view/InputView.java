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
        //TODO: ,를 delimiter 상수로
        List<String> menus = Parser.parseToMenu(getInput(), ",");

        //TODO: 코드가 맘에 안들어.. 안이뻐..
        return menus.stream()
                .map(singleOrder -> {
                    inputValidator.validateOrder(singleOrder);
                    return SingleOrder.create(singleOrder);
                })
                .toList();
    }

    private String getInput() {
        return Console.readLine().trim();
    }
}
