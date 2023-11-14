package christmas.view.output;

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
    private final Writer writer;
    private final DecimalFormat decimalFormat;

    public OutputView(Writer writer) {
        this.writer = writer;
        decimalFormat = new DecimalFormat("#,###");
    }

    public void printGreeting() {
        writer.printLine(GREETING.getMessage());
    }

    public void printAskVisitDate() {
        writer.printLine(ASK_VISIT_DATE.getMessage());
    }

    public void printAskMenu() {
        writer.printLine(ASK_MENU.getMessage());
    }

    public void printPresent(Discountable presentDiscount) {
        writer.printLine(PRESENT_HEADER.getMessage());
        if (presentDiscount.getDiscountPrice() == 0) {
            writer.printLine(NONE.getMessage());
            return;
        }
        writer.printFormat(MENU_FORMAT.getMessage(), CHAMPAGNE.getName(), 1);
    }

    //TODO: DiscountResult에서 entry를 받아오는게 아쉽다... 코드의 길이를 더 줄여보자
    public void printDiscountDetails(DiscountResult discountResult) {
        StringBuilder stringBuilder = new StringBuilder(DISCOUNT_HEADER.getMessage());

        if (discountResult.isEventNotApplied()) {
            stringBuilder.append(NONE.getMessage());
            writer.printLine(stringBuilder.toString());
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
        writer.printLine(stringBuilder.toString());
    }

    public void printTotalDiscountPrice(DiscountResult discountResult) {
        writer.printLine(TOTAL_DISCOUNT_HEADER.getMessage());
        writer.printFormat("-%s원", decimalFormat.format(discountResult.getTotalDiscountPrice()));
    }

    public void printExpectedPrice(int expectedPrice) {
        writer.printLine(EXPECT_PAY_HEADER.getMessage());
        writer.printFormat("%s원", decimalFormat.format(expectedPrice));
    }

    public void printEventBadge(BadgeType badgeType) {
        writer.printLine(EVENT_BADGE.getMessage());
        writer.printLine(badgeType.getName());
    }

    public void printError(String errorMessage) {
        writer.printLine(errorMessage);
    }

    public void printOrderMenu(Map<Menu, Integer> menuScript) {
        StringBuilder stringBuilder = new StringBuilder(ORDER_HEADER.getMessage());
        menuScript.forEach(
                (menu, amount) -> stringBuilder.append(String.format(MENU_FORMAT.getMessage(), menu.getName(), amount))
        );
        writer.printLine(stringBuilder.toString());
    }

    public void printBeforeDiscountPrice(int orderPrice) {
        writer.printLine(TOTAL_HEADER.getMessage());
        writer.printFormat("%s원", decimalFormat.format(orderPrice));
    }

    public void printPreview(int date) {
        writer.printFormat(PREVIEW.getMessage(), date);
    }
}