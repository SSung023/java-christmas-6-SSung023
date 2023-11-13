package christmas;

import christmas.controller.EventController;
import christmas.service.DiscountService;
import christmas.service.MenuService;
import christmas.validator.InputValidator;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        EventController eventController = new EventController(
                new DiscountService(), new MenuService(),
                new InputView(new InputValidator()),
                new OutputView()
        );
        eventController.run();
    }
}
