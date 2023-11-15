package christmas.config;

import christmas.controller.EventController;
import christmas.exception.ExceptionHandler;
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
    private ExceptionHandler exceptionHandler;
    private InputView inputView;
    private OutputView outputView;
    private Reader reader;
    private Writer writer;

    public static AppConfig getInstance() {
        if (appConfig == null) {
            appConfig = new AppConfig();
        }
        return appConfig;
    }

    public EventController eventController() {
        if (eventController == null) {
            eventController = new EventController(discountService(), menuService(),
                    inputView(), outputView(), exceptionHandler());
        }
        return eventController;
    }

    public DiscountService discountService() {
        if (discountService == null) {
            discountService = new DiscountService();
        }
        return discountService;
    }

    public MenuService menuService() {
        if (menuService == null) {
            menuService = new MenuService();
        }
        return menuService;
    }

    public ExceptionHandler exceptionHandler() {
        if (exceptionHandler == null) {
            exceptionHandler = new ExceptionHandler();
        }
        return exceptionHandler;
    }

    public InputView inputView() {
        if (inputView == null) {
            inputView = new InputView(reader(), inputValidator());
        }
        return inputView;
    }

    public InputValidator inputValidator() {
        if (inputValidator == null) {
            inputValidator = new InputValidator();
        }
        return inputValidator;
    }

    public Reader reader() {
        if (reader == null) {
            reader = new ConsoleReader();
        }
        return reader;
    }

    public OutputView outputView() {
        if (outputView == null) {
            outputView = new OutputView(writer());
        }
        return outputView;
    }

    public Writer writer() {
        if (writer == null) {
            writer = new ConsoleWriter();
        }
        return writer;
    }
}
