package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.util.Parser;
import christmas.validator.InputValidator;

public class InputView {
    private final InputValidator inputValidator;

    public InputView(InputValidator inputValidator) {
        this.inputValidator = inputValidator;
    }

    public int askVisitDate() {
        String input = getInput();
        inputValidator.validateDate(input);
        return Parser.parseToInt(input);
    }

    private String getInput() {
        return Console.readLine().trim();
    }
}
