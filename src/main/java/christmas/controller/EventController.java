package christmas.controller;

import static christmas.constants.event.EventType.PRESENT;
import static christmas.constants.menu.MenuType.DESSERT;
import static christmas.constants.menu.MenuType.MAIN;

import christmas.constants.event.BadgeType;
import christmas.dto.SingleMenu;
import christmas.dto.UserOrder;
import christmas.exception.RetryHandler;
import christmas.service.EventService;
import christmas.service.MenuService;
import christmas.view.input.InputView;
import christmas.view.output.OutputView;
import java.util.List;

public class EventController {
    private final EventService eventService;
    private final MenuService menuService;
    private final InputView inputView;
    private final OutputView outputView;
    private final RetryHandler retryHandler;

    public EventController(EventService eventService, MenuService menuService, InputView inputView,
                           OutputView outputView, RetryHandler retryHandler) {
        this.eventService = eventService;
        this.menuService = menuService;
        this.inputView = inputView;
        this.outputView = outputView;
        this.retryHandler = retryHandler;
    }

    public void run() {
        int visitDate = getVisitDate();
        UserOrder userOrder = receiveOrder(visitDate);
        printOrderInformation();

        applyEvent(userOrder);
        printEventDetails(userOrder);

        printBadge();
        inputView.close();
    }

    private int getVisitDate() {
        outputView.printGreeting();
        outputView.printAskVisitDate();

        return retryHandler.execute(inputView::askVisitDate, IllegalArgumentException.class);
    }

    private UserOrder receiveOrder(int visitDate) {
        outputView.printAskMenu();
        UserOrder userOrder = retryHandler.execute(() -> getUserOrder(visitDate), IllegalArgumentException.class);
        outputView.printPreview(visitDate);

        return userOrder;
    }

    private UserOrder getUserOrder(int visitDate) {
        List<SingleMenu> singleMenus = inputView.askOrderMenu();
        menuService.order(singleMenus);
        return new UserOrder(
                menuService.getOrderPrice(),
                visitDate,
                menuService.getAmountByMenu(MAIN),
                menuService.getAmountByMenu(DESSERT)
        );
    }

    private void printOrderInformation() {
        outputView.printOrderMenu(menuService.getMenuScript());
        outputView.printBeforeDiscountPrice(menuService.getOrderPrice());
    }

    private void applyEvent(UserOrder userOrder) {
        eventService.applyEvent(userOrder);
    }

    private void printEventDetails(UserOrder userOrder) {
        int discountPrice = eventService.getDiscountPriceByEvent(PRESENT);
        int expectedPrice = eventService.getExpectedPrice(userOrder);

        outputView.printPresent(discountPrice);
        outputView.printEventDetails(eventService.convertToEventDetails());
        outputView.printTotalBenefitPrice(eventService.getTotalBenefitPrice());
        outputView.printExpectedPrice(expectedPrice);
    }

    private void printBadge() {
        int totalDiscountPrice = eventService.getTotalBenefitPrice();
        outputView.printEventBadge(BadgeType.from(totalDiscountPrice));
    }
}
