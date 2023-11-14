package christmas.constants.menu;

import static christmas.constants.ErrorCode.INVALID_MENU_ORDER;
import static christmas.constants.menu.MenuType.APPETIZER;
import static christmas.constants.menu.MenuType.DESSERT;
import static christmas.constants.menu.MenuType.DRINKS;
import static christmas.constants.menu.MenuType.MAIN;

import java.util.Arrays;
import java.util.Objects;

public enum Menu {
    MUSHROOM_SOUP(APPETIZER, "양송이수프", 6000),
    TAPAS(APPETIZER, "타파스", 5500),
    SALAD(APPETIZER, "시저샐러드", 8000),
    STAKE(MAIN, "티본스테이크", 55_000),
    LIB(MAIN, "바비큐립", 54_000),
    PASTA_(MAIN, "해산물파스타", 35_000),
    PASTA(MAIN, "크리스마스파스타", 25_000),
    CAKE(DESSERT, "초코케이크", 15_000),
    ICE_CREAM(DESSERT, "아이스크림", 5000),
    ZERO_COLA(DRINKS, "제로콜라", 3000),
    WINE(DRINKS, "레드와인", 60_000),
    CHAMPAGNE(DRINKS, "샴페인", 25_000);

    private final MenuType menuType;
    private final String name;
    private final int price;

    Menu(MenuType menuType, String name, int price) {
        this.menuType = menuType;
        this.name = name;
        this.price = price;
    }

    public static Menu from(String input) {
        return Arrays.stream(values())
                .filter(menu -> Objects.equals(menu.name, input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_MENU_ORDER.getMessage()));
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
