package christmas.controller;

import christmas.service.DiscountService;
import christmas.service.MenuService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventController {
    private final DiscountService discountService;
    private final MenuService menuService;
    private final InputView inputView;
    private final OutputView outputView;

    public EventController(DiscountService discountService, MenuService menuService, InputView inputView,
                           OutputView outputView) {
        this.discountService = discountService;
        this.menuService = menuService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        getUserOrder();

    }

    private void getUserOrder() {
        outputView.printGreeting();
        outputView.printAskVisitDate();

        int visitDate = getVisitDate();

        //TODO: (MenuService) 주문 메뉴 입력 + 예외 처리

    }

    private int getVisitDate() {
        while (true) {
            try {
                return inputView.askVisitDate();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private void showUserOrder() {
        //TODO: 주문 메뉴 출력

        //TODO: 할인 전 총주문 금액 출력

    }

    private void discount() {
        //TODO: (DiscountService) 증정 메뉴 계산 및 출력

        //TODO: 혜택 내역 계산 및 출력
    }

}
