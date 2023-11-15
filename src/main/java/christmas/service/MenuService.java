package christmas.service;

import static christmas.constants.event.EventRule.MAX_MENU_AMOUNT;
import static christmas.constants.menu.MenuType.DRINKS;
import static christmas.exception.ErrorCode.INVALID_MENU_ORDER;

import christmas.constants.menu.Menu;
import christmas.constants.menu.MenuType;
import christmas.dto.SingleMenu;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class MenuService {
    private final Map<Menu, Integer> menuScript;

    public MenuService() {
        this.menuScript = new EnumMap<>(Menu.class);
    }

    public void order(List<SingleMenu> singleMenus) {
        validate(singleMenus);
        singleMenus.forEach(singleOrder -> {
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

    private void validate(List<SingleMenu> singleMenus) {
        validateDuplicate(singleMenus);
        validatePerMenuAmount(singleMenus);
        validateOnlyDrink(singleMenus);
        validateTotalMenuAmount(singleMenus);
    }

    private void validatePerMenuAmount(List<SingleMenu> singleMenus) {
        boolean present = singleMenus.stream()
                .anyMatch(singleOrder -> singleOrder.amount() < 1);
        if (present) {
            throw new IllegalArgumentException(INVALID_MENU_ORDER.getMessage());
        }
    }

    private void validateDuplicate(List<SingleMenu> singleMenus) {
        int count = (int) singleMenus.stream()
                .map(singleOrder -> Menu.from(singleOrder.menu()))
                .distinct()
                .count();

        if (count != singleMenus.size()) {
            throw new IllegalArgumentException(INVALID_MENU_ORDER.getMessage());
        }
    }

    private void validateOnlyDrink(List<SingleMenu> singleMenus) {
        int drinks = (int) singleMenus.stream()
                .map(singleOrder -> Menu.from(singleOrder.menu()))
                .filter(menu -> menu.getMenuType() == DRINKS)
                .count();

        if (drinks > 0 && drinks == singleMenus.size()) {
            throw new IllegalArgumentException(INVALID_MENU_ORDER.getMessage());
        }
    }

    private void validateTotalMenuAmount(List<SingleMenu> singleMenus) {
        int totalAmount = singleMenus.stream()
                .mapToInt(SingleMenu::amount)
                .sum();
        if (totalAmount >= MAX_MENU_AMOUNT.getValue()) {
            throw new IllegalArgumentException(INVALID_MENU_ORDER.getMessage());
        }
    }

    public int getAmountByMenu(MenuType menuType) {
        return menuScript.keySet()
                .stream()
                .filter(target -> target.getMenuType() == menuType)
                .mapToInt(menuScript::get)
                .sum();
    }

    public Map<Menu, Integer> getMenuScript() {
        return Collections.unmodifiableMap(menuScript);
    }
}
