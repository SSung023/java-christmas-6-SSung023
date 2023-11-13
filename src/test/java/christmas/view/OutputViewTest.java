package christmas.view;

import christmas.model.PresentDiscount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OutputViewTest {
    private final OutputView outputView = new OutputView();

    @Test
    @DisplayName("증정 이벤트에 대한 출력 테스트 - 12만원 이상이면 샴페인 1개를 출력")
    public void presentDiscountTest() {
        PresentDiscount presentDiscount = PresentDiscount.create(120_000);
        outputView.printPresent(presentDiscount);
    }

    @Test
    @DisplayName("증정 이벤트에 대한 출력 테스트 - 12만원 미만이면 없음을 출력")
    public void presentDiscountTestd() {
        PresentDiscount presentDiscount = PresentDiscount.create(10000);
        outputView.printPresent(presentDiscount);
    }

}