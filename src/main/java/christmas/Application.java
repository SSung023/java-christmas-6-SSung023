package christmas;

import christmas.controller.EventController;
import christmas.service.DiscountService;
import christmas.service.MenuService;
import christmas.validator.InputValidator;
import christmas.view.input.ConsoleReader;
import christmas.view.input.InputView;
import christmas.view.output.ConsoleWriter;
import christmas.view.output.OutputView;

public class Application {
    public static void main(String[] args) {
        EventController eventController = new EventController(
                new DiscountService(), new MenuService(),
                new InputView(new ConsoleReader(), new InputValidator()),
                new OutputView(new ConsoleWriter())
        );
        eventController.run();
    }
}
