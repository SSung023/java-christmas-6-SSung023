package christmas.view;

import static christmas.constants.Message.ASK_MENU;
import static christmas.constants.Message.ASK_VISIT_DATE;
import static christmas.constants.Message.DISCOUNT_HEADER;
import static christmas.constants.Message.EVENT_BADGE;
import static christmas.constants.Message.EXPECT_PAY_HEADER;
import static christmas.constants.Message.GREETING;
import static christmas.constants.Message.MENU_FORMAT;
import static christmas.constants.Message.NONE;
import static christmas.constants.Message.ORDER_HEADER;
import static christmas.constants.Message.PRESENT_HEADER;
import static christmas.constants.Message.PREVIEW;
import static christmas.constants.Message.TOTAL_DISCOUNT_HEADER;
import static christmas.constants.Message.TOTAL_HEADER;
import static christmas.constants.menu.Menu.CHAMPAGNE;

import christmas.constants.event.BadgeType;
import christmas.constants.menu.Menu;
import christmas.model.DiscountResult;
import christmas.model.discount.Discountable;
import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {
    private final DecimalFormat decimalFormat;

    public OutputView() {
        decimalFormat = new DecimalFormat("#,###");
    }

    public void printGreeting() {
        System.out.println(GREETING.getMessage());
    }

    public void printAskVisitDate() {
        System.out.println(ASK_VISIT_DATE.getMessage());
    }

    public void printAskMenu() {
        System.out.println(ASK_MENU.getMessage());
    }

    public void printPresent(Discountable presentDiscount) {
        System.out.println(PRESENT_HEADER.getMessage());
        if (presentDiscount.getDiscountPrice() == 0) {
            System.out.println(NONE.getMessage());
            return;
        }
        System.out.printf(MENU_FORMAT.getMessage(), CHAMPAGNE.getName(), 1);
    }

    //TODO: DiscountResult에서 entry를 받아오는게 아쉽다... 코드의 길이를 더 줄여보자
    public void printDiscountDetails(DiscountResult discountResult) {
        StringBuilder stringBuilder = new StringBuilder(DISCOUNT_HEADER.getMessage());

        if (discountResult.isEventNotApplied()) {
            stringBuilder.append(NONE.getMessage());
            System.out.println(stringBuilder);
            return;
        }

        discountResult.getDiscountApplied()
                .forEach(entry -> {
                    int priceByEvent = discountResult.getDiscountPriceByEvent(entry.getKey());
                    stringBuilder.append(
                            String.format("%s: -%s원%n", entry.getKey().getDescription(),
                                    decimalFormat.format(priceByEvent))
                    );
                });
        System.out.println(stringBuilder);
    }

    public void printTotalDiscountPrice(DiscountResult discountResult) {
        System.out.println(TOTAL_DISCOUNT_HEADER.getMessage());
        System.out.printf("-%s원", decimalFormat.format(discountResult.getTotalDiscountPrice()));
    }

    public void printExpectedPrice(int expectedPrice) {
        System.out.println(EXPECT_PAY_HEADER.getMessage());
        System.out.printf("%s원", decimalFormat.format(expectedPrice));
    }

    public void printEventBadge(BadgeType badgeType) {
        System.out.println(EVENT_BADGE.getMessage());
        System.out.println(badgeType.getName());
    }

    public void printError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void printOrderMenu(Map<Menu, Integer> menuScript) {
        StringBuilder stringBuilder = new StringBuilder(ORDER_HEADER.getMessage());
        menuScript.forEach(
                (menu, amount) -> stringBuilder.append(String.format(MENU_FORMAT.getMessage(), menu.getName(), amount))
        );
        System.out.println(stringBuilder);
    }

    public void printBeforeDiscountPrice(int orderPrice) {
        System.out.println(TOTAL_HEADER.getMessage());
        System.out.printf("%s원", decimalFormat.format(orderPrice));
    }

    public void printPreview(int date) {
        System.out.printf(PREVIEW.getMessage(), date);
    }
}