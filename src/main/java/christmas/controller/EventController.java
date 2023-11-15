package christmas.controller;

import static christmas.constants.menu.MenuType.DESSERT;
import static christmas.constants.menu.MenuType.MAIN;

import christmas.constants.event.BadgeType;
import christmas.constants.event.EventType;
import christmas.dto.SingleOrder;
import christmas.dto.UserOrder;
import christmas.exception.RetryHandler;
import christmas.model.DiscountResult;
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
        UserOrder userOrder = getOrderMenu(visitDate);
        printOrderMenu();

        DiscountResult discountResult = getDiscountResult(userOrder);
        printDiscountDetails(userOrder, discountResult);

        printBadge(discountResult);
        inputView.close();
    }

    private int getVisitDate() {
        outputView.printGreeting();
        outputView.printAskVisitDate();

        return retryHandler.execute(inputView::askVisitDate, IllegalArgumentException.class);
    }

    private UserOrder getOrderMenu(int visitDate) {
        outputView.printAskMenu();
        UserOrder userOrder = retryHandler.execute(() -> order(visitDate), IllegalArgumentException.class);
        outputView.printPreview(visitDate);

        return userOrder;
    }

    private UserOrder order(int visitDate) {
        List<SingleOrder> singleOrders = inputView.askOrderMenu();
        menuService.order(singleOrders);
        return new UserOrder(menuService.getOrderPrice(), visitDate,
                menuService.getAmountByMenu(MAIN),
                menuService.getAmountByMenu(DESSERT));
    }

    private void printOrderMenu() {
        // 주문 메뉴 출력
        outputView.printOrderMenu(menuService.getMenuScript());

        // 할인 전 총 주문 금액 출력
        outputView.printBeforeDiscountPrice(menuService.getOrderPrice());
    }

    private DiscountResult getDiscountResult(UserOrder userOrder) {
        // 증정 메뉴 출력
        DiscountResult discountResult = discountService.calculateDiscountInfo(userOrder);

        // 혜택 내역 출력
        outputView.printPresent(discountResult.getDiscountableByEvent(EventType.PRESENT));
        outputView.printDiscountDetails(discountResult);

        return discountResult;
    }

    private void printDiscountDetails(UserOrder userOrder, DiscountResult discountResult) {
        // 총 혜택 금액 출력
        outputView.printTotalDiscountPrice(discountResult.getTotalDiscountPrice());

        // 할인 후 예상 결제 금액 출력
        int expectedPrice = discountService.getExpectedPrice(userOrder, discountResult);
        outputView.printExpectedPrice(expectedPrice);
    }

    private void printBadge(DiscountResult discountResult) {
        // 12월 이벤트 배지 출력
        int totalDiscountPrice = discountResult.getTotalDiscountPrice();
//        int totalBenefitPrice = discountResult.getTotalBenefitPrice();
        outputView.printEventBadge(BadgeType.from(totalDiscountPrice));
    }
}
