package christmas.view;

import christmas.constants.event.BadgeType;
import christmas.constants.menu.Menu;
import christmas.dto.EventDetail;
import christmas.dto.UserOrder;
import christmas.service.EventService;
import christmas.view.output.ConsoleWriter;
import christmas.view.output.OutputView;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class OutputViewTest {
    private final OutputView outputView = new OutputView(new ConsoleWriter());
    private final EventService eventService = new EventService();

    @Test
    @DisplayName("증정 이벤트에 대한 출력 테스트 - 12만원 이상이면 샴페인 1개를 출력")
    public void presentDiscountTest() {
        outputView.printPresent(120_000);
    }

    @Test
    @DisplayName("증정 이벤트에 대한 출력 테스트 - 12만원 미만이면 없음을 출력")
    public void presentDiscountTestd() {
        outputView.printPresent(10000);
    }

    @Test
    @DisplayName("혜택 내역에 대한 출력 테스트")
    public void discountInfoPrintTest() {
        //given
        UserOrder userOrder = getUserOrder();

        //when
        eventService.applyEvent(userOrder);
        List<EventDetail> eventDetails = eventService.convertToEventDetails();

        //then
        outputView.printEventDetails(eventDetails);
    }

    @Test
    @DisplayName("총 혜택 내역에 대한 출력 테스트")
    public void totalDiscountPrintTest() {
        //given
        UserOrder userOrder = getUserOrder();

        //when
        eventService.applyEvent(userOrder);
        int totalBenefitPrice = eventService.getTotalBenefitPrice();

        //then
        outputView.printTotalBenefitPrice(totalBenefitPrice);
    }

    @Test
    @DisplayName("할인 후 예상 결제 금액 출력 테스트")
    public void expectedPricePrintTest() {
        //given
        UserOrder userOrder = getUserOrder();

        //when
        eventService.applyEvent(userOrder);
        int expectedPrice = eventService.getExpectedPrice(userOrder);

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

    private UserOrder getUserOrder() {
        return new UserOrder(142_000, 3, 2, 2);
    }
}