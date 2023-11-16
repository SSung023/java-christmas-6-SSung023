package christmas.view.input;

import christmas.dto.SingleMenu;
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

    public List<SingleMenu> askOrderMenu() {
        List<String> menus = Parser.parseToMenu(getInput(), ",");

        return menus.stream()
                .map(singleOrder -> {
                    inputValidator.validateOrder(singleOrder);
                    return SingleMenu.create(singleOrder);
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
