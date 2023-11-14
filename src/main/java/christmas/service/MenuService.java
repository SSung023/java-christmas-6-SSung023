package christmas.service;

import static christmas.constants.ErrorCode.INVALID_MENU_ORDER;
import static christmas.constants.ErrorCode.MENU_OVERFLOW;
import static christmas.constants.event.EventRule.MAX_MENU_AMOUNT;

import christmas.constants.menu.Menu;
import christmas.constants.menu.MenuType;
import christmas.dto.SingleOrder;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class MenuService {
    private final Map<Menu, Integer> menuScript;

    public MenuService() {
        this.menuScript = new EnumMap<>(Menu.class);
    }

    public void order(List<SingleOrder> singleOrders) {
        validate(singleOrders);
        singleOrders.forEach(singleOrder -> {
            Menu menu = Menu.from(singleOrder.menu());
            menuScript.put(menu, singleOrder.amount());
        });
    }

    public int getOrderPrice() {
        return menuScript.keySet()
                .stream()
                .mapToInt(menu -> menu.getPrice() * menuScript.get(menu))
                .sum();
    }

    private void validate(List<SingleOrder> singleOrders) {
        validateDuplicate(singleOrders);
        validateAmount(singleOrders);
        validateOnlyDrink(singleOrders);
        validateMenuAmount(singleOrders);
    }

    private void validateAmount(List<SingleOrder> singleOrders) {
        boolean present = singleOrders.stream()
                .anyMatch(singleOrder -> singleOrder.amount() < 1);
        if (present) {
            throw new IllegalArgumentException(INVALID_MENU_ORDER.getMessage());
        }
    }

    private void validateDuplicate(List<SingleOrder> singleOrders) {
        int count = (int) singleOrders.stream()
                .map(singleOrder -> Menu.from(singleOrder.menu()))
                .distinct()
                .count();

        if (count != singleOrders.size()) {
            throw new IllegalArgumentException(INVALID_MENU_ORDER.getMessage());
        }
    }

    private void validateOnlyDrink(List<SingleOrder> singleOrders) {
        List<Menu> menus = singleOrders.stream()
                .map(singleOrder -> Menu.from(singleOrder.menu()))
                .toList();

        int drinks = (int) menus.stream()
                .filter(menu -> menu.getMenuType() == MenuType.DRINKS)
                .count();

        int nonDrinks = (int) menus.stream()
                .filter(menu -> menu.getMenuType() != MenuType.DRINKS)
                .count();

        if (drinks != 0 && nonDrinks == 0) {
            throw new IllegalArgumentException(INVALID_MENU_ORDER.getMessage());
        }
    }

    private void validateMenuAmount(List<SingleOrder> singleOrders) {
        int totalAmount = singleOrders.stream()
                .mapToInt(singleOrder -> singleOrder.amount())
                .sum();
        if (totalAmount >= MAX_MENU_AMOUNT.getValue()) {
            throw new IllegalArgumentException(MENU_OVERFLOW.getMessage());
        }
    }

    public int getAmountByMenu(MenuType menuType) {
        return (int) menuScript.keySet()
                .stream()
                .filter(target -> target.getMenuType() == menuType)
                .count();
    }

    public Map<Menu, Integer> getMenuScript() {
        return Collections.unmodifiableMap(menuScript);
    }
}
