package christmas.view;

import christmas.constants.event.BadgeType;
import christmas.constants.menu.Menu;
import christmas.dto.UserOrder;
import christmas.model.DiscountResult;
import christmas.model.discount.PresentDiscount;
import christmas.service.DiscountService;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class OutputViewTest {
    private final OutputView outputView = new OutputView();
    private final DiscountService discountService = new DiscountService();

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

    @Test
    @DisplayName("혜택 내역에 대한 출력 테스트")
    public void discountInfoPrintTest() {
        //given
        UserOrder userOrder = new UserOrder(142_000, 3, 2, 2);

        //when
        DiscountResult discountResult = discountService.calculateDiscountInfo(userOrder);

        //then
        outputView.printDiscountDetails(discountResult);
    }

    @Test
    @DisplayName("총 혜택 내역에 대한 출력 테스트")
    public void totalDiscountPrintTest() {
        //given
        UserOrder userOrder = new UserOrder(142_000, 3, 2, 2);

        //when
        DiscountResult discountResult = discountService.calculateDiscountInfo(userOrder);

        //then
        outputView.printTotalDiscountPrice(discountResult);
    }

    @Test
    @DisplayName("할인 후 예상 결제 금액 출력 테스트")
    public void expectedPricePrintTest() {
        //given
        UserOrder userOrder = new UserOrder(142_000, 3, 2, 2);

        //when
        DiscountResult discountResult = discountService.calculateDiscountInfo(userOrder);
        int expectedPrice = discountService.getExpectedPrice(userOrder, discountResult);

        //then
        outputView.printExpectedPrice(expectedPrice);
    }

    @ParameterizedTest
    @DisplayName("이벤트 베지 출력 테스트")
    @EnumSource(value = BadgeType.class)
    public void eventBadgePrintTest(BadgeType badgeType) {
        outputView.printEventBadge(badgeType);
    }

    @Test
    @DisplayName("주문 내역 출력 테스트")
    public void orderedMenuPrintTest() {
        //given
        Map<Menu, Integer> menuScript = Map.of(
                Menu.TAPAS, 1, Menu.ZERO_COLA, 1
        );

        //when && then
        outputView.printOrderMenu(menuScript);
    }

    @Test
    @DisplayName("할인 전 총주문 금액 테스트")
    public void printBeforeDiscountTest() {
        //given
        int orderPrice = 8500;

        //when && then
        outputView.printBeforeDiscountPrice(orderPrice);

    }
}