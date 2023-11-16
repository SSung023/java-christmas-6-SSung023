package christmas.config;

import christmas.controller.EventController;
import christmas.exception.RetryHandler;
import christmas.service.EventService;
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
    private EventService eventService;
    private MenuService menuService;
    private InputValidator inputValidator;
    private RetryHandler retryHandler;
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

    public EventService discountService() {
        if (eventService == null) {
            eventService = new EventService();
        }
        return eventService;
    }

    public MenuService menuService() {
        if (menuService == null) {
            menuService = new MenuService();
        }
        return menuService;
    }

    public RetryHandler exceptionHandler() {
        if (retryHandler == null) {
            retryHandler = new RetryHandler(outputView());
        }
        return retryHandler;
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
