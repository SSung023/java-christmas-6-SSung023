package christmas.controller;

import christmas.constants.menu.Menu;
import christmas.dto.SingleOrder;
import christmas.dto.UserOrder;
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
}
