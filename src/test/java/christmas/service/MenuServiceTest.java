package christmas.service;

import static christmas.constants.ErrorCode.INVALID_MENU_ORDER;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.dto.SingleOrder;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuServiceTest {
    private final MenuService menuService = new MenuService();

    @Test
    @DisplayName("메뉴 주문 정보가 전달되었을 때, 메뉴판에 없는 메뉴를 입력한 경우 예외가 발생한다")
    public void should_throwException_when_orderMenuThatNotExist() {
        //given
        List<SingleOrder> singleOrders = List.of(
                new SingleOrder("리조또", 1)
        );

        //when && then
        assertThatThrownBy(() -> menuService.order(singleOrders))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_MENU_ORDER.getMessage());
    }

    @Test
    @DisplayName("메뉴 주문 정보가 전달되었을 때, 메뉴의 개수가 1 이상이 아니라면 예외가 발생한다")
    public void should_throwException_when_amountUnder1() {
        //given
        List<SingleOrder> singleOrders = List.of(
                new SingleOrder("시저샐러드", 1),
                new SingleOrder("티본스테이크", 0)
        );

        //when && then
        assertThatThrownBy(() -> menuService.order(singleOrders))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_MENU_ORDER.getMessage());
    }

    @Test
    @DisplayName("메뉴 주문 정보가 전달되었을 때, 메뉴 형식이 예시와 다른 경우 예외가 발생한다.")
    public void should_throwException_when_invalidFormat() {
        //given

        //when

        //then

    }

    @Test
    @DisplayName("메뉴 주문 정보가 전달되었을 때, 중복 메뉴를 입력한 경우 예외가 발생한다.")
    public void should_throwException_when_menuDuplicated() {
        //given
        List<SingleOrder> singleOrders = List.of(
                new SingleOrder("시저샐러드", 1),
                new SingleOrder("시저샐러드", 2)
        );

        //when && then
        assertThatThrownBy(() -> menuService.order(singleOrders))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_MENU_ORDER.getMessage());
    }

    @Test
    @DisplayName("메뉴 주문 정보가 전달되었을 때, 음료만 주문한 경우 예외가 발생한다.")
    public void should_throwException_when_orderOnlyDrinks() {
        //given
        List<SingleOrder> singleOrders = List.of(
                new SingleOrder("제로콜라", 1)
        );

        //when && then
        assertThatThrownBy(() -> menuService.order(singleOrders))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_MENU_ORDER.getMessage());
    }

    @Test
    @DisplayName("메뉴 주문 정보가 전달되었을 때, 메뉴의 개수가 20개를 초과하는 경우 예외가 발생한다.")
    public void should_throwException_when_amountOverflow() {
        //given
        List<SingleOrder> singleOrders = List.of(
                new SingleOrder("시저샐러드", 1),
                new SingleOrder("제로콜라", 20)
        );

        //when && then
        assertThatThrownBy(() -> menuService.order(singleOrders))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_MENU_ORDER.getMessage());
    }
}