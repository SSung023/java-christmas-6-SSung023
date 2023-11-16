package christmas;

import christmas.config.AppConfig;
import christmas.controller.EventController;

public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = AppConfig.getInstance();
        EventController eventController = appConfig.eventController();
        eventController.run();
    }
}
