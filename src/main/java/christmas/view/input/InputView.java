package christmas.view.input;

import christmas.dto.SingleOrder;
import christmas.util.Parser;
import christmas.validator.InputValidator;
import java.util.List;

public class InputView {
    private final Reader reader;
    private final InputValidator inputValidator;

    public InputView(Reader reader, InputValidator inputValidator) {
        this.reader = reader;
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

    public void close() {
        reader.close();
    }

    private String getInput() {
        return reader.readLine();
    }
}
