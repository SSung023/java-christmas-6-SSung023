package christmas.controller;

import static christmas.constants.menu.MenuType.DESSERT;
import static christmas.constants.menu.MenuType.MAIN;

import christmas.constants.event.EventType;
import christmas.constants.menu.Menu;
import christmas.dto.SingleOrder;
import christmas.dto.UserOrder;
import christmas.model.DiscountResult;
import christmas.service.DiscountService;
import christmas.service.MenuService;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;
import java.util.Map;

public class EventController {
    private final DiscountService discountService;
    private final MenuService menuService;
    private final InputView inputView;
    private final OutputView outputView;
    private UserOrder userOrder; // TODO: 이후에 의존성 낮추기

    public EventController(DiscountService discountService, MenuService menuService, InputView inputView,
                           OutputView outputView) {
        this.discountService = discountService;
        this.menuService = menuService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        int visitDate = getVisitDate();
        getOrderMenu(visitDate);
        userOrder = new UserOrder(menuService.getOrderPrice(), visitDate,
                menuService.getAmountByMenu(MAIN),
                menuService.getAmountByMenu(DESSERT));

        showUserOrder();
        DiscountResult discount = discount(userOrder);
        printDiscountDetails(userOrder, discount);
    }

    private int getVisitDate() {
        outputView.printGreeting();
        outputView.printAskVisitDate();

        int visitDate = getInputDate();
        return visitDate;
    }

    private Map<Menu, Integer> getOrderMenu(int visitDate) {
        //TODO: (MenuService) 주문 메뉴 입력 + 예외 처리
        outputView.printAskMenu();
        getOrderMenu();
        outputView.printPreview(visitDate);

        return menuService.getMenuScript();
    }

    private int getInputDate() {
        while (true) {
            try {
                return inputView.askVisitDate();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private void getOrderMenu() {
        while (true) {
            try {
                List<SingleOrder> singleOrders = inputView.askOrderMenu();
                menuService.order(singleOrders);
                return;
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private void showUserOrder() {
        //TODO: 주문 메뉴 출력
        outputView.printOrdered(menuService.getMenuScript());

        //TODO: 할인 전 총주문 금액 출력
        outputView.printBeforeDiscountPrice(menuService.getOrderPrice());
    }

    private DiscountResult discount(UserOrder userOrder) {
        //TODO: (DiscountService) 증정 메뉴 계산 및 출력
        DiscountResult discountResult = discountService.calculateDiscountInfo(userOrder);

        //TODO: 혜택 내역 계산 및 출력
        outputView.printPresent(discountResult.getDiscountableByEvent(EventType.PRESENT));
        outputView.printDiscountDetails(discountResult);

        return discountResult;
    }

    private void printDiscountDetails(UserOrder userOrder, DiscountResult discountResult) {
        //TODO: 총 혜택 금액 출력
        outputView.printTotalDiscountPrice(discountResult);

        //TODO: 할인 후 예상 결제 금액 출력
        outputView.printExpectedPrice(userOrder.orderPrice(), discountResult);
    }

}
