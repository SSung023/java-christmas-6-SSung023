package christmas.config;

import christmas.controller.EventController;
import christmas.service.DiscountService;
import christmas.service.MenuService;
import christmas.validator.InputValidator;
import christmas.view.input.ConsoleReader;
import christmas.view.input.InputView;
import christmas.view.input.Reader;
import christmas.view.output.ConsoleWriter;
import christmas.view.output.OutputView;
import christmas.view.output.Writer;

public class AppConfig {
    private static AppConfig appConfig;
    private EventController eventController;
    private DiscountService discountService;
    private MenuService menuService;
    private InputValidator inputValidator;
    private InputView inputView;
    private OutputView outputView;
    private Reader reader;
    private Writer writer;

    public static AppConfig getInstance() {
        if (appConfig == null) {
            return new AppConfig();
        }
        return appConfig;
    }

    public EventController eventController() {
        if (eventController == null) {
            return new EventController(discountService(), menuService(), inputView(), outputView());
        }
        return eventController;
    }

    public DiscountService discountService() {
        if (discountService == null) {
            return new DiscountService();
        }
        return discountService;
    }

    public MenuService menuService() {
        if (menuService == null) {
            return new MenuService();
        }
        return menuService;
    }

    public InputView inputView() {
        if (inputView == null) {
            return new InputView(reader(), inputValidator());
        }
        return inputView;
    }

    public InputValidator inputValidator() {
        if (inputValidator == null) {
            return new InputValidator();
        }
        return inputValidator;
    }

    public Reader reader() {
        if (reader == null) {
            return new ConsoleReader();
        }
        return reader;
    }

    public OutputView outputView() {
        if (outputView == null) {
            return new OutputView(writer());
        }
        return outputView;
    }

    public Writer writer() {
        if (writer == null) {
            return new ConsoleWriter();
        }
        return writer;
    }
}
