package christmas.controller;

import static christmas.constants.event.EventType.PRESENT;
import static christmas.constants.menu.MenuType.DESSERT;
import static christmas.constants.menu.MenuType.MAIN;

import christmas.constants.event.BadgeType;
import christmas.dto.SingleOrder;
import christmas.dto.UserOrder;
import christmas.exception.RetryHandler;
import christmas.model.EventResult;
import christmas.service.DiscountService;
import christmas.service.MenuService;
import christmas.view.input.InputView;
import christmas.view.output.OutputView;
import java.util.List;

public class EventController {
    private final DiscountService discountService;
    private final MenuService menuService;
    private final InputView inputView;
    private final OutputView outputView;
    private final RetryHandler retryHandler;

    public EventController(DiscountService discountService, MenuService menuService, InputView inputView,
                           OutputView outputView, RetryHandler retryHandler) {
        this.discountService = discountService;
        this.menuService = menuService;
        this.inputView = inputView;
        this.outputView = outputView;
        this.retryHandler = retryHandler;
    }

    public void run() {
        int visitDate = getVisitDate();
        UserOrder userOrder = getMenuOrder(visitDate);
        printOrderInformation();

        EventResult eventResult = getEventResult(userOrder);
        printEventDetails(userOrder, eventResult);

        printBadge(eventResult);
        inputView.close();
    }

    private int getVisitDate() {
        outputView.printGreeting();
        outputView.printAskVisitDate();

        return retryHandler.execute(inputView::askVisitDate, IllegalArgumentException.class);
    }

    private UserOrder getMenuOrder(int visitDate) {
        outputView.printAskMenu();
        UserOrder userOrder = retryHandler.execute(() -> getUserOrder(visitDate), IllegalArgumentException.class);
        outputView.printPreview(visitDate);

        return userOrder;
    }

    private UserOrder getUserOrder(int visitDate) {
        List<SingleOrder> singleOrders = inputView.askOrderMenu();
        menuService.order(singleOrders);
        return new UserOrder(menuService.getOrderPrice(), visitDate,
                menuService.getAmountByMenu(MAIN),
                menuService.getAmountByMenu(DESSERT));
    }

    private void printOrderInformation() {
        outputView.printOrderMenu(menuService.getMenuScript());
        outputView.printBeforeDiscountPrice(menuService.getOrderPrice());
    }

    private EventResult getEventResult(UserOrder userOrder) {
        return discountService.calculateEventResult(userOrder);
    }

    private void printEventDetails(UserOrder userOrder, EventResult eventResult) {
        int discountPrice = eventResult.getDiscountPriceByEvent(PRESENT);
        int expectedPrice = discountService.getExpectedPrice(userOrder, eventResult);

        outputView.printPresent(discountPrice);
        outputView.printEventDetails(eventResult);
        outputView.printTotalBenefitPrice(eventResult.getTotalBenefitPrice());
        outputView.printExpectedPrice(expectedPrice);
    }

    private void printBadge(EventResult eventResult) {
        int totalDiscountPrice = eventResult.getTotalBenefitPrice();
        outputView.printEventBadge(BadgeType.from(totalDiscountPrice));
    }
}
